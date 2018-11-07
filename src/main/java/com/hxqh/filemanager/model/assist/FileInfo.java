package com.hxqh.filemanager.model.assist;

/**
 * Created by Ocean lin on 2018/11/6.
 *
 * @author Ocean lin
 */
public class FileInfo {
    private String appname;
    private Integer userid;
    private String usersid;
    private String username;
    private Integer recordid;
    private String recordsid;

    private Integer fileid;
    private Integer fileversionid;

    public FileInfo() {
    }

    public FileInfo(String appname, Integer userid, String usersid, String username, Integer recordid, String recordsid) {
        this.appname = appname;
        this.userid = userid;
        this.usersid = usersid;
        this.username = username;
        this.recordid = recordid;
        this.recordsid = recordsid;
    }

    public Integer getFileversionid() {
        return fileversionid;
    }

    public void setFileversionid(Integer fileversionid) {
        this.fileversionid = fileversionid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getRecordsid() {
        return recordsid;
    }

    public void setRecordsid(String recordsid) {
        this.recordsid = recordsid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }
}
