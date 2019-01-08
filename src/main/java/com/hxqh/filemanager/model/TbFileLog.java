package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

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

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date operatetime;

    private String operatetype;

    private Integer userid;

    private String username;

    @Transient
    private Integer fileid;

    /**
     * bi-directional many-to-one association to TbFile
     */
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "fileid")
    private TbFile tbFile;

    public TbFileLog() {
    }

    public Integer getFilelogid() {
        return filelogid;
    }

    public void setFilelogid(Integer filelogid) {
        this.filelogid = filelogid;
    }

    public String getDeptfullname() {
        return deptfullname;
    }

    public void setDeptfullname(String deptfullname) {
        this.deptfullname = deptfullname;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getOperatecount() {
        return operatecount;
    }

    public void setOperatecount(Integer operatecount) {
        this.operatecount = operatecount;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype;
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

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public TbFile getTbFile() {
        return tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }
}