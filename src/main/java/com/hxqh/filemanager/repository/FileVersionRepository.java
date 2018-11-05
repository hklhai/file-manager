package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.FileVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ocean lin on 2018/11/5.
 *
 * @author Ocean lin
 */
@Repository
public interface FileVersionRepository extends JpaRepository<FileVersion, Integer> {
}
