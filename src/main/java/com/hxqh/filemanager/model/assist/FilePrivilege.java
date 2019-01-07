package com.hxqh.filemanager.model.assist;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Lin
 */
@Entity
public class FilePrivilege {

    @Id
    private Integer filekeywordid;
    private Integer fileid;
    private Integer categoryid;
    private Integer categoryid2;
    private Integer userid;
    private Integer fileread = 0;
    private Integer fileedit = 0;
    private Integer fileprint = 0;
    private Integer fileupload = 0;
    private Integer filedownload = 0;
    private Integer fileduplicate = 0;
    private Integer filedelete = 0;

    public FilePrivilege() {
    }

    public Integer getFilekeywordid() {
        return filekeywordid;
    }

    public void setFilekeywordid(Integer filekeywordid) {
        this.filekeywordid = filekeywordid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getCategoryid2() {
        return categoryid2;
    }

    public void setCategoryid2(Integer categoryid2) {
        this.categoryid2 = categoryid2;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFileread() {
        return fileread;
    }

    public void setFileread(Integer fileread) {
        this.fileread = fileread;
    }

    public Integer getFileedit() {
        return fileedit;
    }

    public void setFileedit(Integer fileedit) {
        this.fileedit = fileedit;
    }

    public Integer getFileprint() {
        return fileprint;
    }

    public void setFileprint(Integer fileprint) {
        this.fileprint = fileprint;
    }

    public Integer getFileupload() {
        return fileupload;
    }

    public void setFileupload(Integer fileupload) {
        this.fileupload = fileupload;
    }

    public Integer getFiledownload() {
        return filedownload;
    }

    public void setFiledownload(Integer filedownload) {
        this.filedownload = filedownload;
    }

    public Integer getFileduplicate() {
        return fileduplicate;
    }

    public void setFileduplicate(Integer fileduplicate) {
        this.fileduplicate = fileduplicate;
    }

    public Integer getFiledelete() {
        return filedelete;
    }

    public void setFiledelete(Integer filedelete) {
        this.filedelete = filedelete;
    }
}
