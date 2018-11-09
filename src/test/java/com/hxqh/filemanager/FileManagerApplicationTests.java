package com.hxqh.filemanager;

import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.repository.FileRepository;
import com.hxqh.filemanager.repository.FileVersionRepository;
import com.hxqh.filemanager.service.FileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileManagerApplicationTests {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileVersionRepository fileVersionRepository;


    @Test
    public void userList() {
        User user = fileService.findByUserid(1);
        Assert.assertTrue("admin".equals(user.getName()));
    }

    @Test
    public void fileList() {
        Assert.assertTrue(fileRepository.findAll().size() > 0);
    }

    @Test
    public void fileVersionList() {
        Assert.assertTrue(fileVersionRepository.findAll().size() > 0);
    }

}
