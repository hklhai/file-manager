package com.hxqh.filemanager;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ocean lin on 2019/2/22.
 *
 * @author Ocean lin
 */
public class UploadDemo {

    private static final Logger log = LoggerFactory.getLogger(UploadDemo.class);


    public static String postFile(String url, Map<String, Object> param, File file) throws ClientProtocolException, IOException {
        String res = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(getMutipartEntry(param, file));


        CloseableHttpResponse response = httpClient.execute(httppost);
        HttpEntity entity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            res = EntityUtils.toString(entity, "UTF-8");
            response.close();
        } else {
            res = EntityUtils.toString(entity, "UTF-8");
            response.close();
            throw new IllegalArgumentException(res);
        }
        return res;
    }

    private static MultipartEntity getMutipartEntry(Map<String, Object> param, File file) throws UnsupportedEncodingException {
        if (file == null) {
            throw new IllegalArgumentException("文件不能为空");
        }
        FileBody fileBody = new FileBody(file);
        FormBodyPart filePart = new FormBodyPart("files", fileBody);
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart(filePart);

        Iterator<String> iterator = param.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            FormBodyPart field = new FormBodyPart(key, new StringBody((String) param.get(key)));
            multipartEntity.addPart(field);
        }

        multipartEntity.addPart("userid",new StringBody("100", Charset.forName("UTF-8")));
        multipartEntity.addPart("username",new StringBody("xxx", Charset.forName("UTF-8")));
        multipartEntity.addPart("deptid",new StringBody("200", Charset.forName("UTF-8")));
        multipartEntity.addPart("deptfullname",new StringBody("xxx", Charset.forName("UTF-8")));
        multipartEntity.addPart("appid",new StringBody("300", Charset.forName("UTF-8")));
        multipartEntity.addPart("appname",new StringBody("xxx", Charset.forName("UTF-8")));
        multipartEntity.addPart("recordid",new StringBody("400", Charset.forName("UTF-8")));
        multipartEntity.addPart("pathid",new StringBody("0", Charset.forName("UTF-8")));
        return multipartEntity;
    }


    public static void main(String[] args) throws IOException {
        File file = new File("D://search_text.json");
        System.out.println(file.exists());
        Map<String, Object> param = new HashMap<>();
        String res = postFile("http://127.0.0.1:8099/file/uploadfile", param, file);
        JSONObject result = JSONObject.parseObject(res);
        System.out.println(result);
    }

}
