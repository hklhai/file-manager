package com.hxqh.filemanager;

import com.hxqh.filemanager.util.FileUtil;
import com.hxqh.filemanager.util.Sm4Util;

import java.io.File;

/**
 * Created by Ocean lin on 2018/12/29.
 *
 * @author Ocean lin
 */
public class Sm4FileTest {

    public static void main(String[] args) {
        try {
//            String input = "D:\\testAES\\pom.xml";
//            String encode = "D:\\testAES\\encode.xml";
//            String decode = "D:\\testAES\\decode.xml";

            String input = "D:\\testAES\\1.png";
            String encode = "D:\\testAES\\encode.png";
            String decode = "D:\\testAES\\decode.png";

            byte[] aByte = FileUtil.getByte(new File(input));
            // 自定义的32位16进制密钥
            String key = "86C63180C2806ED1F47B859DE501200B";
            byte[] bytes = Sm4Util.encryptEcb(key, aByte);

            FileUtil.writeFileByByte(encode, bytes);
            byte[] encodeBytes = FileUtil.getByte(new File(encode));

            byte[] decryptEcb = Sm4Util.decryptEcb(key, encodeBytes);

            FileUtil.writeFileByByte(decode, decryptEcb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
