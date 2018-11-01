package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ocean lin on 2018/10/30.
 *
 * @author Ocean lin
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 查询用户
     *
     * @param userId 用户主键
     * @return User对象
     */
    User findByUserid(Integer userId);
}
