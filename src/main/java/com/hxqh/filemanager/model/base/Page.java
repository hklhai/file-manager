package com.hxqh.filemanager.model.base;

import org.springframework.data.domain.Pageable;

/**
 * Created by Ocean lin on 2018/10/17.
 *
 * @author Ocean lin
 */
public class Page {

    private Pageable page;
    private Integer totalPages;
    private Long total;

    public Page() {
    }

    public Page(Pageable page, Integer totalPages, Long total) {
        this.page = page;
        this.totalPages = totalPages;
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
