package com.hxqh.filemanager.model;

import java.io.Serializable;
import javax.persistence.*;


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
     * bi-directional many-to-one association to TbCategory
     */
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private TbCategory tbCategory;

    /**
     * bi-directional many-to-one association to TbFile
     */
    @ManyToOne
    @JoinColumn(name = "fileid")
    private TbFile tbFile;

    /**
     * bi-directional many-to-one association to TbKeyword
     */
    @ManyToOne
    @JoinColumn(name = "keywordid")
    private TbKeyword tbKeyword;

    public TbFileKeyword() {
    }

    public Integer getFilekeywordid() {
        return this.filekeywordid;
    }

    public void setFilekeywordid(int filekeywordid) {
        this.filekeywordid = filekeywordid;
    }

    public TbCategory getTbCategory() {
        return this.tbCategory;
    }

    public void setTbCategory(TbCategory tbCategory) {
        this.tbCategory = tbCategory;
    }

    public TbFile getTbFile() {
        return this.tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }

    public TbKeyword getTbKeyword() {
        return this.tbKeyword;
    }

    public void setTbKeyword(TbKeyword tbKeyword) {
        this.tbKeyword = tbKeyword;
    }

}