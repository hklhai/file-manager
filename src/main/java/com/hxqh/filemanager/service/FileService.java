package com.hxqh.filemanager.service;

import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.model.assist.FileDto;
import com.hxqh.filemanager.model.assist.FileInfo;
import com.hxqh.filemanager.model.assist.FileVersionDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Ocean lin on 2018/10/30.
 *
 * @author Ocean lin
 */
public interface FileService {

    /**
     * 获取用户对象
     *
     * @param userId user主键
     * @return 用户实体类
     */
    User findByUserid(Integer userId);


    /**
     * 保存文件
     *
     * @param files    上传文件
     * @param fileInfo 上传文件信息
     * @throws Exception
     */
    void saveFile(MultipartFile files, FileInfo fileInfo) throws Exception;

    /**
     * 删除文件
     *
     * @param fileInfo
     */
    void deleteFile(FileInfo fileInfo);

    /**
     * 文件列表带条件的分页获取接口
     *
     * @param file     文件实体类
     * @param pageable 分页类
     * @return 文件DTO
     */
    FileDto fileList(TbFile file, Pageable pageable);

    /**
     * 文件版本列表带条件的分页获取接口
     *
     * @param fileVersion 文件版本实体类
     * @param pageable    分页类
     * @return 文件版本DTO
     */
    FileVersionDto fileVersionList(TbFileVersion fileVersion, Pageable pageable);

    /**
     * 根据fileid查询File
     *
     * @param fileid 文件主键
     * @return 文件实体类
     */
    TbFile findByFileid(Integer fileid);

    /**
     * 根据fileversionid查询FileVersion
     *
     * @param fileversionid FileVersion主键
     * @return 文件版本实体类
     */
    TbFileVersion findByFileversionid(Integer fileversionid);
}
