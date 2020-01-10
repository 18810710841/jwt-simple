package com.drj.jwt.jwtsimple.common;

/**
 * 封装API的错误码
 * @author drj
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
