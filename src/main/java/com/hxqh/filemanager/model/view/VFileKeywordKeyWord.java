package com.hxqh.filemanager.model.view;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the tb_file_keyword database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "v_file_keyword_keyword")
public class VFileKeywordKeyWord {

    @Id
    private Integer filekeywordid;

    private Integer fileid;

    private Integer categoryid;

    private Integer keywordid;

    private String keywordname;

    public VFileKeywordKeyWord() {
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

    public Integer getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(Integer keywordid) {
        this.keywordid = keywordid;
    }

    public String getKeywordname() {
        return keywordname;
    }

    public void setKeywordname(String keywordname) {
        this.keywordname = keywordname;
    }
}
