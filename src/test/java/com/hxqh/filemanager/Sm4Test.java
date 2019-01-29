package com.hxqh.filemanager;

import com.hxqh.filemanager.util.Sm4Util;

/**
 * Created by Ocean lin on 2018/12/29.
 *
 * @author Ocean lin
 */
public class Sm4Test {

    public static void main(String[] args) {
        try {
            String json = "{\"name\":\"Marydon\",\"website\":\"http://www.cnblogs.com/Marydon20170307\"}";
            // 自定义的32位16进制密钥
            String key = "86C63180C2806ED1F47B859DE501200B";
            String cipher = Sm4Util.encryptEcb(key, json);
            System.out.println(cipher);
            System.out.println(Sm4Util.verifyEcb(key, cipher, json));
            json = Sm4Util.decryptEcb(key, cipher);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
