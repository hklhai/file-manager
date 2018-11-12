package com.hxqh.filemanager.repository;


import com.hxqh.filemanager.model.TbFileVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ocean lin on 2018/11/5.
 *
 * @author Ocean lin
 */
@Repository
public interface FileVersionRepository extends JpaRepository<TbFileVersion, Integer>, JpaSpecificationExecutor<TbFileVersion> {
    /**
     * 根据id获取FileVersion
     *
     * @param fileversionid FileVersion主键
     * @return TbFileVersion实体类
     */
    TbFileVersion findByFileversionid(Integer fileversionid);

    List<TbFileVersion> findByMd5(String md5String);

    List<TbFileVersion> findByRefertabAndReferid(String fileRefer, Integer fileid);
}
