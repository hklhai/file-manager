package com.hxqh.filemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_file_log database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_file_log")
public class TbFileLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer filelogid;

    private String deptfullname;

    private Integer deptid;

    private Integer operatecount;

    private Date operatetime;

    private String operatetype;

    private Integer userid;

    private String username;

    /**
     * bi-directional many-to-one association to TbFile
     */
    @ManyToOne
    @JoinColumn(name = "fileid")
    private TbFile tbFile;

    public TbFileLog() {
    }

    public Integer getFilelogid() {
        return this.filelogid;
    }

    public void setFilelogid(int filelogid) {
        this.filelogid = filelogid;
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

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public Integer getOperatecount() {
        return this.operatecount;
    }

    public void setOperatecount(int operatecount) {
        this.operatecount = operatecount;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getOperatetype() {
        return this.operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype;
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

    public TbFile getTbFile() {
        return this.tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }

}