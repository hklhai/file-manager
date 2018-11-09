/*
Navicat MySQL Data Transfer

Source Server         : HXQH-spark4-root
Source Server Version : 50722
Source Host           : spark4:3306
Source Database       : filemanager

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-11-09 10:46:46
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
INSERT INTO `hibernate_sequence` VALUES ('50');

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
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('42', null, 'gan.py', '0.01', '1', '', '', '2018-11-07 10:11:56', '2018-11-07 10:11:56', '4', '/2018-11/2018-11-07_101156_afcdfa48-b78d-4c99-aedd-a8413dee99e2.py', 'test', '0', '', null, null, null);
INSERT INTO `tb_file` VALUES ('46', null, 'gan.py', '0.01', '1', '', '', '2018-11-07 10:13:12', '2018-11-07 10:13:12', '1', '/2018-11/2018-11-07_101312_9020f2a8-84f7-4e66-a6b5-6f84cca8f164.py', 'test', '1', '', null, null, null);
INSERT INTO `tb_file` VALUES ('47', null, '9-25汇报.pptx', '0.80', '1', '', '', '2018-11-08 19:42:52', '2018-11-08 19:42:52', '1', '/2018-11/2018-11-09_094252_9873655f-ffbf-4f04-b2f8-8aed4562ddc4.pptx', 'test', '1', '', null, null, null);
INSERT INTO `tb_file` VALUES ('48', null, '9-25汇报.pptx', '0.80', '1', '', '', '2018-11-08 20:02:33', '2018-11-08 20:02:33', '1', '/2018-11/2018-11-09_100225_da0b725e-a5f0-4a79-b134-fa7a2c014055.pptx', 'test', '1', '', null, null, null);
INSERT INTO `tb_file` VALUES ('49', null, '2018-11-09_100225_da0b725e-a5f0-4a79-b134-fa7a2c014055.pptx', '0.80', '1', '', '', '2018-11-08 20:05:06', '2018-11-08 20:05:06', '1', '/2018-11/2018-11-09_100501_3b5481f6-829b-446b-95f6-682daa56dca7.pptx', 'test', '1', '', null, null, null);

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
  PRIMARY KEY (`fileversionid`),
  KEY `tb_file_fileid` (`fileid`),
  CONSTRAINT `tb_file_fileid` FOREIGN KEY (`fileid`) REFERENCES `tb_file` (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file_version
-- ----------------------------
INSERT INTO `tb_file_version` VALUES ('43', '42', null, 'gan.py', '0.01', '1', '', '2018-11-07 10:09:39', '2018-11-07 10:09:39', '1', '/2018-11/2018-11-07_100939_8dc9cd4f-883c-4bd0-be96-d75c9a93ff84.py', 'test', '0', '', '', null, null, null);
INSERT INTO `tb_file_version` VALUES ('44', '42', null, 'gan.py', '0.01', '1', '', '2018-11-07 10:11:49', '2018-11-07 10:11:49', '2', '/2018-11/2018-11-07_101149_5c49888d-f56b-4a80-b12f-de03fb35606c.py', 'test', '0', '', '', null, null, null);
INSERT INTO `tb_file_version` VALUES ('45', '42', null, 'gan.py', '0.01', '1', '', '2018-11-07 10:11:55', '2018-11-07 10:11:55', '3', '/2018-11/2018-11-07_101155_ea7a3970-2ffc-44f8-ac46-1007552fdec0.py', 'test', '0', '', '', null, null, null);

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
