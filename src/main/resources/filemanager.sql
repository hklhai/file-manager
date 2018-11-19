/*
Navicat MySQL Data Transfer

Source Server         : HXQH-spark4-root
Source Server Version : 50722
Source Host           : spark4:3306
Source Database       : filemanager

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-11-19 11:15:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('81');

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `fileid` int(10) NOT NULL AUTO_INCREMENT,
  `filename` varchar(150) DEFAULT NULL,
  `filerealname` varchar(150) DEFAULT NULL,
  `filesize` float(5,2) DEFAULT NULL,
  `userid` int(10) DEFAULT NULL,
  `usersid` varchar(100) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `editdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `fileversion` int(3) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `appname` varchar(120) DEFAULT NULL,
  `recordid` int(11) DEFAULT NULL,
  `recordsid` varchar(100) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `refertab` varchar(50) DEFAULT NULL,
  `referid` int(10) DEFAULT NULL,
  `extensionname` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('77', '9-25汇报', '9-25汇报.pptx', '0.80', '1', '', '', '2018-11-19 09:36:16', '2018-11-19 09:36:16', '1', '/2018-11/2018-11-19_093522_238aec5b-e9c6-42a2-9b23-3a77eb901929.pptx', 'test', '2', '', '33955d41fd7a2bd2ce135e18824239c8', null, null, 'pptx');
INSERT INTO `tb_file` VALUES ('78', '9-25汇报', '9-25汇报.pptx', '0.80', '1', '', '', '2018-11-19 09:36:19', '2018-11-19 09:36:19', '1', '/2018-11/2018-11-19_093522_238aec5b-e9c6-42a2-9b23-3a77eb901929.pptx', 'test', '3', '', '33955d41fd7a2bd2ce135e18824239c8', 'file', '77', 'pptx');
INSERT INTO `tb_file` VALUES ('80', '9-25汇报', '9-25汇报.pptx', '0.80', '1', '', '', '2018-11-18 19:37:08', '2018-11-18 19:37:08', '1', '/2018-11/2018-11-19_093522_238aec5b-e9c6-42a2-9b23-3a77eb901929.pptx', 'test', '1', '', '33955d41fd7a2bd2ce135e18824239c8', 'file', '77', 'pptx');

-- ----------------------------
-- Table structure for tb_file_version
-- ----------------------------
DROP TABLE IF EXISTS `tb_file_version`;
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
  `appname` varchar(255) DEFAULT NULL,
  `recordid` int(11) DEFAULT NULL,
  `recordsid` varchar(255) DEFAULT NULL,
  `usersid` varchar(255) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `refertab` varchar(50) DEFAULT NULL,
  `referid` int(10) DEFAULT NULL,
  `extensionname` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`fileversionid`),
  KEY `tb_file_fileid` (`fileid`),
  CONSTRAINT `tb_file_fileid` FOREIGN KEY (`fileid`) REFERENCES `tb_file` (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file_version
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userid` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `crate_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '2', '2018-10-10 15:03:04', 'admin', null, 'admin');
