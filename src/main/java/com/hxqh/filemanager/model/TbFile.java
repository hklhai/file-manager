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

    private static final long serialVersionUID = -4737417559965849634L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date editdate;

    private String filename;

    private String filepath;

    private String filerealname;

    private Float filesize;

    private Integer fileversion;

    private Integer userid;
    private String usersid;
    private String username;

    private String appname;
    private Integer recordid;
    private String recordsid;

    @JSONField(serialize = false)
    @OneToMany(mappedBy = "tbFile")
    private List<TbFileVersion> tbFileVersions;

    public TbFile() {
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

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Integer getFileid() {
        return this.fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getEditdate() {
        return this.editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
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

    public Float getFilesize() {
        return this.filesize;
    }

    public void setFilesize(Float filesize) {
        this.filesize = filesize;
    }

    public Integer getFileversion() {
        return this.fileversion;
    }

    public void setFileversion(Integer fileversion) {
        this.fileversion = fileversion;
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