package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_file database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_file")
public class TbFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileid;

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


    private String secretkey;

    /**
     * bi-directional many-to-one association to TbCurrentFileLog
     */
    @OneToMany(mappedBy = "tbFile", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JSONField(serialize = false)
    private List<TbCurrentFileLog> tbCurrentFileLogs;

    /**
     * bi-directional many-to-one association to TbPath
     */
    @ManyToOne
    @JoinColumn(name = "pathid")
    @JSONField(serialize = false)
    private TbPath tbPath;

    /**
     * bi-directional many-to-one association to TbFileKeyword
     */
    @OneToMany(mappedBy = "tbFile", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JSONField(serialize = false)
    private List<TbFileKeyword> tbFileKeywords;

    /**
     * bi-directional many-to-one association to TbFileLog
     */
    @OneToMany(mappedBy = "tbFile", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JSONField(serialize = false)
    private List<TbFileLog> tbFileLogs;

    /**
     * bi-directional many-to-one association to TbFileVersion
     */
    @OneToMany(mappedBy = "tbFile")
    @JSONField(serialize = false)
    private List<TbFileVersion> tbFileVersions;

    public TbFile() {
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public Integer getFileid() {
        return this.fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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

    public List<TbCurrentFileLog> getTbCurrentFileLogs() {
        return this.tbCurrentFileLogs;
    }

    public void setTbCurrentFileLogs(List<TbCurrentFileLog> tbCurrentFileLogs) {
        this.tbCurrentFileLogs = tbCurrentFileLogs;
    }

    public TbCurrentFileLog addTbCurrentFileLog(TbCurrentFileLog tbCurrentFileLog) {
        getTbCurrentFileLogs().add(tbCurrentFileLog);
        tbCurrentFileLog.setTbFile(this);

        return tbCurrentFileLog;
    }

    public TbCurrentFileLog removeTbCurrentFileLog(TbCurrentFileLog tbCurrentFileLog) {
        getTbCurrentFileLogs().remove(tbCurrentFileLog);
        tbCurrentFileLog.setTbFile(null);

        return tbCurrentFileLog;
    }

    public TbPath getTbPath() {
        return this.tbPath;
    }

    public void setTbPath(TbPath tbPath) {
        this.tbPath = tbPath;
    }

    public List<TbFileKeyword> getTbFileKeywords() {
        return this.tbFileKeywords;
    }

    public void setTbFileKeywords(List<TbFileKeyword> tbFileKeywords) {
        this.tbFileKeywords = tbFileKeywords;
    }

    public TbFileKeyword addTbFileKeyword(TbFileKeyword tbFileKeyword) {
        getTbFileKeywords().add(tbFileKeyword);
        tbFileKeyword.setTbFile(this);

        return tbFileKeyword;
    }

    public TbFileKeyword removeTbFileKeyword(TbFileKeyword tbFileKeyword) {
        getTbFileKeywords().remove(tbFileKeyword);
        tbFileKeyword.setTbFile(null);

        return tbFileKeyword;
    }

    public List<TbFileLog> getTbFileLogs() {
        return this.tbFileLogs;
    }

    public void setTbFileLogs(List<TbFileLog> tbFileLogs) {
        this.tbFileLogs = tbFileLogs;
    }

    public TbFileLog addTbFileLog(TbFileLog tbFileLog) {
        getTbFileLogs().add(tbFileLog);
        tbFileLog.setTbFile(this);

        return tbFileLog;
    }

    public TbFileLog removeTbFileLog(TbFileLog tbFileLog) {
        getTbFileLogs().remove(tbFileLog);
        tbFileLog.setTbFile(null);

        return tbFileLog;
    }

    public List<TbFileVersion> getTbFileVersions() {
        return this.tbFileVersions;
    }

    public void setTbFileVersions(List<TbFileVersion> tbFileVersions) {
        this.tbFileVersions = tbFileVersions;
    }

    public TbFileVersion addTbFileVersion(TbFileVersion tbFileVersion) {
        getTbFileVersions().add(tbFileVersion);
        tbFileVersion.setTbFile(this);

        return tbFileVersion;
    }

    public TbFileVersion removeTbFileVersion(TbFileVersion tbFileVersion) {
        getTbFileVersions().remove(tbFileVersion);
        tbFileVersion.setTbFile(null);

        return tbFileVersion;
    }

}