package com.hxqh.filemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Ocean lin on 2018/11/5.
 *
 * @author Ocean lin
 */
@Entity
@Table(name = "tb_file")
public class File implements Serializable {

    private static final long serialVersionUID = -1083261538760251762L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileid;
    private String filename;
    private String filerealname;
    private Double filesize;
    private Integer userid;
    private String username;
    private Date createdate;
    private Date editdate;
    private Integer fileversion;
    private String filepath;

    @Basic
    @Column(name = "fileid", nullable = true)
    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    @Basic
    @Column(name = "filename", nullable = true, length = 150)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "filerealname", nullable = true, length = 150)
    public String getFilerealname() {
        return filerealname;
    }

    public void setFilerealname(String filerealname) {
        this.filerealname = filerealname;
    }

    @Basic
    @Column(name = "filesize", nullable = true, precision = 2)
    public Double getFilesize() {
        return filesize;
    }

    public void setFilesize(Double filesize) {
        this.filesize = filesize;
    }

    @Basic
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "createdate", nullable = true)
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "editdate", nullable = true)
    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }

    @Basic
    @Column(name = "fileversion", nullable = true)
    public Integer getFileversion() {
        return fileversion;
    }

    public void setFileversion(Integer fileversion) {
        this.fileversion = fileversion;
    }

    @Basic
    @Column(name = "filepath", nullable = true, length = 255)
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File that = (File) o;
        return Objects.equals(fileid, that.fileid) &&
                Objects.equals(filename, that.filename) &&
                Objects.equals(filerealname, that.filerealname) &&
                Objects.equals(filesize, that.filesize) &&
                Objects.equals(userid, that.userid) &&
                Objects.equals(username, that.username) &&
                Objects.equals(createdate, that.createdate) &&
                Objects.equals(editdate, that.editdate) &&
                Objects.equals(fileversion, that.fileversion) &&
                Objects.equals(filepath, that.filepath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fileid, filename, filerealname, filesize, userid, username, createdate, editdate, fileversion, filepath);
    }
}
