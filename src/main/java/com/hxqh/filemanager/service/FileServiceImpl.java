package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ocean lin on 2018/10/30.
 *
 * @author Ocean lin
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserid(Integer userId) {
        return userRepository.findByUserid(userId);
    }
}
