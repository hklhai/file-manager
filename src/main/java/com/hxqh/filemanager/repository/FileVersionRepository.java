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


    /**
     * 根据md5值获取文件版本对象
     *
     * @param md5String
     * @return 文件版本对象列表
     */
    List<TbFileVersion> findByMd5(String md5String);

    /**
     * 根据应用关系获取文件版本对象
     *
     * @param fileRefer 引用表
     * @param fileid    引用ID
     * @return 文件版本对象列表
     */
    List<TbFileVersion> findByRefertabAndReferid(String fileRefer, Integer fileid);
}
