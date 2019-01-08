package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.view.VFileKeywordKeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VFileKeywordKeyWordRepository extends JpaRepository<VFileKeywordKeyWord, Integer> {
    /**
     * @param fileid
     * @return
     */
    @Query("select u from VFileKeywordKeyWord u where u.fileid=?1 ")
    List<VFileKeywordKeyWord> findByFileid(Integer fileid);
}
