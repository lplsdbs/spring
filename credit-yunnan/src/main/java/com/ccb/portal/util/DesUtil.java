package com.ccb.portal.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by as on 2018/10/24.
 */

public class DesUtil {

    private static String password=PropertiesUtil.getPropertierByKey("config.webPsaaword");
    /**
     * 加密
     * @param   dataStr     原文
     *
     * @return  密文
     */

    public static String encrypt(String dataStr) {
        try{
            byte[] datasource = dataStr.getBytes();
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory =
                    SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            byte[] res =  cipher.doFinal(datasource);
            return byteToHexString(res);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        String password="lpl1234444444444";
//        KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
//        keyGenerator.init(16);
//        SecretKey secretKey=keyGenerator.generateKey();
//        byte[]bytes=secretKey.getEncoded();

        String en=encrypt("{\"Cst_id\":\"1\",\"Cst_type\":\"企业\",\"Cst_identity_type\":\"12321\",\"Cst_identity_id\":\"123213\"}");
        System.out.println(en);
        try {
            String de=decrypt(en);
            System.out.println(de);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 解密
     * @param   dataStr     密文
     * @param
     * @return 明文
     * @throws Exception    异常
     */
    public static String decrypt(String dataStr)
            throws Exception {
        byte[] src = hexStringToBytes(dataStr);
        //DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        //创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        //创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        //将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        //真正开始解密操作
        byte[] res = cipher.doFinal(src);
        return new String(res,"UTF-8");
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
