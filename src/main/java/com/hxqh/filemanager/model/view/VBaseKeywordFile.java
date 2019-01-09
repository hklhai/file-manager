package com.hxqh.filemanager.model.view;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lin
 */
@Entity
@Table(name = "v_base_keyword_file")
public class VBaseKeywordFile {

    @Id
    private Integer fileid;
    private String filepath;
    private String filename;
    @JSONField(serialize = false)
    private Integer userid;
    @JSONField(serialize = false)
    private String username;

    public VBaseKeywordFile() {
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
}
