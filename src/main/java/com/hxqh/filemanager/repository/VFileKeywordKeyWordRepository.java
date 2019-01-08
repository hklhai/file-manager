package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.view.VFileKeywordKeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VFileKeywordKeyWordRepository  extends JpaRepository<VFileKeywordKeyWord, Integer> {
}
