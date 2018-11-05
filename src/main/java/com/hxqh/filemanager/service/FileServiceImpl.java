package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;

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
