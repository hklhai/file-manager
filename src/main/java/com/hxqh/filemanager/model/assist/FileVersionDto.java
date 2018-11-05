package com.hxqh.filemanager.model.assist;

import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.base.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Ocean lin on 2018/11/5.
 *
 * @author Ocean lin
 */
public class FileVersionDto extends Page {

    private List<TbFileVersion> fileVersionList;

    public FileVersionDto(List<TbFileVersion> fileVersionList) {
        this.fileVersionList = fileVersionList;
    }

    public FileVersionDto(Pageable page, Integer totalPages, Long total, List<TbFileVersion> fileVersionList) {
        super(page, totalPages, total);
        this.fileVersionList = fileVersionList;
    }

    public List<TbFileVersion> getFileVersionList() {
        return fileVersionList;
    }

    public void setFileVersionList(List<TbFileVersion> fileVersionList) {
        this.fileVersionList = fileVersionList;
    }
}
