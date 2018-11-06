package com.hxqh.filemanager.model.assist;

/**
 * Created by Ocean lin on 2018/11/6.
 *
 * @author Ocean lin
 */
public class FileInfo {
    private  String appname;
    private Long userid;
    private String usersid;
    private Long recordid;
    private String recordsid;


    public FileInfo() {
    }

    public FileInfo(String appname, Long userid, String usersid, Long recordid, String recordsid) {
        this.appname = appname;
        this.userid = userid;
        this.usersid = usersid;
        this.recordid = recordid;
        this.recordsid = recordsid;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public String getRecordsid() {
        return recordsid;
    }

    public void setRecordsid(String recordsid) {
        this.recordsid = recordsid;
    }
}
