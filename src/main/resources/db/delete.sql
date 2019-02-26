use filemanager;


delete from tb_file_version;
delete from tb_file_log;
delete from tb_file_keyword;
delete from tb_current_file_log;
delete from tb_file;
delete from tb_path where pathid > 5;

# 删除文件夹下文件
rm -rf /home/hadoop/doclinks/*
mkdir -p /home/hadoop/doclinks/priavte
mkdir -p /home/hadoop/doclinks/icon