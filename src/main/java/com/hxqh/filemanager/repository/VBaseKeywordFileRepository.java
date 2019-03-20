package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.assist.VBaseKeywordFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VBaseKeywordFileRepository extends JpaRepository<VBaseKeywordFile, Integer>, JpaSpecificationExecutor<VBaseKeywordFile> {
}
