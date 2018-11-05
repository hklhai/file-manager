package com.hxqh.filemanager.model.assist;

import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.base.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ocean lin on 2018/11/5.
 *
 * @author Ocean lin
 */
public class FileDto extends Page implements Serializable {

    private static final long serialVersionUID = 605917729806183175L;
    private List<TbFile> fileList;

    public FileDto(List<TbFile> fileList) {
        this.fileList = fileList;
    }

    public FileDto(Pageable page, Integer totalPages, Long total, List<TbFile> fileList) {
        super(page, totalPages, total);
        this.fileList = fileList;
    }

    public List<TbFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<TbFile> fileList) {
        this.fileList = fileList;
    }
}
