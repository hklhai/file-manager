package com.hxqh.filemanager.model.assist;

/**
 * Created by Ocean lin on 2019/2/24.
 *
 * @author Ocean lin
 */
public class FileIdSize {

    private Integer fileid;

    private Float filesize;


    private String webUrl;

    public FileIdSize() {
    }

    public FileIdSize(Integer fileid, Float filesize, String webUrl) {
        this.fileid = fileid;
        this.filesize = filesize;
        this.webUrl = webUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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
