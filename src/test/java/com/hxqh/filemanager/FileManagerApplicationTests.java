package com.hxqh.filemanager;

import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.service.FileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileManagerApplicationTests {

    @Autowired
    private FileService fileService;

    @Test
    public void contextLoads() {
        User user = fileService.findByUserid(1);
        Assert.assertTrue("admin".equals(user.getName()));
    }

}
