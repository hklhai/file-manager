package com.hxqh.filemanager;

import com.hxqh.filemanager.model.User;
import com.hxqh.filemanager.repository.FileRepository;
import com.hxqh.filemanager.repository.FileVersionRepository;
import com.hxqh.filemanager.service.FileService;
import com.hxqh.filemanager.util.FilePathUtil;
import com.hxqh.filemanager.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

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
    @Value(value = "${com.hxqh.filemanager.upload}")
    private String uploadPath;
    @Value(value = "${com.hxqh.filemanager.private}")
    private String privatePath;

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

    @Test
    public void mkdir() {
        String path = privatePath + "/" + "bbb";
        boolean b = FilePathUtil.legalFile(path);
        if (b) {
            FileUtil.createPaths(path);
        } else {
            System.out.println("invalid");
        }
    }

    @Test
    public void listFileAll() {
        String path = privatePath;
        List<File> files = FileUtil.listFileAll(new File(path));
        files.stream().forEach(e -> System.out.println(e.getName()));
    }




}
