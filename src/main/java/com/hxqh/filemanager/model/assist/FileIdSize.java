package com.hxqh.filemanager.model.assist;

/**
 * Created by Ocean lin on 2019/2/24.
 *
 * @author Ocean lin
 */
public class FileIdSize {

    private Integer fileid;

    private Float filesize;

    public FileIdSize() {
    }

    public FileIdSize(Integer fileid, Float filesize) {
        this.fileid = fileid;
        this.filesize = filesize;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Float getFilesize() {
        return filesize;
    }

    public void setFilesize(Float filesize) {
        this.filesize = filesize;
    }
}
