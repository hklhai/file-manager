/*
Navicat MySQL Data Transfer

Source Server         : 203.93.173.180
Source Server Version : 50722
Source Host           : 203.93.173.180:3306
Source Database       : crcc

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-03-06 17:03:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_path
-- ----------------------------
DROP TABLE IF EXISTS `tb_path`;
CREATE TABLE `tb_path` (
  `pathid` int(11) NOT NULL AUTO_INCREMENT COMMENT '路径ID',
  `parentid` int(11) DEFAULT NULL COMMENT '路径父节点ID',
  `pathname` varchar(100) NOT NULL COMMENT '路径名称',
  `parentname` varchar(100) NOT NULL COMMENT '父路径名称',
  `foldername` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `deptid` int(11) DEFAULT NULL COMMENT '组织机构ID',
  PRIMARY KEY (`pathid`),
  UNIQUE KEY `IDX_PATH_PK` (`pathid`) USING BTREE,
  KEY `IDX_PATH_PARENTID` (`parentid`) USING BTREE,
  KEY `IDX_PATH_PATHNAME` (`pathname`) USING BTREE,
  KEY `IDX_PATH_DEPTID` (`deptid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1092 DEFAULT CHARSET=utf8 COMMENT='文档目录表';

-- ----------------------------
-- Records of tb_path
-- ----------------------------
INSERT INTO `tb_path` VALUES ('1', null, 'D:/DOCLINKS', '', null, null);
INSERT INTO `tb_path` VALUES ('2', null, 'D:/DOCLINKS/private', 'D:/DOCLINKS', 'private', null);
INSERT INTO `tb_path` VALUES ('3', null, '/home/hadoop/doclinks', ' ', null, '-1');
INSERT INTO `tb_path` VALUES ('4', '3', '/home/hadoop/doclinks/private', '/home/hadoop/doclinks', 'private', '0');
INSERT INTO `tb_path` VALUES ('5', '3', '/home/hadoop/doclinks/icon', '/home/hadoop/doclinks', 'icon', '-1');
INSERT INTO `tb_path` VALUES ('1091', '3', '/home/hadoop/doclinks/总公司', '/home/hadoop/doclinks', '总公司', '128');
