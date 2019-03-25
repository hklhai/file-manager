delete from tb_file_version;
delete from tb_file_log;
delete from tb_file_keyword;
delete from tb_current_file_log;
delete from tb_file;
delete from tb_path;

-- ----------------------------
-- Records of tb_path
-- ----------------------------
INSERT INTO `tb_path` VALUES ('1', null, 'D:/DOCLINKS', '', null, null);
INSERT INTO `tb_path` VALUES ('2', null, 'D:/DOCLINKS/private', 'D:/DOCLINKS', 'private', null);
INSERT INTO `tb_path` VALUES ('3', null, '/home/hadoop/doclinks', ' ', null, '-1');
INSERT INTO `tb_path` VALUES ('4', '3', '/home/hadoop/doclinks/private', '/home/hadoop/doclinks', 'private', '0');
INSERT INTO `tb_path` VALUES ('5', '3', '/home/hadoop/doclinks/icon', '/home/hadoop/doclinks', 'icon', '-1');
