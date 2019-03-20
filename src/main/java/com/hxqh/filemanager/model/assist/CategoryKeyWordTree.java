package com.hxqh.filemanager.model.assist;

import com.hxqh.filemanager.model.TbCategory;

import java.util.List;

/**
 * @author Lin
 */
public class CategoryKeyWordTree {

    private List<TbCategory> categoryList;

    public CategoryKeyWordTree() {
    }

    public CategoryKeyWordTree(List<TbCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<TbCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<TbCategory> categoryList) {
        this.categoryList = categoryList;
    }
}
