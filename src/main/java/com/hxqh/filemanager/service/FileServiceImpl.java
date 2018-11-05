package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.model.assist.FileDto;
import com.hxqh.filemanager.model.assist.FileVersionDto;
import com.hxqh.filemanager.repository.FileRepository;
import com.hxqh.filemanager.repository.FileVersionRepository;
import com.hxqh.filemanager.repository.UserRepository;
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
import java.util.List;

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
    public User findByUserid(Integer userId) {
        return userRepository.findByUserid(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveFile(MultipartFile file, String appname, Long userid) {
        // todo 1. 文件记录，2. 历史版本记录

        String filename;
        if (file.getOriginalFilename() != null && file.getSize() > 0) {
            File f = new File(uploadPath);

            // todo 是否需要 yyyy-mm路径的配置
            if (!f.exists()) {
                f.mkdirs();
            }
            filename = uploadPath + "/" + file.getOriginalFilename();
            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream(new File(filename));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFile(Long docinfoid) {
        // todo 数据库删除

        // todo 文件系统删除

    }


}
