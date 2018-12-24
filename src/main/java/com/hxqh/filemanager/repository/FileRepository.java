package com.hxqh.filemanager.repository;


import com.hxqh.filemanager.model.TbFile;
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
public interface FileRepository extends JpaRepository<TbFile, Integer>, JpaSpecificationExecutor<TbFile> {
    /**
     * 根据id获取File
     *
     * @param fid File主键
     * @return File对象
     */
    TbFile findByFileid(Integer fid);

    /**
     * 根据md5值获取文件对象
     *
     * @param md5String
     * @return 文件对象列表
     */
    List<TbFile> findByMd5(String md5String);

    /**
     * 根据应用关系获取文件对象
     *
     * @param fileRefer 引用表
     * @param fileid    引用ID
     * @return 文件对象列表
     */
    List<TbFile> findByRefertabAndReferid(String fileRefer, Integer fileid);
}
