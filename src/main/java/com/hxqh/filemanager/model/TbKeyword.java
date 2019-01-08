package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_keyword database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_keyword2")
public class TbKeyword implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer categoryid;

    private String keywordname;

    private Integer userid;

    private String username;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private String deptfullname;

    private String deptid;


    public TbKeyword() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getKeywordname() {
        return keywordname;
    }

    public void setKeywordname(String keywordname) {
        this.keywordname = keywordname;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDeptfullname() {
        return deptfullname;
    }

    public void setDeptfullname(String deptfullname) {
        this.deptfullname = deptfullname;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
}