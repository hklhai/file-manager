package com.hxqh.filemanager.model.base;

/**
 * Created by Ocean lin on 2019/2/20.
 *
 * @author Ocean lin
 */
public class MessageFile extends Message {

    private Integer fileid;

    private Float filesize;

    public MessageFile() {
    }

    public MessageFile(int code, String message) {
        super(code, message);
    }

    public MessageFile(int code, String message, Integer fileid, Float filesize) {
        super(code, message);
        this.fileid = fileid;
        this.filesize = filesize;
    }

    public MessageFile(int code, String message, boolean success) {
        super(code, message, success);
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
