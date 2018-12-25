package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the tb_category database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_category")
public class TbCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryid;

    private String categoryname;

    private Time createtime;

    private String deptfullname;

    private Integer deptid;

    private Integer userid;

    private String username;

    /**
     * bi-directional many-to-one association to TbFileKeyword
     */
    @OneToMany(mappedBy = "tbCategory")
    @JSONField(serialize = false)
    private List<TbFileKeyword> tbFileKeywords;

    public TbCategory() {
    }

    public Integer getCategoryid() {
        return this.categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return this.categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Time getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Time createtime) {
        this.createtime = createtime;
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

    public List<TbFileKeyword> getTbFileKeywords() {
        return this.tbFileKeywords;
    }

    public void setTbFileKeywords(List<TbFileKeyword> tbFileKeywords) {
        this.tbFileKeywords = tbFileKeywords;
    }

    public TbFileKeyword addTbFileKeyword(TbFileKeyword tbFileKeyword) {
        getTbFileKeywords().add(tbFileKeyword);
        tbFileKeyword.setTbCategory(this);

        return tbFileKeyword;
    }

    public TbFileKeyword removeTbFileKeyword(TbFileKeyword tbFileKeyword) {
        getTbFileKeywords().remove(tbFileKeyword);
        tbFileKeyword.setTbCategory(null);

        return tbFileKeyword;
    }

}