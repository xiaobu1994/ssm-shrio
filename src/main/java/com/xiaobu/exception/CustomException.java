package com.xiaobu.exception;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/12/17 16:01
 * @description V1.0
 */
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1750343959640944922L;

    public CustomException(String message) {
        super(message);
    }
}
