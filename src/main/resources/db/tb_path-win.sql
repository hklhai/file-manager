/*
Navicat MySQL Data Transfer

Source Server         : HXQH-spark4-root
Source Server Version : 50722
Source Host           : spark4:3306
Source Database       : crcc

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-03-06 17:04:36
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
) ENGINE=InnoDB AUTO_INCREMENT=727 DEFAULT CHARSET=utf8 COMMENT='文档目录表';

-- ----------------------------
-- Records of tb_path
-- ----------------------------
INSERT INTO `tb_path` VALUES ('1', null, 'C:/doclinks', '', null, '-1');
INSERT INTO `tb_path` VALUES ('2', '1', 'C:/doclinks/private', 'D:/DOCLINKS', 'private', '0');
INSERT INTO `tb_path` VALUES ('3', null, '/home/hadoop/doclinks', ' ', null, '-1');
INSERT INTO `tb_path` VALUES ('4', null, '/home/hadoop/doclinks/private', '/home/hadoop/doclinks', 'private', '0');
INSERT INTO `tb_path` VALUES ('5', null, '/home/hadoop/doclinks/icon', '/home/hadoop/doclinks', 'icon', '-1');
INSERT INTO `tb_path` VALUES ('6', '1', 'C:/doclinks/icon', 'D:/DOCLINKS', 'icon', '-1');
