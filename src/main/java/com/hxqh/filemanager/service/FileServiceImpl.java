package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.*;
import com.hxqh.filemanager.model.assist.*;
import com.hxqh.filemanager.model.view.VFileKeywordKeyWord;
import com.hxqh.filemanager.repository.*;
import com.hxqh.filemanager.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static com.hxqh.filemanager.common.IConstants.*;

/**
 * Created by Ocean lin on 2018/10/30.
 *
 * @author Ocean lin
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    private final static Logger logger = Logger.getLogger(FileServiceImpl.class);

    @Value(value = "${com.hxqh.filemanager.upload}")
    private String uploadPath;

    @Value(value = "${com.hxqh.filemanager.private}")
    private String privatePath;

    @Value(value = "${com.hxqh.filemanager.file.url}")
    private String webUrl;

    @Value(value = "${com.hxqh.filemanager.file.tomcat.url}")
    private String iconUrl;

    @Value(value = "${com.hxqh.filemanager.file.download}")
    private String downloadUrl;

    @Value(value = "${com.hxqh.filemanager.icon}")
    private String icondPath;


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }


    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileVersionRepository fileVersionRepository;
    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private CurrentFileLogRepository currentFileLogRepository;
    @Autowired
    private FileLogRepository fileLogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private FileKeywordRepository fileKeywordRepository;

    @Autowired
    private VFileKeywordKeyWordRepository vFileKeywordKeyWordRepository;
    @Autowired
    private VBaseKeywordFileRepository vBaseKeywordFileRepository;


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FileDto fileList(TbFile file, Pageable pageable) {
        Specification<TbFile> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>(10);

            // todo appid
            Integer isShow = null == file.getIsshow() ? 1 : 0;
            list.add(cb.equal(root.get("isshow").as(Integer.class), isShow));
            if (StringUtils.isNotBlank(file.getAppname())) {
                list.add(cb.equal(root.get("appname").as(String.class), file.getAppname()));
            }
            if (null != file.getRecordid()) {
                list.add(cb.equal(root.get("recordid").as(Integer.class), file.getRecordid()));
            }
            if (StringUtils.isNotBlank(file.getFilerealname())) {
                list.add(cb.like(root.get("filerealname").as(String.class), file.getFilerealname() + "%"));
            }

            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };

        Page<TbFile> files = fileRepository.findAll(specification, pageable);

        List<TbFile> fileList = files.getContent();
        fileList.stream().map(e -> {
            e.setWebUrl(webUrl + e.getFilepath());
            e.setFilepath(webUrl + downloadUrl + DOWNLOAD_FILE + e.getFileid());
            return e;
        }).collect(Collectors.toList());

        Integer totalPages = files.getTotalPages();
        FileDto fileDto = new FileDto(pageable, totalPages, files.getTotalElements(), fileList);
        return fileDto;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FileVersionDto fileVersionList(TbFileVersion fileVersion, Pageable pageable) {
        Specification<TbFileVersion> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>(5);

            if (StringUtils.isNotBlank(fileVersion.getFilerealname())) {
                list.add(cb.like(root.get("filerealname").as(String.class), fileVersion.getFilerealname() + "%"));
            }
            if (null != fileVersion.getFileid()) {
                list.add(cb.equal(root.get("tbFile").get("fileid").as(String.class), fileVersion.getFileid()));
            }
            if (null != fileVersion.getFileversion()) {
                list.add(cb.equal(root.get("fileversion").as(String.class), fileVersion.getFileversion()));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };

        Page<TbFileVersion> fileVersions = fileVersionRepository.findAll(specification, pageable);

        List<TbFileVersion> fileVersionList = fileVersions.getContent();
        fileVersionList.stream().map(e -> {
            e.setWebUrl(webUrl + e.getFilepath());
            e.setFilepath(webUrl + downloadUrl + DOWNLOAD_VERSION + e.getFileversionid());
            return e;
        }).collect(Collectors.toList());
        Integer totalPages = fileVersions.getTotalPages();
        FileVersionDto fileVersionDto = new FileVersionDto(pageable, totalPages, fileVersions.getTotalElements(), fileVersionList);
        return fileVersionDto;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public TbFile findByFileid(Integer fid) {
        return fileRepository.findByFileid(fid);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public TbFileVersion findByFileversionid(Integer fid) {
        return fileVersionRepository.findByFileversionid(fid);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public boolean hasSameFile(MultipartFile files, Integer fileid) throws Exception {
        String md5String = Md5Utils.getFileMD5String(files.getBytes());
        TbFile file = fileRepository.findByFileid(fileid);
        List<TbFileVersion> fileVersionByMd5 = fileVersionRepository.findByMd5(md5String);

        if (md5String.equals(file.getMd5())) {
            return true;
        }
        for (int i = 0; i < fileVersionByMd5.size(); i++) {
            TbFileVersion fileVersion = fileVersionByMd5.get(i);
            if (md5String.equals(fileVersion.getMd5())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 保存文件
     * <p>
     * 先判断tb_file tb_fileverson是否存在相同的md5
     * 相同的md5文件不新建文件，设置引用关系
     *
     * @param file     文件
     * @param fileInfo 文件信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FileIdSize saveFile(MultipartFile file, FileInfo fileInfo) throws Exception {
        Refer refer = null;
        String filePath, savePath;
        String md5String = Md5Utils.getFileMD5String(file.getBytes());

        List<TbFile> fileByMd5 = fileRepository.findByMd5(md5String);
        List<TbFileVersion> fileVersionByMd5 = fileVersionRepository.findByMd5(md5String);

        // 生成随机文件名称
        savePath = generateFileName(file, fileInfo, uploadPath);

        if (file.getOriginalFilename() != null && file.getSize() > 0) {
            // 保存至文件系统
            if (fileByMd5.size() == 0 && fileVersionByMd5.size() == 0) {
                if (0 == fileInfo.getPathid()) {
                    filePath = uploadPath + savePath;
                } else {
                    Optional<TbPath> path = pathRepository.findById(fileInfo.getPathid());
                    filePath = path.get().getPathname() + savePath;
                }
                try {
                    //读取要加密的文件流
                    byte[] bytes = file.getBytes();
                    byte[] encodeBytes = Sm4Util.encryptEcb(KEY, bytes);
                    FileUtil.writeFileByByte(filePath, encodeBytes);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            } else {
                refer = getSavePath(fileByMd5, fileVersionByMd5);
                savePath = refer.getSavePath();
            }

            if (null == fileInfo.getFileid()) {
                // 保存文件信息
                TbFile save = saveFileIno(file, fileInfo, refer, savePath, md5String);
                String webLink = webUrl + downloadUrl + DOWNLOAD_FILE + save.getFileid();
                FileIdSize fileIdSize = new FileIdSize(save.getFileid(), save.getFilesize(), webLink);
                fileIdSize.setFilename(save.getFilename());
                fileIdSize.setFilerealname(save.getFilerealname());
                return fileIdSize;
            } else {
                // 保存文件版本信息
                TbFileVersion tbFileVersion = saveFileVersion(file, fileInfo, refer, savePath, md5String);
                String webLink = webUrl + downloadUrl + DOWNLOAD_FILE + tbFileVersion.getFileversionid();
                FileIdSize fileIdSize = new FileIdSize(tbFileVersion.getFileversionid(), tbFileVersion.getFilesize(), webLink);
                return fileIdSize;
            }

        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public IconDto saveIcon(MultipartFile file, FileInfo fileInfo) throws IOException {
        String filePath = null, savePath;
        TbPath path = null;
        String uuid = UUID.randomUUID().toString();
        savePath = uuid + "." + file.getOriginalFilename().split("\\.")[1];

        if (file.getOriginalFilename() != null && file.getSize() > 0) {
            // 保存至文件系统
            path = pathRepository.findById(fileInfo.getPathid()).get();
            filePath = path.getPathname() + "/" + savePath;
            try {
                byte[] bytes = file.getBytes();
                FileUtil.writeFileByByte(filePath, bytes);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        TbFile tbFile = saveIconIno(file, fileInfo, savePath, path, uuid);
        IconDto iconDto = new IconDto(iconUrl + "/" + tbFile.getFilepath(), tbFile.getFileid());
        return iconDto;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public IconDto getIconUrl(FileInfo fileInfo) {
        TbFile file = fileRepository.findAppnameAndUserid(fileInfo.getAppname(), fileInfo.getUserid());
        IconDto iconDto = new IconDto(iconUrl + "/" + file.getFilepath(), file.getFileid());
        return iconDto;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public CategoryKeyWordTree categoryKeyWordTreeList() {
        List<TbCategory> categoryList = categoryRepository.findByStatus();
        List<TbKeyword> keywordList = keywordRepository.findByStatus();
        // 先对keywordList分组
        Map<Integer, List<TbKeyword>> listMap = GroupListUtil.group(keywordList, (obj) -> {
            TbKeyword d = (TbKeyword) obj;
            return d.getCategoryid();
        });


        for (int i = 0; i < categoryList.size(); i++) {
            TbCategory tbCategory = categoryList.get(i);
            for (Map.Entry entry : listMap.entrySet()) {
                if (tbCategory.getId().equals(entry.getKey())) {
                    tbCategory.setKeywordList((List<TbKeyword>) entry.getValue());
                }
            }
        }
        return new CategoryKeyWordTree(categoryList);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public BaseKeywordDto baseKeywordList(BaseKeywordFile baseKeywordFile, int page, int size) {
        Session session = getSession();
        String filename = baseKeywordFile.getFilename();
        Integer offset = (page - 1) * size;
        Integer pageSize = size;

        List<VBaseKeywordFile> list = getvBaseKeywordFiles(baseKeywordFile, session, filename, offset, pageSize);

        // 查询总数
        Integer total = getCount(baseKeywordFile, session, filename);
        Integer totalPages = (total + pageSize - 1) / pageSize;

        list.stream().map(e -> {
            e.setFilepath(webUrl + downloadUrl + DOWNLOAD_FILE + e.getFileid());
            return e;
        }).collect(Collectors.toList());

        return new BaseKeywordDto(list, total, totalPages, page, size);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private List<VBaseKeywordFile> getvBaseKeywordFiles(BaseKeywordFile baseKeywordFile, Session session, String filename, Integer offset, Integer pageSize) {
        StringBuilder querySQL = new StringBuilder(300);
        querySQL.append(KEYWORD_SQL_1);
        querySQL.append(COMMON_CONDTION);
        if (!"".equals(filename)) {
            querySQL.append("and f.filename like :filename ");
        }
        querySQL.append("LIMIT :offset,:pageSize");

        NativeQuery nativeQuery = session.createSQLQuery(querySQL.toString())
                .addEntity(VBaseKeywordFile.class)
                .setParameter("categoryid", baseKeywordFile.getCategoryid())
                .setParameter("keywordid", baseKeywordFile.getKeywordid())
                .setParameter("offset", offset)
                .setParameter("pageSize", pageSize)
                .setParameter("userid", baseKeywordFile.getUserid());

        if (!"".equals(filename)) {
            nativeQuery.setParameter("filename", filename + "%");
        }
        return (List<VBaseKeywordFile>) nativeQuery.list();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private Integer getCount(BaseKeywordFile baseKeywordFile, Session session, String filename) {
        StringBuilder countSQL = new StringBuilder(300);
        countSQL.append(KEYWORD_SQL_2);
        countSQL.append(COMMON_CONDTION);
        if (!"".equals(filename)) {
            countSQL.append("and f.filename like :filename ");
        }

        NativeQuery countQuery = session.createSQLQuery(countSQL.toString())
                .setParameter("categoryid", baseKeywordFile.getCategoryid())
                .setParameter("keywordid", baseKeywordFile.getKeywordid())
                .setParameter("userid", baseKeywordFile.getUserid());

        if (!"".equals(filename)) {
            countQuery.setParameter("filename", filename + "%");
        }
        return Integer.parseInt(countQuery.uniqueResult().toString());
    }

    private TbFile saveIconIno(MultipartFile file, FileInfo fileInfo, String savePath, TbPath path, String uuid) throws IOException {
        TbFile tbFile = null;
        if (0 == fileInfo.getFileid()) {
            fileInfo.setFileid(null);
            tbFile = new TbFile();
        } else {
            tbFile = fileRepository.findByFileid(fileInfo.getFileid());
        }
        setFileProperties(file, fileInfo, savePath, tbFile);
        tbFile.setTbPath(path);
        tbFile.setFilename(file.getOriginalFilename());
        tbFile.setExtensionname(file.getOriginalFilename().split("\\.")[1]);
        tbFile.setIsshow(1);
        tbFile.setFilename(uuid);
        tbFile.setFilestatus(STATUS_RELEASE);
        String md5String = Md5Utils.getFileMD5String(file.getBytes());
        tbFile.setMd5(md5String);
        fileRepository.saveAndFlush(tbFile);
        return tbFile;
    }

    private TbFile saveFileIno(MultipartFile file, FileInfo fileInfo, Refer refer, String savePath, String md5String) {
        TbFile tbFile = new TbFile();
        setFileProperties(file, fileInfo, savePath, tbFile);

        if (0 != fileInfo.getAppid()) {
            String path = uploadPath + "/" + fileInfo.getDeptfullname();
            // 存储路径
            mkdirStorePath(path, fileInfo.getDeptfullname(), fileInfo);
            TbPath tbPath = pathRepository.findByPathname(path);
            tbFile.setTbPath(tbPath);
        } else {
            Optional<TbPath> path = pathRepository.findById(fileInfo.getPathid());
            //String filePath = path.get().getPathname() + savePath;
            tbFile.setTbPath(path.get());
        }

        tbFile.setFilestatus(STATUS_RELEASE);
        tbFile.setMd5(md5String);
        tbFile.setFilename(file.getOriginalFilename().split("\\.")[0]);
        tbFile.setExtensionname(file.getOriginalFilename().split("\\.")[1]);
        if (null != refer) {
            BeanUtils.copyProperties(refer, tbFile);
        }

        TbFileLog fileLog = new TbFileLog();
        BeanUtils.copyProperties(tbFile, fileLog);
        fileLog.setOperatetime(new Date());
        fileLog.setOperatecount(1);
        fileLog.setOperatetype(UPDATE_STATE);
        fileLog.setTbFile(tbFile);
        List<TbFileLog> fileLogList = new ArrayList<>();
        fileLogList.add(fileLog);

        TbCurrentFileLog tbCurrentFileLog = new TbCurrentFileLog();
        BeanUtils.copyProperties(fileLog, tbCurrentFileLog);
        tbCurrentFileLog.setOperatetime(new Date());
        tbCurrentFileLog.setOperatecount(1);
        tbCurrentFileLog.setOperatetype(UPDATE_STATE);
        tbCurrentFileLog.setTbFile(tbFile);
        List<TbCurrentFileLog> tbCurrentFileLogList = new ArrayList<>();
        tbCurrentFileLogList.add(tbCurrentFileLog);

        tbFile.setTbFileLogs(fileLogList);
        tbFile.setTbCurrentFileLogs(tbCurrentFileLogList);
        tbFile.setIsshow(1);

        TbFile save = fileRepository.save(tbFile);
        fileLogRepository.save(fileLog);
        currentFileLogRepository.save(tbCurrentFileLog);
        return save;
    }

    private TbFileVersion saveFileVersion(MultipartFile file, FileInfo fileInfo, Refer refer, String savePath, String md5String) {
        TbFile tbFile = fileRepository.findByFileid(fileInfo.getFileid());
        TbFileVersion fileVersion = new TbFileVersion();
        BeanUtils.copyProperties(tbFile, fileVersion);

        TbFile newFile = new TbFile();
        BeanUtils.copyProperties(tbFile, fileInfo);
        TbFile newVersion = setFileProperties(file, fileInfo, savePath, newFile);
        newVersion.setFileversion(tbFile.getFileversion() + 1);

        BeanUtils.copyProperties(newVersion, tbFile);
        fileVersion.setTbFile(tbFile);
        fileVersion.setMd5(md5String);
        if (null != refer) {
            BeanUtils.copyProperties(refer, fileVersion);
        }
        TbFileVersion save = fileVersionRepository.save(fileVersion);
        return save;
    }

    private Cipher getCipherEncrpt() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        File fileKey = new File(uploadPath + "/a.text");
        byte[] key = new byte[(int) fileKey.length()];
        FileInputStream fis = new FileInputStream(fileKey);
        fis.read(key);
        //根据给定的字节数组(密钥数组)构造一个AES密钥。
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        //实例化一个密码器（CBC模式）
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //初始化密码器
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, new IvParameterSpec(IV.getBytes()));
        return cipher;
    }

    private Refer getSavePath(List<TbFile> fileByMd5, List<TbFileVersion> fileVersionByMd5) {
        Refer refer = null;
        if (fileByMd5.size() > 0) {
            refer = new Refer(FILE_REFER, fileByMd5.get(0).getFileid(), fileByMd5.get(0).getFilepath());
        }
        if (fileVersionByMd5.size() > 0) {
            refer = new Refer(VERSION_REFER, fileVersionByMd5.get(0).getFileversionid(), fileVersionByMd5.get(0).getFilepath());
        }
        return refer;
    }

    private String generateFileName(MultipartFile file, FileInfo fileInfo, String uploadPath) {
        String savePath = null;
        if (0 != fileInfo.getAppid()) {
            String path = uploadPath + "/" + fileInfo.getDeptfullname();
            FileUtil.createPaths(path);
            String extensionName = file.getOriginalFilename().split("\\.")[1];
            savePath = "/" + fileInfo.getDeptfullname() + "/" + DateUtils.getTodayTime() + "_" + UUID.randomUUID() + "." + extensionName;
        } else {
            String path = "/" + fileInfo.getDeptfullname();
            String extensionName = file.getOriginalFilename().split("\\.")[1];
            savePath = "/" + DateUtils.getTodayTime() + "_" + UUID.randomUUID() + "." + extensionName;
        }
        return savePath;
    }

    private TbPath mkdirStorePath(String path, String folderName, FileInfo fileInfo) {
        FileUtil.createPaths(path);

        // 保存至path中
        TbPath tbPath = pathRepository.findByPathname(path);
        TbPath tbParentPath = pathRepository.findByPathname(uploadPath);
        if (null == tbPath) {
            tbPath = new TbPath();
            tbPath.setParentid(tbParentPath.getPathid());
            tbPath.setPathname(path);
            tbPath.setParentname(uploadPath);
            tbPath.setFoldername(folderName);
            tbPath.setDeptid(fileInfo.getDeptid());
            pathRepository.save(tbPath);
        }
        return tbPath;
    }

    private TbFile setFileProperties(MultipartFile file, FileInfo fileInfo, String savePath, TbFile tbFile) {
        BeanUtils.copyProperties(fileInfo, tbFile);

        tbFile.setFilerealname(file.getOriginalFilename());
        Double fileSize = file.getSize() * 1.0 / THOUSAND / THOUSAND;
        tbFile.setFilesize(fileSize.floatValue());
        tbFile.setStatustime(new Date());
        tbFile.setUploadtime(new Date());
        tbFile.setFileversion(1);
        tbFile.setFilepath(savePath);
        return tbFile;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void logicDeleteFile(Integer fileId) {
        TbFile file = fileRepository.findByFileid(fileId);
        file.setIsshow(0);
        fileRepository.save(file);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFile(Integer fileId) throws Exception {
        if (null != fileId) {
            // 文件系统删除
            TbFile file = fileRepository.findByFileid(fileId);
            String filePath = uploadPath + file.getFilepath();

            // 先查询主记录是否存在被引用情况
            List<TbFile> fileList = fileRepository.findByRefertabAndReferid(FILE_REFER, file.getFileid());
            // 重置引用关系
            resetFileList(fileList);

            List<TbFileVersion> fileVersionList = fileVersionRepository.findByRefertabAndReferid(FILE_REFER, file.getFileid());
            // 重置引用关系
            resetFileVersionList(fileVersionList);


            if (fileList.size() == 0 && fileVersionList.size() == 0) {
                FileUtil.deleteFile(filePath);
            }

            List<TbFileVersion> fileVersions = file.getTbFileVersions();
            for (int i = 0; i < fileVersions.size(); i++) {
                TbFileVersion fileVersion = fileVersions.get(i);
                filePath = uploadPath + fileVersion.getFilepath();
                FileUtil.deleteFile(filePath);
            }

            // 删除tb_file_keyword
            List<TbFileKeyword> fileKeywords = fileKeywordRepository.findByFileId(file.getFileid());
            for (TbFileKeyword fileKeyword : fileKeywords) {
                fileKeywordRepository.deleteById(fileKeyword.getFilekeywordid());
            }

            // 数据库删除
            fileRepository.delete(file);
        }

        // todo 后期检查
//        if (null != fileInfo.getFileversionid()) {
//            //文件系统删除
//            TbFileVersion fileVersion = fileVersionRepository.findByFileversionid(fileInfo.getFileversionid());
//            String filePath = uploadPath + fileVersion.getFilepath();
//
//            // 先查询主记录是否存在被引用情况
//            List<TbFile> fileList = fileRepository.findByRefertabAndReferid(VERSION_REFER, fileVersion.getFileversionid());
//            // 重置引用关系
//            resetFileList(fileList);
//
//            List<TbFileVersion> fileVersionList = fileVersionRepository.findByRefertabAndReferid(VERSION_REFER, fileVersion.getFileversionid());
//            // 重置引用关系
//            resetFileVersionList(fileVersionList);
//
//            if (fileList.size() == 0 && fileVersionList.size() == 0) {
//                FileUtil.deleteFile(filePath);
//            }

        // 数据库删除
//            fileVersionRepository.delete(fileVersion);
    }


    private void resetFileVersionList(List<TbFileVersion> fileVersionList) {
        // 选取一条设置
        for (int i = 0; i < fileVersionList.size(); i++) {
            TbFileVersion fileVersion = fileVersionList.get(i);
            if (i == 0) {
                fileVersion.setReferid(null);
                fileVersion.setRefertab(null);
            } else {
                fileVersion.setReferid(fileVersionList.get(0).getFileid());
            }
            fileVersionRepository.save(fileVersion);
        }
    }

    private void resetFileList(List<TbFile> fileList) {
        // 选取一条设置
        for (int i = 0; i < fileList.size(); i++) {
            if (0 == i) {
                fileList.get(0).setReferid(null);
                fileList.get(0).setRefertab(null);
                fileRepository.save(fileList.get(0));
            } else {
                fileList.get(i).setReferid(fileList.get(0).getFileid());
                fileRepository.save(fileList.get(i));
            }
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public boolean isExist(TbPath tbPath) {
        TbPath path = pathRepository.findParentIdAndFoldername(tbPath.getParentid(), tbPath.getFoldername());
        if (null != path) {
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createPath(TbPath tbPath) {
        Optional<TbPath> path = pathRepository.findById(tbPath.getParentid());
        String parentPathName = path.get().getPathname();
        String newPath = parentPathName + "/" + tbPath.getFoldername();
        TbPath newTbPath = new TbPath();

        newTbPath.setPathname(newPath);
        newTbPath.setParentname(parentPathName);
        newTbPath.setParentid(path.get().getPathid());
        newTbPath.setFoldername(tbPath.getFoldername());
        newTbPath.setDeptid(tbPath.getDeptid());
        pathRepository.save(newTbPath);

        // 创建文件夹
        FileUtil.createPaths(newPath);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<TbPath> pathList(TbPath path) {
        List<TbPath> pathList;

        if (path.getPathid() == 3) {
            pathList = pathRepository.findByParentidAndDeptidWithRoot(path.getPathid(), path.getDeptid());
        } else {
            pathList = pathRepository.findByParentidAndDeptid(path.getPathid(), path.getDeptid());
        }
        return pathList;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public boolean hasFile(Integer pathId) {
        List<TbFile> tbFiles = fileRepository.findByPathid(pathId);
        if (null != tbFiles && tbFiles.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public boolean hasPath(Integer pathId) {
        List<TbPath> tbPaths = pathRepository.findByParentid(pathId);
        if (null != tbPaths && tbPaths.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FileDto findFileByPathId(TbPath path, Sort sort, int page, int size) {
        Specification<TbFile> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>(10);

            if (null != path.getPathid()) {
                list.add(cb.equal(root.get("tbPath").get("pathid").as(Integer.class), path.getPathid()));
            }
//            if (null != path.getUserid()) {
//                list.add(cb.equal(root.get("userid").as(Integer.class), path.getUserid()));
//            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TbFile> files = fileRepository.findAll(specification, pageable);

        List<TbFile> fileList = files.getContent();
        Integer totalPages = files.getTotalPages();
        fileList.stream().map(e -> {
            e.setFilepath(webUrl + downloadUrl + DOWNLOAD_FILE + e.getFileid());
            return e;
        }).collect(Collectors.toList());

        FileDto fileDto = new FileDto(pageable, totalPages, files.getTotalElements(), fileList);
        return fileDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePath(Integer pathId) {
        TbPath tbPath = pathRepository.findById(pathId).get();
        pathRepository.deleteById(pathId);
        FileUtil.deleteDir(new File(tbPath.getPathname()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void fileBindKeyword(FileKeyword fileKeyword) {
        List<Integer> deleteFilekeywordidList = fileKeyword.getDeleteFilekeywordidList();
        List<CategoryKeyword> categoryKeywordList = fileKeyword.getCategoryKeywordList();
        List<TbFileKeyword> keywordList = new ArrayList<>(50);
        TbFile tbFile = fileRepository.findById(fileKeyword.getFileid()).get();
        for (CategoryKeyword categoryKeyword : categoryKeywordList) {
            if (null == categoryKeyword.getFilekeywordid()) {
                TbFileKeyword tbFileKeyword = new TbFileKeyword();
                BeanUtils.copyProperties(categoryKeyword, tbFileKeyword);
                tbFileKeyword.setTbFile(tbFile);
                keywordList.add(tbFileKeyword);
            }
        }

        // column删除
        if (null != deleteFilekeywordidList && deleteFilekeywordidList.size() >= 1) {
            for (Integer filekeywordid : deleteFilekeywordidList) {
                fileKeywordRepository.deleteById(filekeywordid);
            }
        }
        fileKeywordRepository.saveAll(keywordList);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FilePrivilege privilege(FilePrivilegeDto filePrivilegeDto) {
        List<FilePrivilege> list = getSession().createSQLQuery(SQL_1 + " :fileid \n" + SQL_2 + " :userid ")
                .addEntity(FilePrivilege.class).setParameter("fileid", filePrivilegeDto.getFileid())
                .setParameter("userid", filePrivilegeDto.getUserid()).list();

        return getFilePrivilege(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TbCurrentFileLog logAndReturnCurrent(TbFileLog tbFileLog) {
        TbCurrentFileLog currentFileLog = currentFileLogRepository.findByFileidAndOperatetype(tbFileLog.getFileid(), DOWNLOAD_STATE);
        if (null == currentFileLog) {
            currentFileLog = new TbCurrentFileLog();
            currentFileLog.setOperatecount(0);
            Optional<TbFile> file = fileRepository.findById(tbFileLog.getFileid());
            currentFileLog.setTbFile(file.get());
        }
        currentFileLog.setUserid(tbFileLog.getUserid());
        currentFileLog.setUsername(tbFileLog.getUsername());
        currentFileLog.setDeptid(tbFileLog.getDeptid());
        currentFileLog.setDeptfullname(tbFileLog.getDeptfullname());
        currentFileLog.setOperatetype(DOWNLOAD_STATE);
        currentFileLog.setOperatetime(new Date());
        currentFileLog.setOperatecount(currentFileLog.getOperatecount() + 1);
        currentFileLogRepository.save(currentFileLog);

        TbFileLog fileLog = new TbFileLog();
        BeanUtils.copyProperties(currentFileLog, fileLog);
        fileLogRepository.save(fileLog);
        return currentFileLog;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public TbPath findPathById(Integer pathid) {
        Optional<TbPath> path = pathRepository.findById(pathid);
        return path.get();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<VFileKeywordKeyWord> fileKeywordList(TbFile file) {
        return vFileKeywordKeyWordRepository.findByFileid(file.getFileid());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FileLogDto fileLogList(TbFile file, Pageable pageable) {
        Specification<TbFileLog> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>(5);

            if (null != file.getFileid()) {
                list.add(cb.equal(root.get("tbFile").get("fileid").as(Integer.class), file.getFileid()));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        Page<TbFileLog> fileLogPage = fileLogRepository.findAll(specification, pageable);
        List<TbFileLog> fileLogList = fileLogPage.getContent();
        Integer totalPages = fileLogPage.getTotalPages();
        FileLogDto fileDto = new FileLogDto(pageable, totalPages, fileLogPage.getTotalElements(), fileLogList);
        return fileDto;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<TbCurrentFileLog> fileCurrentLogList(TbFile file) {
        return currentFileLogRepository.findByFileId(file.getFileid());
    }


    /**
     * 1. 遍历categoryid与categoryid2相同放入CommonSet
     * 2. 不相同放入DenySet
     * 3. 判断如果DenySet不存在于CommonSet 不允许访问
     * 4. 若DenySet存在于CommonSet 允许访问
     * 5. 权限做设置
     */
    private FilePrivilege getFilePrivilege(List<FilePrivilege> list) {
        Set<Integer> commonSet = new HashSet<>(50);
        Set<FilePrivilege> commonPrivilegeSet = new HashSet<>(50);
        Set<Integer> denySet = new HashSet<>(50);

        for (FilePrivilege privilege : list) {
            if (privilege.getCategoryid().equals(privilege.getCategoryid2())) {
                commonSet.add(privilege.getCategoryid());
                commonPrivilegeSet.add(privilege);
            } else {
                denySet.add(privilege.getCategoryid());
            }
        }

        FilePrivilege filePrivilege = new FilePrivilege();
        if (commonSet.containsAll(denySet)) {
            for (FilePrivilege common : commonPrivilegeSet) {
                filePrivilege.setFilekeywordid(common.getFilekeywordid());
                filePrivilege.setFileid(common.getFileid());
                filePrivilege.setCategoryid(common.getCategoryid());
                filePrivilege.setUserid(common.getUserid());

                Integer fileread = common.getFileread() == 1 ? 1 : 0;
                Integer fileedit = common.getFileedit() == 1 ? 1 : 0;
                Integer fileprint = common.getFileprint() == 1 ? 1 : 0;
                Integer fileupload = common.getFileupload() == 1 ? 1 : 0;
                Integer filedownload = common.getFiledownload() == 1 ? 1 : 0;
                Integer fileduplicate = common.getFileduplicate() == 1 ? 1 : 0;
                Integer filedelete = common.getFiledelete() == 1 ? 1 : 0;
                filePrivilege.setFileread(fileread);
                filePrivilege.setFileedit(fileedit);
                filePrivilege.setFileprint(fileprint);
                filePrivilege.setFileupload(fileupload);
                filePrivilege.setFiledownload(filedownload);
                filePrivilege.setFileduplicate(fileduplicate);
                filePrivilege.setFiledelete(filedelete);
            }
        } else {
            filePrivilege.setFilekeywordid(0);
        }

        return filePrivilege;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public TbFile filePath(Integer fileId) {
        TbFile file = fileRepository.findById(fileId).get();
        String pathname = file.getTbPath().getParentname();
        file.setFilepath(pathname + file.getFilepath());
        return file;
    }


}
