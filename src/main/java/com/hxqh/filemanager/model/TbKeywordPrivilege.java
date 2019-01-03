package com.hxqh.filemanager.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tb_keyword_privilege database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_keyword_privilege2")
public class TbKeywordPrivilege implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer keywordid;

    private Integer userid;

    private String username;

    private Integer categoryid;

    private Integer filedelete;

    private Integer filedownload;

    private Integer fileduplicate;

    private Integer fileedit;

    private Integer fileprint;

    private Integer fileread;

    private Integer fileupload;


    public TbKeywordPrivilege() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(Integer keywordid) {
        this.keywordid = keywordid;
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

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getFiledelete() {
        return filedelete;
    }

    public void setFiledelete(Integer filedelete) {
        this.filedelete = filedelete;
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

    public Integer getFileread() {
        return fileread;
    }

    public void setFileread(Integer fileread) {
        this.fileread = fileread;
    }

    public Integer getFileupload() {
        return fileupload;
    }

    public void setFileupload(Integer fileupload) {
        this.fileupload = fileupload;
    }
}