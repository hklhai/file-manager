package com.hxqh.filemanager.model.assist;

import com.hxqh.filemanager.model.TbPath;

import java.util.List;

/**
 * Created by Ocean lin on 2019/1/2.
 *
 * @author Ocean lin
 */
public class PathDto {
    private List<TbPath> pathList;
    FileDto fileList;

    public PathDto() {
    }

    public PathDto(List<TbPath> pathList, FileDto fileList) {
        this.pathList = pathList;
        this.fileList = fileList;
    }

    public List<TbPath> getPathList() {
        return pathList;
    }

    public void setPathList(List<TbPath> pathList) {
        this.pathList = pathList;
    }

    public FileDto getFileList() {
        return fileList;
    }

    public void setFileList(FileDto fileList) {
        this.fileList = fileList;
    }
}
