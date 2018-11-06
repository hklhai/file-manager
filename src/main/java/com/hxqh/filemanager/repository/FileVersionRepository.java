package com.hxqh.filemanager.repository;


import com.hxqh.filemanager.model.TbFileVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Ocean lin on 2018/11/5.
 *
 * @author Ocean lin
 */
@Repository
public interface FileVersionRepository extends JpaRepository<TbFileVersion, Integer>, JpaSpecificationExecutor<TbFileVersion> {
    TbFileVersion findByFileversionid(Integer fid);
}
