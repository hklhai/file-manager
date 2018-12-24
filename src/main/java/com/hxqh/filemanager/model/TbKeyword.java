package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_keyword database table.
 * 
 */
@Entity
@Table(name="tb_keyword")
public class TbKeyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer keywordid;

	private Integer categoryid;

	private Date createtime;

	private String deptfullname;

	private String deptid;

	private String keywordname;

	private Integer userid;

	private String username;

	//bi-directional many-to-one association to TbFileKeyword
	@OneToMany(mappedBy="tbKeyword")
	@JSONField(serialize = false)
	private List<TbFileKeyword> tbFileKeywords;

	//bi-directional many-to-one association to TbKeywordPrivilege
	@OneToMany(mappedBy="tbKeyword")
	@JSONField(serialize = false)
	private List<TbKeywordPrivilege> tbKeywordPrivileges;

	public TbKeyword() {
	}

	public Integer getKeywordid() {
		return this.keywordid;
	}

	public void setKeywordid(int keywordid) {
		this.keywordid = keywordid;
	}

	public Integer getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDeptfullname() {
		return this.deptfullname;
	}

	public void setDeptfullname(String deptfullname) {
		this.deptfullname = deptfullname;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getKeywordname() {
		return this.keywordname;
	}

	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
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
		tbFileKeyword.setTbKeyword(this);

		return tbFileKeyword;
	}

	public TbFileKeyword removeTbFileKeyword(TbFileKeyword tbFileKeyword) {
		getTbFileKeywords().remove(tbFileKeyword);
		tbFileKeyword.setTbKeyword(null);

		return tbFileKeyword;
	}

	public List<TbKeywordPrivilege> getTbKeywordPrivileges() {
		return this.tbKeywordPrivileges;
	}

	public void setTbKeywordPrivileges(List<TbKeywordPrivilege> tbKeywordPrivileges) {
		this.tbKeywordPrivileges = tbKeywordPrivileges;
	}

	public TbKeywordPrivilege addTbKeywordPrivilege(TbKeywordPrivilege tbKeywordPrivilege) {
		getTbKeywordPrivileges().add(tbKeywordPrivilege);
		tbKeywordPrivilege.setTbKeyword(this);

		return tbKeywordPrivilege;
	}

	public TbKeywordPrivilege removeTbKeywordPrivilege(TbKeywordPrivilege tbKeywordPrivilege) {
		getTbKeywordPrivileges().remove(tbKeywordPrivilege);
		tbKeywordPrivilege.setTbKeyword(null);

		return tbKeywordPrivilege;
	}

}