package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.User;

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

}
