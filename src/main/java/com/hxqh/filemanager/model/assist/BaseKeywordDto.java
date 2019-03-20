package com.hxqh.filemanager.model.assist;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lin
 */
public class BaseKeywordDto implements Serializable {

    private List<VBaseKeywordFile> vBaseKeywordFiles;
    private Integer totalPages;
    private Integer total;
    private Integer  page;
    private Integer size;

    public BaseKeywordDto() {
    }

    public BaseKeywordDto(List<VBaseKeywordFile> vBaseKeywordFiles, Integer totalPages, Integer total, Integer page, Integer size) {
        this.vBaseKeywordFiles = vBaseKeywordFiles;
        this.totalPages = totalPages;
        this.total = total;
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<VBaseKeywordFile> getvBaseKeywordFiles() {
        return vBaseKeywordFiles;
    }

    public void setvBaseKeywordFiles(List<VBaseKeywordFile> vBaseKeywordFiles) {
        this.vBaseKeywordFiles = vBaseKeywordFiles;
    }
}
