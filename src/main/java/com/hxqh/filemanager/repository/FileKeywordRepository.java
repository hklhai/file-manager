package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.TbFileKeyword;
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
public interface FileKeywordRepository  extends JpaRepository<TbFileKeyword, Integer> {

    /**
     *
     * @param fileid
     * @return
     */
    @Query("select u from TbFileKeyword u where u.tbFile.fileid=?1")
    List<TbFileKeyword> findByFileId(Integer fileid);
}
