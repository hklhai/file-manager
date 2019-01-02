package com.hxqh.filemanager.model.assist;

import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbPath;

import java.util.List;

/**
 * Created by Ocean lin on 2019/1/2.
 *
 * @author Ocean lin
 */
public class PathDto {
    private List<TbPath> pathList;
    private List<TbFile> fileList;

    public PathDto() {
    }

    public PathDto(List<TbPath> pathList, List<TbFile> fileList) {
        this.pathList = pathList;
        this.fileList = fileList;
    }

    public List<TbPath> getPathList() {
        return pathList;
    }

    public void setPathList(List<TbPath> pathList) {
        this.pathList = pathList;
    }

    public List<TbFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<TbFile> fileList) {
        this.fileList = fileList;
    }
}
