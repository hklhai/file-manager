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
    private String filerealname;
    private String filename;
    private Integer categoryid;
    private Integer keywordid;
    private Integer categoryid2;
    private Integer keywordid2;
    private Integer userid;
    private Integer fileread;
    private Integer fileedit;
    private Integer fileprint;
    private Integer fileupload;
    private Integer filedownload;
    private Integer fileduplicate;
    private Integer filedelete;

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

    public String getFilerealname() {
        return filerealname;
    }

    public void setFilerealname(String filerealname) {
        this.filerealname = filerealname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(Integer keywordid) {
        this.keywordid = keywordid;
    }

    public Integer getCategoryid2() {
        return categoryid2;
    }

    public void setCategoryid2(Integer categoryid2) {
        this.categoryid2 = categoryid2;
    }

    public Integer getKeywordid2() {
        return keywordid2;
    }

    public void setKeywordid2(Integer keywordid2) {
        this.keywordid2 = keywordid2;
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

    @Override
    public String toString() {
        return "FilePrivilege{" +
                "fileid=" + fileid +
                ", filerealname='" + filerealname + '\'' +
                ", filename='" + filename + '\'' +
                ", categoryid=" + categoryid +
                ", keywordid=" + keywordid +
                ", categoryid2=" + categoryid2 +
                ", keywordid2=" + keywordid2 +
                ", userid=" + userid +
                ", fileread=" + fileread +
                ", fileedit=" + fileedit +
                ", fileprint=" + fileprint +
                ", fileupload=" + fileupload +
                ", filedownload=" + filedownload +
                ", fileduplicate=" + fileduplicate +
                ", filedelete=" + filedelete +
                '}';
    }
}
