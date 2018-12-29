package com.hxqh.filemanager;

import com.hxqh.filemanager.util.Sm4Utils;

import java.io.IOException;

/**
 * Created by Ocean lin on 2018/12/29.
 *
 * @author Ocean lin
 */
public class Sm4Test {

    public static void main(String[] args) throws IOException {
        String plainText = "ssda你好你好ii&$$^##)*&6223';.,[]";
        Sm4Utils sm4 = new Sm4Utils();
        sm4.setSecretKey("11HDESaAhiHHugDz");
        plainText.getBytes("UTF-8");
        System.out.println("ECB模式");
        String cipherText = sm4.encryptData_ECB(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptData_ECB(cipherText);
        System.out.println("明文: " + plainText);
        System.out.println("===================================");


//        System.out.println("CBC模式");
//        sm4.setIv("UISwD9fW6cFh9SNS");
//        cipherText = sm4.encryptData_CBC(plainText);
//        System.out.println("密文: " + cipherText);
//        System.out.println("");
//
//        plainText = sm4.decryptData_CBC(cipherText);
//        System.out.println("明文: " + plainText);
        //PI4ke7HMoUMD/LOSHWX5/g==
    }
}
