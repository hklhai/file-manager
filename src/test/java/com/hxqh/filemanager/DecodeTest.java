package com.hxqh.filemanager;

import com.hxqh.filemanager.common.IConstants;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

/**
 * Created by Ocean lin on 2018/12/25.
 *
 * @author Ocean lin
 */
public class DecodeTest {


    public static void main(String[] args) throws Exception {
        File fileKey = new File("D:/DOCLINKS/a.text");
        byte[] key = new byte[(int) fileKey.length()];
        FileInputStream fis = new FileInputStream(fileKey);
        fis.read(key);
        //根据给定的字节数组(密钥数组)构造一个AES密钥。
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        //实例化一个密码器（CBC模式）
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //初始化密码器
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, new IvParameterSpec(IConstants.IV.getBytes()));

        //加密文件流
        FileInputStream inputStream = new FileInputStream("D:\\DOCLINKS\\2018-12\\2018-12-25_115157_2125ae28-d255-44fd-95fb-76cb55136dd8.txt");
        //解密文件流
        FileOutputStream outputStream = new FileOutputStream("D:\\2.txt");
        //以解密流写出文件
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = inputStream.read(buffer)) >= 0) {
            cipherOutputStream.write(buffer, 0, r);
        }
        cipherOutputStream.close();
        inputStream.close();
        outputStream.close();

    }
}
