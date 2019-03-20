package com.hxqh.filemanager.model.assist;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Lin
 */
@Entity
public class VBaseKeywordFile {

    @Id
    private Integer fileid;
    private String filepath;
    private String filename;

    @JSONField(serialize = false)
    private Integer deptid;
    @JSONField(serialize = false)
    private String deptfullname;

    private Double filesize;
    private String filestatus;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date uploadtime;

    private Integer isshow;

    public VBaseKeywordFile() {
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Double getFilesize() {
        return filesize;
    }

    public void setFilesize(Double filesize) {
        this.filesize = filesize;
    }

    public String getFilestatus() {
        return filestatus;
    }

    public void setFilestatus(String filestatus) {
        this.filestatus = filestatus;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getDeptfullname() {
        return deptfullname;
    }

    public void setDeptfullname(String deptfullname) {
        this.deptfullname = deptfullname;
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

}
