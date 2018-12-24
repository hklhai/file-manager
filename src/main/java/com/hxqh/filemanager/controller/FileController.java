package com.hxqh.filemanager.controller;

import com.hxqh.filemanager.common.IConstants;
import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.assist.FileDto;
import com.hxqh.filemanager.model.assist.FileInfo;
import com.hxqh.filemanager.model.assist.FileVersionDto;
import com.hxqh.filemanager.model.base.Message;
import com.hxqh.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

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
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public Message uploadfile(@RequestParam("files") MultipartFile files,
                              @RequestParam(value = "appname") String appname,
                              @RequestParam(value = "userid", defaultValue = "0") Integer userid,
                              @RequestParam(value = "usersid", defaultValue = "") String usersid,
                              @RequestParam(value = "username", defaultValue = "") String username,
                              @RequestParam(value = "recordid", defaultValue = "0") Integer recordid,
                              @RequestParam(value = "recordsid", defaultValue = "") String recordsid) {
        Message message;
        try {
            if (0 == files.getSize()) {
                message = new Message(IConstants.FAIL, IConstants.UPLOADSIZE);
            } else {
                FileInfo fileInfo = new FileInfo(appname, userid, usersid, username, recordid, recordsid);
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
                             @RequestParam("fid") Integer fid) throws IOException {


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

        response.setHeader("Content-Disposition", "attachment;filename=" + downloadName);
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}
