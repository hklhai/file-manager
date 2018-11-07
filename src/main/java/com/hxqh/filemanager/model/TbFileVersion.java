package com.hxqh.filemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_file_version database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_file_version")
public class TbFileVersion implements Serializable {

    private static final long serialVersionUID = -953280945409228673L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileversionid;

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

    private String username;

    private String usersid;

    private String appname;

    private Integer recordid;

    private String recordsid;

    @Transient
    private Integer fileid;

    @ManyToOne
    @JoinColumn(name = "fileid")
    private TbFile tbFile;

    public TbFileVersion() {
    }

    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
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

    public Integer getFileversionid() {
        return this.fileversionid;
    }

    public void setFileversionid(Integer fileversionid) {
        this.fileversionid = fileversionid;
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

    public TbFile getTbFile() {
        return this.tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }

}