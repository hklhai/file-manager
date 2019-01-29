# File Management


---
> HK  
> linh@bjhxqh.com





### 部署修改
1. 设置tomcat.ip、tomcat.port (如果需要下载链接，加密后数据无法直接下载，本项需要配置server.xml中Context)
```
<Context docBase="/home/hadoop/doclinks" path="/file" reloadable="true" source="org.eclipse.jst.jee.server:file"/>
```
2. 设置com.hxqh.filemanager.upload与com.hxqh.filemanager.file.download
3. 新建目录
```
mkdir -p /home/hadoop/doclinks
mkdir -p /home/hadoop/doclinks/priavte
```
4. 将jdk中 JAVA_HOME/jre/lib/security/中的jar替换为resources/jar/local_policy.jar;resources/jar/US_export_policy.jar

