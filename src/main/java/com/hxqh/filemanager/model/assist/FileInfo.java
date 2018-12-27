package com.hxqh.filemanager.model.assist;

/**
 * Created by Ocean lin on 2018/11/6.
 *
 * @author Ocean lin
 */
public class FileInfo {
    private Integer userid;
    private String username;
    private Integer deptid;
    private String deptfullname;
    private Integer appid;
    private String appname;
    private Integer recordid;

    private Integer fileid;
    private Integer fileversionid;

    private Integer pathid;

    public FileInfo() {
    }

    public Integer getPathid() {
        return pathid;
    }

    public void setPathid(Integer pathid) {
        this.pathid = pathid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getDeptfullname() {
        return deptfullname;
    }

    public void setDeptfullname(String deptfullname) {
        this.deptfullname = deptfullname;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }


    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Integer getFileversionid() {
        return fileversionid;
    }

    public void setFileversionid(Integer fileversionid) {
        this.fileversionid = fileversionid;
    }
}
