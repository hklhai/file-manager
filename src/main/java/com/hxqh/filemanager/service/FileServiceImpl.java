package com.hxqh.filemanager.service;

import com.hxqh.filemanager.common.IConstants;
import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.TbPath;
import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.model.assist.FileDto;
import com.hxqh.filemanager.model.assist.FileInfo;
import com.hxqh.filemanager.model.assist.FileVersionDto;
import com.hxqh.filemanager.model.assist.Refer;
import com.hxqh.filemanager.repository.*;
import com.hxqh.filemanager.util.DateUtils;
import com.hxqh.filemanager.util.FileUtil;
import com.hxqh.filemanager.util.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.hxqh.filemanager.common.IConstants.THOUSAND;

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

    @Value(value = "${com.hxqh.filemanager.file.download}")
    private String downloadUrl;

    @Autowired
    private UserRepository userRepository;
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
    private KeywordRepository keywordRepository;


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override

    public FileDto fileList(TbFile file, Pageable pageable) {
        Specification<TbFile> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>(10);

            if (StringUtils.isNotBlank(file.getAppname())) {
                list.add(cb.equal(root.get("appname").as(String.class), file.getAppname()));
            }
            if (null != file.getRecordid()) {
                list.add(cb.equal(root.get("recordid").as(Integer.class), file.getRecordid()));
            }
            if (StringUtils.isNotBlank(file.getFilerealname())) {
                list.add(cb.like(root.get("filerealname").as(String.class), "%" + file.getFilerealname() + "%"));
            }

            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };

        Page<TbFile> files = fileRepository.findAll(specification, pageable);

        List<TbFile> fileList = files.getContent();
        fileList.stream().map(e -> {
            e.setWebUrl(webUrl + e.getFilepath());
            e.setFilepath(webUrl + downloadUrl + IConstants.DOWNLOAD_FILE + e.getFileid());
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
                list.add(cb.like(root.get("filerealname").as(String.class), "%" + fileVersion.getFilerealname() + "%"));
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
            e.setFilepath(webUrl + downloadUrl + IConstants.DOWNLOAD_VERSION + e.getFileversionid());
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


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public User findByUserid(Integer userId) {
        return userRepository.findByUserid(userId);
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
    public void saveFile(MultipartFile file, FileInfo fileInfo) throws Exception {
        Refer refer = null;
        String filePath, savePath;
        String md5String = Md5Utils.getFileMD5String(file.getBytes());

        List<TbFile> fileByMd5 = fileRepository.findByMd5(md5String);
        List<TbFileVersion> fileVersionByMd5 = fileVersionRepository.findByMd5(md5String);


        // 生成随机文件名称
        savePath = generateFileName(file);

        if (file.getOriginalFilename() != null && file.getSize() > 0) {
            // 保存至文件系统
            if (fileByMd5.size() == 0 && fileVersionByMd5.size() == 0) {
                filePath = uploadPath + savePath;
                FileOutputStream outputStream;
                try {
                    outputStream = new FileOutputStream(new File(filePath));
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            } else {
                refer = getSavePath(fileByMd5, fileVersionByMd5);
                savePath = refer.getSavePath();
            }

            if (null == fileInfo.getFileid()) {
                // 保存文件信息
                TbFile tbFile = new TbFile();
                setFileProperties(file, fileInfo, savePath, tbFile);

                // todo 增加目录
                if (null != fileInfo.getAppname()) {
                    String path = uploadPath + "/" + DateUtils.getTodayMonth();

                    // 存储路径
                    mkdirStorePath(path);
                    TbPath tbPath = pathRepository.findByPathname(uploadPath);
                    tbFile.setTbPath(tbPath);
                }

                tbFile.setFilestatus(IConstants.STATUS_RELEASE);
                tbFile.setMd5(md5String);
                tbFile.setFilename(file.getOriginalFilename().split("\\.")[0]);
                tbFile.setExtensionname(file.getOriginalFilename().split("\\.")[1]);
                if (null != refer) {
                    BeanUtils.copyProperties(refer, tbFile);
                }
                fileRepository.save(tbFile);
            } else {
                // 保存文件版本信息
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
                fileVersionRepository.save(fileVersion);
            }
        }

    }

    private Refer getSavePath(List<TbFile> fileByMd5, List<TbFileVersion> fileVersionByMd5) {
        Refer refer = null;
        if (fileByMd5.size() > 0) {
            refer = new Refer(IConstants.FILE_REFER, fileByMd5.get(0).getFileid(), fileByMd5.get(0).getFilepath());
        }
        if (fileVersionByMd5.size() > 0) {
            refer = new Refer(IConstants.VERSION_REFER, fileVersionByMd5.get(0).getFileversionid(), fileVersionByMd5.get(0).getFilepath());
        }
        return refer;
    }

    private String generateFileName(MultipartFile file) {
        String savePath;
        String extensionName = file.getOriginalFilename().split("\\.")[1];
        savePath = "/" + DateUtils.getTodayMonth() + "/" + DateUtils.getTodayTime() + "_" + UUID.randomUUID()
                + "." + extensionName;
        return savePath;
    }

    private void mkdirStorePath(String path) {
        FileUtil.createPaths(path);
        // 保存至path中
        TbPath tbPath = pathRepository.findByPathname(path);
        TbPath tbParentPath = pathRepository.findByPathname(uploadPath);
        if (null == tbPath) {
            tbPath = new TbPath();
            tbPath.setParentid(tbParentPath.getPathid());
            tbPath.setPathname(path);
            tbPath.setParentname(uploadPath);
        }
        pathRepository.save(tbPath);
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
    public void deleteFile(FileInfo fileInfo) {

        if (null != fileInfo.getFileid()) {
            // 文件系统删除
            TbFile file = fileRepository.findByFileid(fileInfo.getFileid());
            String filePath = uploadPath + file.getFilepath();

            // 先查询主记录是否存在被引用情况
            List<TbFile> fileList = fileRepository.findByRefertabAndReferid(IConstants.FILE_REFER, file.getFileid());
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

            List<TbFileVersion> fileVersionList = fileVersionRepository.findByRefertabAndReferid(IConstants.FILE_REFER, file.getFileid());
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


            if (fileList.size() == 0 && fileVersionList.size() == 0) {
                FileUtil.deleteFile(filePath);
            }

            List<TbFileVersion> fileVersions = file.getTbFileVersions();
            for (int i = 0; i < fileVersions.size(); i++) {
                TbFileVersion fileVersion = fileVersions.get(i);
                filePath = uploadPath + fileVersion.getFilepath();
                FileUtil.deleteFile(filePath);
            }

            // 数据库删除
            fileRepository.delete(file);
        }
        if (null != fileInfo.getFileversionid()) {
            //文件系统删除
            TbFileVersion fileVersion = fileVersionRepository.findByFileversionid(fileInfo.getFileversionid());
            String filePath = uploadPath + fileVersion.getFilepath();
            FileUtil.deleteFile(filePath);

            // 数据库删除
            fileVersionRepository.delete(fileVersion);
        }

    }


}
