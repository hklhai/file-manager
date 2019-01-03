package com.hxqh.filemanager.model.assist;

public class CategoryKeyword {

    private Integer categoryid;
    private Integer keywordid;

    public CategoryKeyword() {
    }

    public CategoryKeyword(Integer categoryid, Integer keywordid) {
        this.categoryid = categoryid;
        this.keywordid = keywordid;
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
