delete from tb_file_version;
delete from tb_file_log;
delete from tb_file_keyword;
delete from tb_current_file_log;
delete from tb_file;
delete from tb_path;

INSERT INTO `tb_path` VALUES (3, NULL, 'C:/doclinks', ' ', NULL, -1);
INSERT INTO `tb_path` VALUES (4, 3, 'C:/doclinks/private', 'C:/doclinks', 'private', 0);
INSERT INTO `tb_path` VALUES (5, 3, 'C:/doclinks/icon', 'C:/doclinks', 'icon', -1);