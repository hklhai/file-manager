package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the tb_path database table.
 *
 * @author Lin
 */
@Entity
@Table(name = "tb_path")
public class TbPath implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pathid;

    private Integer parentid;

    private String parentname;

    private String pathname;

    /**
     * bi-directional many-to-one association to TbFile
     */
    @OneToMany(mappedBy = "tbPath")
    @JSONField(serialize = false)
    private List<TbFile> tbFiles;

    public TbPath() {
    }

    public Integer getPathid() {
        return this.pathid;
    }

    public void setPathid(Integer pathid) {
        this.pathid = pathid;
    }

    public Integer getParentid() {
        return this.parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return this.parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getPathname() {
        return this.pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public List<TbFile> getTbFiles() {
        return this.tbFiles;
    }

    public void setTbFiles(List<TbFile> tbFiles) {
        this.tbFiles = tbFiles;
    }

    public TbFile addTbFile(TbFile tbFile) {
        getTbFiles().add(tbFile);
        tbFile.setTbPath(this);

        return tbFile;
    }

    public TbFile removeTbFile(TbFile tbFile) {
        getTbFiles().remove(tbFile);
        tbFile.setTbPath(null);

        return tbFile;
    }

}