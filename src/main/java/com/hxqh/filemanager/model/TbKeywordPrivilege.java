package com.hxqh.filemanager.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tb_keyword_privilege database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_keyword_privilege")
public class TbKeywordPrivilege implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer keywordprivilegeid;

    private Integer filedelete;

    private Integer filedownload;

    private Integer fileduplicate;

    private Integer fileedit;

    private Integer fileprint;

    private Integer fileread;

    private Integer fileupload;

    private Integer userid;

    private String username;

    /**
     * bi-directional many-to-one association to TbKeyword
     */
    @ManyToOne
    @JoinColumn(name = "keywordid")
    private TbKeyword tbKeyword;

    public TbKeywordPrivilege() {
    }

    public Integer getKeywordprivilegeid() {
        return this.keywordprivilegeid;
    }

    public void setKeywordprivilegeid(int keywordprivilegeid) {
        this.keywordprivilegeid = keywordprivilegeid;
    }

    public Integer getFiledelete() {
        return this.filedelete;
    }

    public void setFiledelete(int filedelete) {
        this.filedelete = filedelete;
    }

    public Integer getFiledownload() {
        return this.filedownload;
    }

    public void setFiledownload(int filedownload) {
        this.filedownload = filedownload;
    }

    public Integer getFileduplicate() {
        return this.fileduplicate;
    }

    public void setFileduplicate(int fileduplicate) {
        this.fileduplicate = fileduplicate;
    }

    public Integer getFileedit() {
        return this.fileedit;
    }

    public void setFileedit(int fileedit) {
        this.fileedit = fileedit;
    }

    public Integer getFileprint() {
        return this.fileprint;
    }

    public void setFileprint(int fileprint) {
        this.fileprint = fileprint;
    }

    public Integer getFileread() {
        return this.fileread;
    }

    public void setFileread(int fileread) {
        this.fileread = fileread;
    }

    public Integer getFileupload() {
        return this.fileupload;
    }

    public void setFileupload(int fileupload) {
        this.fileupload = fileupload;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TbKeyword getTbKeyword() {
        return this.tbKeyword;
    }

    public void setTbKeyword(TbKeyword tbKeyword) {
        this.tbKeyword = tbKeyword;
    }

}