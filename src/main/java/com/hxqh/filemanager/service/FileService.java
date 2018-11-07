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
     * @param files
     * @param fileInfo
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
     * @param file
     * @param pageable
     * @return
     */
    FileDto fileList(TbFile file, Pageable pageable);

    /**
     * 文件版本列表带条件的分页获取接口
     *
     * @param fileVersion
     * @param pageable
     * @return
     */
    FileVersionDto fileVersionList(TbFileVersion fileVersion, Pageable pageable);

    TbFile findByFileid(Integer fid);

    TbFileVersion findByFileversionid(Integer fid);
}
