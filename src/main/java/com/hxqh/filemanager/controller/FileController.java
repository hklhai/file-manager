package com.hxqh.filemanager.controller;

import com.hxqh.filemanager.common.IConstants;
import com.hxqh.filemanager.model.TbFile;
import com.hxqh.filemanager.model.TbFileVersion;
import com.hxqh.filemanager.model.assist.FileDto;
import com.hxqh.filemanager.model.assist.FileVersionDto;
import com.hxqh.filemanager.model.base.Message;
import com.hxqh.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
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
    public Message uploadfileUpload(@RequestParam("files") MultipartFile files,
                                    @RequestParam("appname") String appname,
                                    @RequestParam("userid") Long userid) {
        Message message;
        try {
            fileService.saveFile(files, appname, userid);
            message = new Message(IConstants.SUCCESS, IConstants.UPLOADSUCCESS);
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.UPLOADFAIL);
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public Message deleteFile(@RequestParam("docinfoid") Long docinfoid) {
        Message message = null;
        try {
            fileService.deleteFile(docinfoid);
            message = new Message(IConstants.SUCCESS, IConstants.DELETESUCCESS);
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.DELETEFAIL);
            e.printStackTrace();
        } finally {
            return message;
        }
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @RequestParam("docinfoid") Long docinfoid) throws IOException {
        //获取文件路径
//        Docinfo docinfo = fileService.getFilePath(docinfoid);
        String s;
//        String urlname = docinfo.getUrlname();
        String urlname = null;
        if (urlname.contains("\\")) {
            s = urlname.replace("\\", "\\\\");
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
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + urlname + "\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}
