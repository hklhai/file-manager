package com.hxqh.filemanager.controller;

import com.hxqh.filemanager.common.IConstants;
import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.TbPath;
import com.hxqh.filemanager.model.assist.FileDto;
import com.hxqh.filemanager.model.assist.FileInfo;
import com.hxqh.filemanager.model.assist.FileVersionDto;
import com.hxqh.filemanager.model.base.Message;
import com.hxqh.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ocean Lin on 2018/10/30.
 *
 * @author Lin
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value(value = "${com.hxqh.filemanager.upload}")
    private String uploadPath;


    @ResponseBody
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public Message uploadfile(@RequestParam("files") MultipartFile files,
                              @RequestParam(value = "userid", defaultValue = "0") Integer userid,
                              @RequestParam(value = "username", defaultValue = "") String username,
                              @RequestParam(value = "deptid", defaultValue = "0") Integer deptid,
                              @RequestParam(value = "deptfullname", defaultValue = "") String deptfullname,
                              @RequestParam(value = "appid", defaultValue = "0") Integer appid,
                              @RequestParam(value = "appname", defaultValue = "") String appname,
                              @RequestParam(value = "recordid", defaultValue = "0") Integer recordid) {
        Message message;
        try {
            if (0 == files.getSize()) {
                message = new Message(IConstants.FAIL, IConstants.UPLOADSIZE);
            } else {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setUserid(userid);
                fileInfo.setUsername(username);
                fileInfo.setDeptid(deptid);
                fileInfo.setDeptfullname(deptfullname);
                fileInfo.setRecordid(recordid);
                fileInfo.setAppid(appid);
                fileInfo.setAppname(appname);

                fileService.saveFile(files, fileInfo);
                message = new Message(IConstants.SUCCESS, IConstants.UPLOADSUCCESS);
            }
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.UPLOADFAIL);
            e.printStackTrace();
        }
        return message;
    }


    @ResponseBody
    @RequestMapping(value = "/fileList", method = RequestMethod.POST)
    public FileDto fileList(@RequestBody TbFile file,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        FileDto fileDto = null;
        Sort sort = new Sort(Sort.Direction.DESC, "fileid");
        try {
            Pageable pageable = PageRequest.of(page, size, sort);
            fileDto = fileService.fileList(file, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileDto;
    }

    @ResponseBody
    @RequestMapping(value = "/createPath", method = RequestMethod.POST)
    public Message createPath(@RequestBody TbPath tbPath) {
        Message message;

        try {
            // todo
            // 不合法

            // 已存在

            // 创建成功

            // fileService.createPath(tbPath);
            message = new Message(IConstants.SUCCESS, IConstants.UPLOADSUCCESS);
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.UPLOADFAIL);
            e.printStackTrace();
        }
        return message;
    }


    // todo 删除 判断是否存在文件； 判断是否有子文件夹； 删除表中关系


    @ResponseBody
    @RequestMapping(value = "/fileVersionList", method = RequestMethod.POST)
    public FileVersionDto fileVersionList(@RequestBody TbFileVersion fileVersion,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        FileVersionDto fileVersionDto = null;
        Sort sort = new Sort(Sort.Direction.DESC, "fileversionid");
        try {
            Pageable pageable = PageRequest.of(page, size, sort);
            fileVersionDto = fileService.fileVersionList(fileVersion, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileVersionDto;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadNewVersion", method = RequestMethod.POST)
    public Message uploadNewVersion(@RequestParam("files") MultipartFile files,
                                    @RequestParam("fileid") Integer fileid) {
        Message message;
        try {
            // 增加同一文件上传时，判断如果md5相同提示存在相同文件。
            if (0 == files.getSize()) {
                message = new Message(IConstants.FAIL, IConstants.UPLOADSIZE);
            } else if (fileService.hasSameFile(files, fileid)) {
                message = new Message(IConstants.FAIL, IConstants.UPLOADSAME);
            } else {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileid(fileid);
                fileService.saveFile(files, fileInfo);
                message = new Message(IConstants.SUCCESS, IConstants.UPLOADSUCCESS);
            }

        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.UPLOADFAIL);
            e.printStackTrace();
        }
        return message;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
    public Message deleteFile(@RequestBody FileInfo fileInfo) {
        Message message = null;
        try {
            fileService.deleteFile(fileInfo);
            message = new Message(IConstants.SUCCESS, IConstants.DELETESUCCESS);
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.DELETEFAIL);
            e.printStackTrace();
        }
        return message;
    }

    /**
     * @param response
     * @param ftype    "file" 或 "viersion"
     * @param fid
     * @throws IOException
     */
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "ftype", defaultValue = "") String ftype,
                             @RequestParam("fid") Integer fid) throws Exception {


        String userAgent = request.getHeader("User-Agent");

        String s;
        String urlname, fileName = null;
        if (IConstants.FILE.equals(ftype)) {
            TbFile file = fileService.findByFileid(fid);
            urlname = uploadPath + file.getFilepath();
            fileName = file.getFilerealname();
        } else {
            TbFileVersion fileVersion = fileService.findByFileversionid(fid);
            urlname = uploadPath + fileVersion.getFilepath();
            fileName = IConstants.VERSION + fileVersion.getFileversion() + "_" + fileVersion.getFilerealname();
        }

        if (urlname.contains(IConstants.SPLIT)) {
            s = urlname.replace(IConstants.SPLIT, IConstants.DOUBLE_SPLIT);
        } else {
            s = urlname;
        }

        File file = new File(s);
        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        String downloadName;
        // 针对IE或者以IE为内核的浏览器
        if (userAgent.contains(IConstants.MSIE) || userAgent.contains(IConstants.TRIDENT)) {
            downloadName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            downloadName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        Cipher cipher = getCipherDecrypt();

        //解密文件流
        FileOutputStream outputStream = new FileOutputStream(uploadPath + "/tmp");
        //以解密流写出文件
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = inputStream.read(buffer)) >= 0) {
            cipherOutputStream.write(buffer, 0, r);
        }
        cipherOutputStream.close();
        outputStream.close();
        inputStream.close();


        InputStream stream = new BufferedInputStream(new FileInputStream(uploadPath + "/tmp"));
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadName);
        // 设置强制下载不打开
        response.setContentType("application/force-download");

        File tmp = new File(uploadPath + "/tmp");
        response.setContentLength((int) tmp.length());
        FileCopyUtils.copy(stream, response.getOutputStream());
        stream.close();
    }

    private Cipher getCipherDecrypt() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        File fileKey = new File(uploadPath + "/a.text");
        byte[] key = new byte[(int) fileKey.length()];
        FileInputStream fis = new FileInputStream(fileKey);
        fis.read(key);
        //根据给定的字节数组(密钥数组)构造一个AES密钥。
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        //实例化一个密码器（CBC模式）
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //初始化密码器
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, new IvParameterSpec(IConstants.IV.getBytes()));
        return  cipher;
    }

}
