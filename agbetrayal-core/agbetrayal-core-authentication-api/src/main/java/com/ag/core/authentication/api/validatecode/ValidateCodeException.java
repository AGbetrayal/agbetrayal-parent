package com.ag.core.authentication.api.validatecode;


import com.ag.core.authentication.api.AuthenticationException;

/**
 * 验证码异常
 *
 * @author zhengaiguo
 * @date 2018-07-26 15:39
 */
@SuppressWarnings("serial")
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
