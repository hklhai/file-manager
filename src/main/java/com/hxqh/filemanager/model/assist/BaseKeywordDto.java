package com.hxqh.filemanager.model.assist;

import com.hxqh.filemanager.model.TbFileLog;
import com.hxqh.filemanager.model.base.Page;
import com.hxqh.filemanager.model.view.VBaseKeywordFile;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lin
 */
public class BaseKeywordDto extends Page implements Serializable {

    private List<VBaseKeywordFile> vBaseKeywordFiles;


    public BaseKeywordDto(List<VBaseKeywordFile> vBaseKeywordFiles) {
        this.vBaseKeywordFiles = vBaseKeywordFiles;
    }

    public BaseKeywordDto(Pageable page, Integer totalPages, Long total, List<VBaseKeywordFile> vBaseKeywordFiles) {
        super(page, totalPages, total);
        this.vBaseKeywordFiles = vBaseKeywordFiles;
    }

    public List<VBaseKeywordFile> getvBaseKeywordFiles() {
        return vBaseKeywordFiles;
    }

    public void setvBaseKeywordFiles(List<VBaseKeywordFile> vBaseKeywordFiles) {
        this.vBaseKeywordFiles = vBaseKeywordFiles;
    }
}
