package com.hxqh.filemanager.model.assist;

/**
 * @author Lin
 */
public class BaseKeywordFile {

    private Integer userid;
    private Integer categoryid;
    private Integer keywordid;

    public BaseKeywordFile() {
    }

    public BaseKeywordFile(Integer userid, Integer categoryid, Integer keywordid) {
        this.userid = userid;
        this.categoryid = categoryid;
        this.keywordid = keywordid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
}
