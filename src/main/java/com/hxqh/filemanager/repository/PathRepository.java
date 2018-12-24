package com.hxqh.filemanager.repository;

import com.hxqh.filemanager.model.TbPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ocean lin on 2018/12/24.
 *
 * @author Ocean lin
 */
@Repository
public interface PathRepository extends JpaRepository<TbPath, Integer> {
}
