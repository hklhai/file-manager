create database filemanager default character set utf8;

use filemanager;

delete from tb_file_version;
delete from tb_file;


-- New Database
use apps_center;


-- 清库
delete * from tb_file;
delete * from tb_path t  where t.pathname = 'D:/DOCLINKS/2018-12'
