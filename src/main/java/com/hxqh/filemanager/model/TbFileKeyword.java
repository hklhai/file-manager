package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tb_file_keyword database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_file_keyword")
public class TbFileKeyword implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer filekeywordid;

    /**
     * bi-directional many-to-one association to TbFile
     */
    @JSONField(serialize=false)
    @ManyToOne
    @JoinColumn(name = "fileid")
    private TbFile tbFile;

    private Integer categoryid;

    private Integer keywordid;


    public TbFileKeyword() {
    }

    public void setFilekeywordid(Integer filekeywordid) {
        this.filekeywordid = filekeywordid;
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

    public Integer getFilekeywordid() {
        return this.filekeywordid;
    }

    public void setFilekeywordid(int filekeywordid) {
        this.filekeywordid = filekeywordid;
    }


    public TbFile getTbFile() {
        return this.tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }


}