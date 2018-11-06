package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.model.assist.FileDto;
import com.hxqh.filemanager.model.assist.FileInfo;
import com.hxqh.filemanager.model.assist.FileVersionDto;
import com.hxqh.filemanager.repository.FileRepository;
import com.hxqh.filemanager.repository.FileVersionRepository;
import com.hxqh.filemanager.repository.UserRepository;
import com.hxqh.filemanager.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileVersionRepository fileVersionRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FileDto fileList(TbFile file, Pageable pageable) {
        Specification<TbFile> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>(5);

            if (StringUtils.isNotBlank(file.getFilerealname())) {
                list.add(cb.like(root.get("filerealname").as(String.class), "%" + file.getFilerealname() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };

        Page<TbFile> files = fileRepository.findAll(specification, pageable);

        List<TbFile> fileList = files.getContent();
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
    public User findByUserid(Integer userId) {
        return userRepository.findByUserid(userId);
    }

    /**
     * 保存文件
     *
     * @param file     文件
     * @param fileInfo 文件信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveFile(MultipartFile file, FileInfo fileInfo) throws Exception {

        String filePath, savePath;
        if (file.getOriginalFilename() != null && file.getSize() > 0) {

            File f = new File(uploadPath + "/" + DateUtils.getTodayMonth());

            if (!f.exists()) {
                f.mkdirs();
            }
            String extensionName = file.getOriginalFilename().split("\\.")[1];

            savePath = "/" + DateUtils.getTodayMonth() + "/" + DateUtils.getTodayTime() + "_" + UUID.randomUUID()
                    +"."+ extensionName;
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
            // 保存文件信息
            TbFile tbFile = new TbFile();

            tbFile.setAppname(fileInfo.getAppname());
            tbFile.setUserid(fileInfo.getUserid().intValue());
            tbFile.setUsersid(fileInfo.getUsersid());
            tbFile.setRecordid(fileInfo.getRecordid().intValue());
            tbFile.setRecordsid(fileInfo.getRecordsid());

            tbFile.setFilerealname(file.getOriginalFilename());
            Double fileSize = file.getSize() * 1.0 / THOUSAND / THOUSAND;
            tbFile.setFilesize(fileSize.floatValue());
            tbFile.setCreatedate(new Date());
            tbFile.setEditdate(new Date());
            // todo 判断是否存在历史版本
            tbFile.setFileversion(1);
            tbFile.setFilepath(savePath);

            fileRepository.save(tbFile);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFile(Long docinfoid) {
        // todo 数据库删除

        // todo 文件系统删除

    }


}
