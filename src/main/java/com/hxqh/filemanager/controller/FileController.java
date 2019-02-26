package com.hxqh.filemanager.controller;

import com.hxqh.filemanager.common.IConstants;
import com.hxqh.filemanager.model.*;
import com.hxqh.filemanager.model.assist.*;
import com.hxqh.filemanager.model.base.Message;
import com.hxqh.filemanager.model.base.MessageFile;
import com.hxqh.filemanager.model.base.MessageInfo;
import com.hxqh.filemanager.model.view.VBaseKeywordFile;
import com.hxqh.filemanager.model.view.VFileKeywordKeyWord;
import com.hxqh.filemanager.service.FileService;
import com.hxqh.filemanager.util.FileUtil;
import com.hxqh.filemanager.util.Sm4Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.hxqh.filemanager.common.IConstants.*;

/**
 * Created by Ocean Lin on 2018/10/30.
 *
 * @author Lin
 */
@Controller
@RequestMapping("/file")
public class FileController {


    private final static Integer NUM = 3;

    @Autowired
    private FileService fileService;

    @Value(value = "${com.hxqh.filemanager.upload}")
    private String uploadPath;


    @ResponseBody
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public MessageFile uploadfile(@RequestParam("files") MultipartFile files,
                                  @RequestParam(value = "userid", defaultValue = "0") Integer userid,
                                  @RequestParam(value = "username", defaultValue = "") String username,
                                  @RequestParam(value = "deptid", defaultValue = "0") Integer deptid,
                                  @RequestParam(value = "deptfullname", defaultValue = "") String deptfullname,
                                  @RequestParam(value = "appid", defaultValue = "0") Integer appid,
                                  @RequestParam(value = "appname", defaultValue = "") String appname,
                                  @RequestParam(value = "recordid", defaultValue = "0") Integer recordid,
                                  @RequestParam(value = "pathid", defaultValue = "0") Integer pathid) {
        MessageFile message;
        if (0 == pathid && 0 != appid) {
            pathid = PATH;
        } else if (0 == pathid && 0 == appid) {
            pathid = PATH;
        }
        try {
            if (0 == files.getSize()) {
                message = new MessageFile(IConstants.FAIL, IConstants.UPLOADSIZE);
            } else if (PATH_PRIVATE.equals(pathid)) {
                message = new MessageFile(IConstants.FAIL, IConstants.PATHPRIVATEROOT);
                // 已存在
            } else if (files.getOriginalFilename().split(IConstants.DOT).length >= NUM) {
                message = new MessageFile(IConstants.FAIL, IConstants.UPLOADDOT);
            } else if (null == deptfullname || "".equals(deptfullname)) {
                message = new MessageFile(IConstants.FAIL, IConstants.DEPT_IS_NULL);
            } else {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setUserid(userid);
                fileInfo.setUsername(username);
                fileInfo.setDeptid(deptid);
                fileInfo.setDeptfullname(deptfullname);
                fileInfo.setRecordid(recordid);
                fileInfo.setAppid(appid);
                fileInfo.setAppname(appname);
                fileInfo.setPathid(pathid);
                FileIdSize fileIdSize = fileService.saveFile(files, fileInfo);
                message = new MessageFile(IConstants.SUCCESS, IConstants.UPLOADSUCCESS,
                        fileIdSize.getFileid(), fileIdSize.getFilesize());
            }
        } catch (Exception e) {
            message = new MessageFile(IConstants.FAIL, IConstants.UPLOADFAIL);
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadIcon", method = RequestMethod.POST)
    public MessageInfo uploadIcon(@RequestParam("files") MultipartFile files,
                                  @RequestParam(value = "userid", defaultValue = "0") Integer userid,
                                  @RequestParam(value = "username", defaultValue = "") String username,
                                  @RequestParam(value = "appname", defaultValue = "icon") String appname,
                                  @RequestParam(value = "recordid", defaultValue = "0") Integer recordid,
                                  @RequestParam(value = "deptid", defaultValue = "0") Integer deptid,
                                  @RequestParam(value = "deptfullname", defaultValue = "") String deptfullname,
                                  @RequestParam(value = "pathid", defaultValue = "0") Integer pathid) {

        // todo  增加fileid 判断是否修改
        MessageInfo message;
        String icon;
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUserid(userid);
        fileInfo.setRecordid(recordid);
        fileInfo.setUsername(username);
        fileInfo.setAppname(appname);
        fileInfo.setDeptid(deptid);
        fileInfo.setDeptfullname(deptfullname);
        fileInfo.setPathid(pathid);
        try {
            icon = fileService.saveIcon(files, fileInfo);
            message = new MessageInfo(IConstants.SUCCESS, IConstants.UPLOADSUCCESS, icon);
        } catch (Exception e) {
            message = new MessageInfo(IConstants.FAIL, IConstants.UPLOADFAIL, null);
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/icon", method = RequestMethod.GET)
    public MessageInfo icon(@RequestParam(value = "appname", defaultValue = "icon") String appname,
                            @RequestParam(value = "recordid", defaultValue = "0") Integer recordid) {
        MessageInfo message;
        String icon;
        FileInfo fileInfo = new FileInfo();
        fileInfo.setRecordid(recordid);
        fileInfo.setAppname(appname);
        try {
            icon = fileService.getIconUrl(fileInfo);
            message = new MessageInfo(IConstants.SUCCESS, IConstants.UPLOADSUCCESS, icon);
        } catch (Exception e) {
            message = new MessageInfo(IConstants.FAIL, IConstants.UPLOADFAIL, null);
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
    @RequestMapping(value = "/fileKeyword", method = RequestMethod.POST)
    public Message fileKeyword(@RequestBody FileKeyword fileKeyword) {
        Message message;
        try {
            fileService.fileBindKeyword(fileKeyword);
            message = new Message(IConstants.SUCCESS, IConstants.KEYWORD_SUCCESS);
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.KEYWORD_FAIL);
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/fileKeywordList", method = RequestMethod.POST)
    public List<VFileKeywordKeyWord> fileKeywordList(@RequestBody TbFile file) {
        List<VFileKeywordKeyWord> keywordList = null;
        try {
            keywordList = fileService.fileKeywordList(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keywordList;
    }


    @ResponseBody
    @RequestMapping(value = "/createPath", method = RequestMethod.POST)
    public Message createPath(@RequestBody TbPath tbPath) {
        Message message;

        try {
            // 不合法
            if (!FileUtil.isValidFileName(tbPath.getFoldername())) {
                message = new Message(IConstants.FAIL, IConstants.PATHINVALID);
                // 根目录不允许创建
            } else if (0 == tbPath.getParentid()) {
                message = new Message(IConstants.FAIL, IConstants.PATHROOT);
                // 已存在
            } else if (fileService.isExist(tbPath)) {
                message = new Message(IConstants.FAIL, IConstants.PATHEXIST);
                // 创建成功
            } else {
                fileService.createPath(tbPath);
                message = new Message(IConstants.SUCCESS, IConstants.PATHSUCCESS);
            }
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.PATHFAIL);
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/pathList", method = RequestMethod.POST)
    public PathDto pathList(@RequestBody TbPath path,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "fileid");
        if (0 == path.getPathid()) {
            path.setPathid(PATH);
        }
        List<TbPath> pathList;
        FileDto fileList;
        PathDto pathDto = null;

        try {
            pathList = fileService.pathList(path);
            fileList = fileService.findFileByPathId(path, sort, page, size);
            pathDto = new PathDto(pathList, fileList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathDto;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFile/{id}", method = RequestMethod.DELETE)
    public Message deleteFile(@PathVariable("id") Integer fileId) {
        Message message;
        try {
            fileService.deleteFile(fileId);
            message = new Message(IConstants.SUCCESS, IConstants.DELETESUCCESS);
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.DELETEFAIL);
            e.printStackTrace();
        }
        return message;
    }


    @ResponseBody
    @RequestMapping(value = "/logicDeleteFile/{id}", method = RequestMethod.DELETE)
    public Message logicDeleteFile(@PathVariable("id") Integer fileId) {
        Message message;
        try {
            fileService.logicDeleteFile(fileId);
            message = new Message(IConstants.SUCCESS, IConstants.DELETESUCCESS);
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.DELETEFAIL);
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/deletePath/{id}", method = RequestMethod.DELETE)
    public Message deletePath(@PathVariable("id") Integer pathId) {
        Message message;

        if (1 == pathId || 2 == pathId || 3 == pathId || 4 == pathId) {
            message = new Message(IConstants.FAIL, IConstants.DELETEROOT);
            return message;
        }
        try {
            // 删除 判断是否存在文件； 判断是否有子文件夹； 删除表中关系
            if (fileService.hasFile(pathId)) {
                message = new Message(IConstants.FAIL, IConstants.DELETEHASFILE);
            } else if (fileService.hasPath(pathId)) {
                message = new Message(IConstants.FAIL, IConstants.DELETEHASPATH);
            } else {
                fileService.deletePath(pathId);
                message = new Message(IConstants.SUCCESS, IConstants.DELETESUCCESS);
            }
        } catch (Exception e) {
            message = new Message(IConstants.FAIL, IConstants.DELETEPATHFAIL);
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
                             @RequestParam("fid") Integer fid,
                             @RequestParam("userid") Integer userid,
                             @RequestParam("username") String username,
                             @RequestParam("deptid") Integer deptid,
                             @RequestParam("deptfullname") String deptfullname) throws Exception {

        TbFileLog tbFileLog = new TbFileLog();
        tbFileLog.setFileid(fid);
        tbFileLog.setUserid(userid);
        tbFileLog.setUsername(username);
        tbFileLog.setDeptid(deptid);
        tbFileLog.setDeptfullname(deptfullname);

        TbCurrentFileLog tbCurrentFileLog = fileService.logAndReturnCurrent(tbFileLog);
        System.out.println(tbCurrentFileLog);

        String userAgent = request.getHeader("User-Agent");
        String s;
        String urlname, fileName = null;
        if (IConstants.FILE.equals(ftype)) {
            TbFile file = fileService.findByFileid(fid);
            if (0 != file.getAppid()) {
                urlname = uploadPath + file.getFilepath();
            } else {
                TbPath path = fileService.findPathById(file.getTbPath().getPathid());
                urlname = path.getPathname() + file.getFilepath();
            }
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

        byte[] encodeBytes = FileUtil.getByte(file);
        byte[] decryptEcb = Sm4Util.decryptEcb(KEY, encodeBytes);

        response.setHeader("Content-Disposition", "attachment;filename=" + downloadName);
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        response.setContentLength(decryptEcb.length);
        // 指向response的输出流
        OutputStream os = response.getOutputStream();
        os.write(decryptEcb, 0, decryptEcb.length);
        os.close();
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
        return cipher;
    }


    @ResponseBody
    @RequestMapping(value = "/fileCurrentLogList", method = RequestMethod.POST)
    public List<TbCurrentFileLog> fileCurrentLogList(@RequestBody TbFile file) {
        List<TbCurrentFileLog> currentFileLogList = null;
        try {
            currentFileLogList = fileService.fileCurrentLogList(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentFileLogList;
    }

    @ResponseBody
    @RequestMapping(value = "/fileLogList", method = RequestMethod.POST)
    public FileLogDto fileLogList(@RequestBody TbFile file,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        FileLogDto fileDto = null;
        Sort sort = new Sort(Sort.Direction.DESC, "filelogid");
        try {
            Pageable pageable = PageRequest.of(page, size, sort);
            fileDto = fileService.fileLogList(file, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileDto;
    }


    @ResponseBody
    @RequestMapping(value = "/baseKeywordList", method = RequestMethod.POST)
    public BaseKeywordDto baseKeywordList(@RequestBody VBaseKeywordFile keywordFile,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        BaseKeywordDto baseKeywordDto = null;
        Sort sort = new Sort(Sort.Direction.DESC, "fileid");
        try {
            Pageable pageable = PageRequest.of(page, size, sort);
            baseKeywordDto = fileService.baseKeywordList(keywordFile, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseKeywordDto;
    }


    @ResponseBody
    @RequestMapping(value = "/privilege", method = RequestMethod.POST)
    public FilePrivilege privilege(@RequestBody FilePrivilegeDto filePrivilegeDto) {
        FilePrivilege filePrivilege = null;
        try {
            filePrivilege = fileService.privilege(filePrivilegeDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePrivilege;
    }


    @ResponseBody
    @RequestMapping(value = "/filePath", method = RequestMethod.GET)
    public TbFile filePath(@RequestParam(value = "fileId", defaultValue = "0") Integer fileId) {
        TbFile file = null;
        try {
            file = fileService.filePath(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
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
            } else if (files.getOriginalFilename().split(IConstants.DOT).length >= NUM) {
                message = new Message(IConstants.FAIL, IConstants.UPLOADDOT);
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
}
