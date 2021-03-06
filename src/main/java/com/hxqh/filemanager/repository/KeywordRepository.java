package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.TbCategory;
import com.hxqh.filemanager.model.TbKeyword;
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
public interface KeywordRepository extends JpaRepository<TbKeyword, Integer> {

    @Query("select u from TbKeyword u where u.status='0' ")
    List<TbKeyword> findByStatus();

}
