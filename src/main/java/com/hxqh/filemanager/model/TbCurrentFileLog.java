package com.hxqh.filemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_current_file_log database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_current_file_log")
public class TbCurrentFileLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentfilelogid;

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

    public TbCurrentFileLog() {
    }

    public Integer getCurrentfilelogid() {
        return currentfilelogid;
    }

    public void setCurrentfilelogid(Integer currentfilelogid) {
        this.currentfilelogid = currentfilelogid;
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

    public TbFile getTbFile() {
        return tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }

    @Override
    public String toString() {
        return "TbCurrentFileLog{" +
                "currentfilelogid=" + currentfilelogid +
                ", deptfullname='" + deptfullname + '\'' +
                ", deptid=" + deptid +
                ", operatecount=" + operatecount +
                ", operatetime=" + operatetime +
                ", operatetype='" + operatetype + '\'' +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", tbFile=" + tbFile +
                '}';
    }
}