package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.TbCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ocean lin on 2018/12/24.
 *
 * @author Ocean lin
 */
@Repository
public interface CategoryRepository extends JpaRepository<TbCategory, Integer> {

    @Query("select u from TbCategory u where u.status='0' ")
    List<TbCategory> findByStatus();
}
