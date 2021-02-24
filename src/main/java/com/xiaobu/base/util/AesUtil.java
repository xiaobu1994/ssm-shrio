package com.xiaobu.base.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES加密
     *
     * @param passwd
     *         加密的密钥
     * @param content
     *         需要加密的字符串
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String passwd, String content)  {
        // 创建密码器
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

        // 初始化为加密模式的密码器
        try {
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(passwd));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        // 加密
        byte[] result = new byte[0];
        try {
            result = cipher.doFinal(byteContent);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        //通过Base64转码返回
        return org.apache.commons.codec.binary.Base64.encodeBase64String(result);
    }

    /**
     * AES解密
     *
     * @param passwd
     *         加密的密钥
     * @param encrypted
     *         已加密的密文
     * @return 返回解密后的数据
     */
    public static String decrypt(String passwd, String encrypted)  {
        //实例化
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        //使用密钥初始化，设置为解密模式
        try {
            assert cipher != null;
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(passwd));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        //执行操作
        byte[] result = new byte[0];
        try {
            result = cipher.doFinal(Base64.decodeBase64(encrypted));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(result, StandardCharsets.UTF_8);
    }

    /**
     * 生成加密秘钥
     */
    private static SecretKeySpec getSecretKey(final String password)  {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // javax.crypto.BadPaddingException: Given final block not properly padded解决方案
        // https://www.cnblogs.com/zempty/p/4318902.html - 用此法解决的
        // https://www.cnblogs.com/digdeep/p/5580244.html - 留作参考吧
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert random != null;
        random.setSeed(password.getBytes());
        //AES 要求密钥长度为 128
        assert kg != null;
        kg.init(128, random);

        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        // 转换为AES专用密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }


}
