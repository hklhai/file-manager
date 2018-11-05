package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Ocean lin on 2018/10/30.
 *
 * @author Ocean lin
 */
public interface FileService {

    /**
     * 获取用户对象
     *
     * @param userId user主键
     * @return 用户实体类
     */
    User findByUserid(Integer userId);


    /**
     * 保存文件
     *
     * @param files
     * @param appname
     * @param userid
     */
    void saveFile(MultipartFile files, String appname, Long userid);

    /**
     *  删除文件
     *
     * @param docinfoid
     */
    void deleteFile(Long docinfoid);
}
