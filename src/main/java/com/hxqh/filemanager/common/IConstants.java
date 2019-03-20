package com.hxqh.filemanager.common;

/**
 * Created by Ocean lin on 2018/11/1.
 *
 * @author Lin
 */
public interface IConstants {


    String OS = System.getProperty("os.name");
    Integer PATH = OS.toLowerCase().startsWith("win") == true ? 3 : 3;
    Integer PATH_PRIVATE = OS.toLowerCase().startsWith("win") == true ? 4 : 4;

    String FILE = "file";
    Integer SUCCESS = 1;
    Integer FAIL = 0;
    Integer THOUSAND = 1024;
    String VERSION = "Viersion";

    String DOWNLOAD_FILE = "file&fid=";
    String DOWNLOAD_VERSION = "version&fid=";

    String MSIE = "MSIE";
    String TRIDENT = "Trident";

    String GET_SUCCESS = "获取成功";
    String GET_FAIL = "获取失败";

    String DELETESUCCESS = "删除成功!";
    String DELETEFAIL = "删除失败!";
    String UPLOADSUCCESS = "上传成功!";
    String UPLOADFAIL = "上传失败!";
    String UPLOADFAIL_ICON = "头像需要PNG或PNG格式";
    String UPLOADSIZE = "文件大小不能为0!";
    String UPLOADDOT = "名称中包含“.”，请修改后重试！";
    String DEPT_IS_NULL = "组织机构名称不能为空！";
    String UPLOADSAME = "已存在相同名称文件!";
    String FILE_REFER = "file";
    String VERSION_REFER = "version";


    String PATHINVALID = "文件目录不合法！";
    String PATHEXIST = "文件目录已存在！";
    String PATHROOT = "根目录下无法创建文件夹，请在Private目录下创建！";
    String PATHPRIVATEROOT = "Private根目录下无法上传文件，请在Private目录下创建文件夹！";
    String PATHSUCCESS = "文件目录创建成功！";
    String PATHFAIL = "文件目录创建失败！";


    String DELETEROOT = "不允许删除根目录！";
    String DELETEHASFILE = "该目录下存在子文件！";
    String DELETEHASPATH = "该目录下存在子目录！";
    String DELETEPATHFAIL = "文件目录删除失败！";


    String KEYWORD_SUCCESS = "关键字设置成功！";
    String KEYWORD_FAIL = "关键字设置失败！";


    String SPLIT = "\\";
    String DOUBLE_SPLIT = "\\";

    String STATUS_RELEASE = "已发布";

    String DOT = "\\.";


    String UPDATE_STATE = "UPLOAD";
    String DOWNLOAD_STATE = "DOWNLOAD";


    /**
     * 初始化向量(IV)，aes 16位
     */
    String IV = "abcdefghijk1mnop";


    String SQL_1 = "SELECT \n" +
            "tb_file_keyword.filekeywordid,\n" +
            "tb_file.fileid,\n" +
            "tb_file_keyword.categoryid ,\n" +
            "tb_keyword_privilege2.categoryid as categoryid2,\n" +
            "tb_keyword_privilege2.userid,\n" +
            "tb_keyword_privilege2.fileread,\n" +
            "tb_keyword_privilege2.fileedit,\n" +
            "tb_keyword_privilege2.fileprint,\n" +
            "tb_keyword_privilege2.fileupload,\n" +
            "tb_keyword_privilege2.filedownload,\n" +
            "tb_keyword_privilege2.fileduplicate,\n" +
            "tb_keyword_privilege2.filedelete\n" +
            "FROM tb_file_keyword\n" +
            "INNER JOIN tb_file ON tb_file_keyword.fileid = tb_file.fileid and tb_file.fileid =";

    String SQL_2 = " LEFT OUTER JOIN tb_keyword_privilege2 ON tb_file_keyword.categoryid = tb_keyword_privilege2.categoryid " +
            "AND tb_file_keyword.keywordid = tb_keyword_privilege2.keywordid and tb_keyword_privilege2.userid = ";

    String UNION_ALL = " union all ";

    String KEY = "86C63180C2806ED1F47B859DE501200B";

    String PNG = "PNG";
    String JPG = "JPG";

    String KEYWORD_SQL_1 = "SELECT\n" +
            "\t`f`.`fileid` AS `fileid`,\n" +
            "\t`f`.`filepath` AS `filepath`,\n" +
            "\t`f`.`filename` AS `filename`,\n" +
            "\t`f`.`deptid` AS `deptid`,\n" +
            "\t`f`.`deptfullname` AS `deptfullname`,\n" +
            "\t`f`.`filestatus` AS `filestatus`,\n" +
            "\t`f`.`uploadtime` AS `uploadtime`,\n" +
            "\t`f`.`filesize` AS `filesize`,\n" +
            "\t`f`.`isshow` AS `isshow` \n" +
            "FROM\n" +
            "\ttb_file f,\n" +
            "\ttb_file_keyword fk,\n" +
            "\ttb_keyword_privilege2 kp \n" +
            "WHERE\n" +
            "\t`f`.`fileid` = `fk`.`fileid` \n" +
            "\tAND fk.categoryid = kp.categoryid \n" +
            "\tAND fk.keywordid = kp.keywordid \n" +
            "\tAND kp.userid = ";


    String  KEYWORD_SQL_2= "SELECT\n" +
            "count(1) as total \n" +
            "FROM\n" +
            "tb_file f,\n" +
            "tb_file_keyword fk,\n" +
            "tb_keyword_privilege2 kp \n" +
            "WHERE\n" +
            "`f`.`fileid` = `fk`.`fileid` \n" +
            "AND fk.categoryid = kp.categoryid \n" +
            "AND fk.keywordid = kp.keywordid \n" +
            "AND kp.userid =";

}
