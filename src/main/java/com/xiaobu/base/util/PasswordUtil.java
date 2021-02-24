package com.xiaobu.base.util;

public class PasswordUtil {

    /**
     * AES 加密
     * @param password
     *         未加密的密码
     * @param salt
     *         盐值，默认使用用户名就可
     */
    public static String encrypt(String password, String salt)  {
        try {
            return AesUtil.encrypt(Md5Util.MD5(salt + CommonConst.THW_SECURITY_KEY), password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密
     * @param encryptPassword
     *         加密后的密码
     * @param salt
     *         盐值，默认使用用户名就可
     */
    public static String decrypt(String encryptPassword, String salt) {
        return AesUtil.decrypt(Md5Util.MD5(salt + CommonConst.THW_SECURITY_KEY), encryptPassword);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("admin","123456"));
    }
}
