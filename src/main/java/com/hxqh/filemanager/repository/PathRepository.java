package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbPath;
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
public interface PathRepository extends JpaRepository<TbPath, Integer> {
    /**
     * 查询路径
     *
     * @param path
     * @return
     */
    TbPath findByPathname(String path);

    /**
     * @param pathid
     * @return
     */
    List<TbPath> findByParentid(Integer pathid);

    /**
     * @param parentid
     * @param foldername
     * @return
     */
    @Query("select u from TbPath u where u.parentid=?1 and u.foldername=?2")
    TbPath findParentIdAndFoldername(Integer parentid, String foldername);

}
