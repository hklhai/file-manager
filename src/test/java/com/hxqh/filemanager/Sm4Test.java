package com.hxqh.filemanager;

import com.hxqh.filemanager.util.Sm4Utils;

import java.io.*;

/**
 * Created by Ocean lin on 2018/12/29.
 *
 * @author Ocean lin
 */
public class Sm4Test {

    public static void main(String[] args) throws IOException {
//        String plainText = "ssda你好你好ii&$$^##)*&6223';.,[]";
//        Sm4Utils sm4 = new Sm4Utils();
//        sm4.setSecretKey("99HDESaAhiHHugZz");
//        plainText.getBytes("UTF-8");
//        System.out.println("ECB模式");
//        String cipherText = sm4.encryptData_ECB(plainText);
//        System.out.println("密文: " + cipherText);
//        System.out.println("");
//
//        plainText = sm4.decryptData_ECB(cipherText);
//        System.out.println("明文: " + plainText);
//        System.out.println("===================================");


//        System.out.println("CBC模式");
//        sm4.setIv("UISwD9fW6cFh9SNS");
//        cipherText = sm4.encryptData_CBC(plainText);
//        System.out.println("密文: " + cipherText);
//        System.out.println("");
//
//        plainText = sm4.decryptData_CBC(cipherText);
//        System.out.println("明文: " + plainText);
        //PI4ke7HMoUMD/LOSHWX5/g==


        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\lenovo\\Desktop\\file-er\\db_v2.0.jpg"));
        Sm4Utils sm4 = new Sm4Utils();
        sm4.setSecretKey("99HDESaAhiHHugZz");
        int len = 1024;
        byte tmp[] = new byte[len];
        int i;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((i = inputStream.read(tmp, 0, len)) > 0) {
            baos.write(tmp, 0, i);
        }
        byte[] imgs = baos.toByteArray();

        System.out.println("ECB模式");
        String cipherText = sm4.encryptDataEcB(imgs);
        System.out.println("密文: " + cipherText);
        // 保存至文件系统
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\lenovo\\Desktop\\file-er\\db_v2.0-en.jpg"));
        fos.write(cipherText.getBytes("UTF-8"));
        inputStream.close();
        fos.close();

        FileInputStream inputStream2 = new FileInputStream(new File("C:\\Users\\lenovo\\Desktop\\file-er\\db_v2.0-en.jpg"));
        int len2 = 1024;
        byte tmp2[] = new byte[len2];
        int j = 0;
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        while ((i = inputStream2.read(tmp2, 0, len2)) > 0) {
            baos2.write(tmp2, 0, j);
        }
        byte[] imgs2 = baos.toByteArray();

        String plainText = sm4.decryptData_ECB(new String(imgs2, "UTF-8"));
        System.out.println("明文: " + plainText);
        System.out.println("===================================");

        FileOutputStream fos2 = new FileOutputStream(new File("C:\\Users\\lenovo\\Desktop\\file-er\\db_v2.0-de.jpg"));
        fos2.write(plainText.getBytes("UTF-8"));
        inputStream2.close();
        fos2.close();


    }
}
