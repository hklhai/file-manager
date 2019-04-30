package com.hxqh.filemanager;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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

    private static HttpEntity getMutipartEntry(Map<String, Object> param, File file) throws UnsupportedEncodingException {
        if (file == null) {
            throw new IllegalArgumentException("文件不能为空");
        }
        FileBody bin = new FileBody(file);

        StringBody userid = new StringBody("1", ContentType.create("text/plain", Consts.UTF_8));
        StringBody username = new StringBody("admin", ContentType.create("text/plain", Consts.UTF_8));
        StringBody deptid = new StringBody("1", ContentType.create("text/plain", Consts.UTF_8));
        StringBody deptfullname = new StringBody("admin", ContentType.create("text/plain", Consts.UTF_8));
        StringBody appid = new StringBody("1", ContentType.create("text/plain", Consts.UTF_8));
        StringBody appname = new StringBody("important_problem", ContentType.create("text/plain", Consts.UTF_8));
        StringBody recordid = new StringBody("1", ContentType.create("text/plain", Consts.UTF_8));
//        StringBody pathid = new StringBody("202", ContentType.create("text/plain", Consts.UTF_8));

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        HttpEntity reqEntity = multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("files", bin)
                .addPart("userid", userid)
                .addPart("username", username)
                .addPart("deptid", deptid)
                .addPart("deptfullname", deptfullname)
                .addPart("appid", appid)
                .addPart("appname", appname)
                .addPart("recordid", recordid)
//                .addPart("pathid", pathid)
                .setCharset(CharsetUtils.get("UTF-8")).build();

        return reqEntity;
    }


    public static void main(String[] args) throws IOException {
        File file = new File("D://x.png");
        System.out.println(file.exists());
        Map<String, Object> param = new HashMap<>();
//        String name = new String("http://127.0.0.1:8099/file/uploadfile".getBytes("UTF-8"), "iso-8859-1");
        String name = new String("http://spark2:8088/file/uploadfile".getBytes("UTF-8"), "iso-8859-1");
        String res = postFile(name, param, file);
        JSONObject result = JSONObject.parseObject(res);
        System.out.println(result);
    }

}
