package com.hxqh.filemanager.model.assist;

import java.util.List;

public class FileKeyword {

    private Integer fileid;

    private List<CategoryKeyword> categoryKeywordList;

    private List<Integer> deleteFilekeywordidList;

    public FileKeyword() {
    }

    public FileKeyword(Integer fileid, List<CategoryKeyword> categoryKeywordList) {
        this.fileid = fileid;
        this.categoryKeywordList = categoryKeywordList;
    }

    public List<Integer> getDeleteFilekeywordidList() {
        return deleteFilekeywordidList;
    }

    public void setDeleteFilekeywordidList(List<Integer> deleteFilekeywordidList) {
        this.deleteFilekeywordidList = deleteFilekeywordidList;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public List<CategoryKeyword> getCategoryKeywordList() {
        return categoryKeywordList;
    }

    public void setCategoryKeywordList(List<CategoryKeyword> categoryKeywordList) {
        this.categoryKeywordList = categoryKeywordList;
    }
}
