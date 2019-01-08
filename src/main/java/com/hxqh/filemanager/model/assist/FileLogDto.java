package com.hxqh.filemanager.model.assist;

import com.hxqh.filemanager.model.TbFileLog;
import com.hxqh.filemanager.model.base.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lin
 */
public class FileLogDto extends Page implements Serializable {

    private List<TbFileLog> fileLogList;

    public FileLogDto(List<TbFileLog> fileLogList) {
        this.fileLogList = fileLogList;
    }

    public FileLogDto(Pageable page, Integer totalPages, Long total, List<TbFileLog> fileLogList) {
        super(page, totalPages, total);
        this.fileLogList = fileLogList;
    }

    public List<TbFileLog> getFileLogList() {
        return fileLogList;
    }

    public void setFileLogList(List<TbFileLog> fileLogList) {
        this.fileLogList = fileLogList;
    }
}
