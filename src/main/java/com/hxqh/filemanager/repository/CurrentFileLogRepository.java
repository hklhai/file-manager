package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.TbCurrentFileLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ocean lin on 2018/12/24.
 *
 * @author Ocean lin
 */
@Repository
public interface CurrentFileLogRepository extends JpaRepository<TbCurrentFileLog, Integer> {

    /**
     * @param fileid
     * @param operatetype
     * @return
     */
    @Query("select u from TbCurrentFileLog u where u.tbFile.fileid=?1 and u.operatetype=?2")
    TbCurrentFileLog findByFileidAndOperatetype(Integer fileid, String operatetype);

    /**
     * @param fileid
     * @return
     */
    @Query("select u from TbCurrentFileLog u where u.tbFile.fileid=?1")
    List<TbCurrentFileLog> findByFileId(Integer fileid);
}
