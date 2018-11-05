create database filemanager default character set utf8;

use filemanager;

CREATE TABLE `tb_user` (
  `userid` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `crate_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE `tb_file` (
  `fileid` int(10) NOT NULL AUTO_INCREMENT,
  `filename` varchar(150) DEFAULT NULL,
  `filerealname` varchar(150) DEFAULT NULL,
  `filesize` float(5,2) DEFAULT NULL,
  `userid` int(10) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `editdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `fileversion` int(3) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



CREATE TABLE `tb_file_version` (
  `fileversionid` int(11) NOT NULL AUTO_INCREMENT,
  `fileid` int(11) DEFAULT NULL,
  `filename` varchar(150) DEFAULT NULL,
  `filerealname` varchar(150) DEFAULT NULL,
  `filesize` float(5,2) DEFAULT NULL,
  `userid` int(10) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `editdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `fileversion` int(3) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fileversionid`),
  KEY `tb_file_fileid` (`fileid`),
  CONSTRAINT `tb_file_fileid` FOREIGN KEY (`fileid`) REFERENCES `tb_file` (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

