package com.hxqh.filemanager.model.assist;

/**
 * @author Lin
 */
public class FilePrivilegeDto {

    private Integer userid;
    private Integer fileid;

    public FilePrivilegeDto() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }
}
