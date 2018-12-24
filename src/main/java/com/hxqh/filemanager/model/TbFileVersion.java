package com.hxqh.filemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_file_version database table.
 */
@Entity
@Table(name = "tb_file_version")
public class TbFileVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer fileversionid;

    private Integer appid;

    private String appname;

    private String deptfullname;

    private Integer deptid;

    private String extensionname;

    private String filename;

    private String filepath;

    private String filerealname;

    private Float filesize;

    private String filestatus;

    private Integer fileversion;

    private Date invaliddate;

    private String md5;

    private Integer pathid;

    private Integer recordid;

    private Integer referid;

    private String refertab;

    private Date statustime;

    private Date uploadtime;

    private Integer userid;

    private String username;

    private Date validdate;

    @Transient
    private String webUrl;

    @Transient
    private Integer fileid;

    //bi-directional many-to-one association to TbFile
    @ManyToOne
    @JoinColumn(name = "fileid")
    private TbFile tbFile;

    public TbFileVersion() {
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

    public Integer getFileversionid() {
        return this.fileversionid;
    }

    public void setFileversionid(Integer fileversionid) {
        this.fileversionid = fileversionid;
    }

    public Integer getAppid() {
        return this.appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getAppname() {
        return this.appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getDeptfullname() {
        return this.deptfullname;
    }

    public void setDeptfullname(String deptfullname) {
        this.deptfullname = deptfullname;
    }

    public Integer getDeptid() {
        return this.deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getExtensionname() {
        return this.extensionname;
    }

    public void setExtensionname(String extensionname) {
        this.extensionname = extensionname;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilerealname() {
        return this.filerealname;
    }

    public void setFilerealname(String filerealname) {
        this.filerealname = filerealname;
    }


    public String getFilestatus() {
        return this.filestatus;
    }

    public void setFilestatus(String filestatus) {
        this.filestatus = filestatus;
    }

    public Integer getFileversion() {
        return this.fileversion;
    }

    public void setFileversion(Integer fileversion) {
        this.fileversion = fileversion;
    }

    public Date getInvaliddate() {
        return this.invaliddate;
    }

    public void setInvaliddate(Date invaliddate) {
        this.invaliddate = invaliddate;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getPathid() {
        return this.pathid;
    }

    public void setPathid(Integer pathid) {
        this.pathid = pathid;
    }

    public Integer getRecordid() {
        return this.recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getReferid() {
        return this.referid;
    }

    public void setReferid(Integer referid) {
        this.referid = referid;
    }

    public String getRefertab() {
        return this.refertab;
    }

    public void setRefertab(String refertab) {
        this.refertab = refertab;
    }

    public Float getFilesize() {
        return filesize;
    }

    public void setFilesize(Float filesize) {
        this.filesize = filesize;
    }

    public Date getStatustime() {
        return statustime;
    }

    public void setStatustime(Date statustime) {
        this.statustime = statustime;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getValiddate() {
        return this.validdate;
    }

    public void setValiddate(Date validdate) {
        this.validdate = validdate;
    }

    public TbFile getTbFile() {
        return this.tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }

}