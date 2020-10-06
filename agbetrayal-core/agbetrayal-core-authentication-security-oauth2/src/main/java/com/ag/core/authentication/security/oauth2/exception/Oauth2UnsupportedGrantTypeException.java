package com.ag.core.authentication.security.oauth2.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;

/**
 * @author agbetrayal
 * @date 2018-08-20 08:44
 */
@SuppressWarnings("serial")
@JsonSerialize(using = Oauth2ExceptionSerializer.OAuth2UnsupportedGrantedExceptionJackson2Serializer.class)
public class Oauth2UnsupportedGrantTypeException extends UnsupportedGrantTypeException {

    public Oauth2UnsupportedGrantTypeException(String msg) {
        super(msg);
    }

    public Oauth2UnsupportedGrantTypeException(String msg, Throwable t) {
        super(msg, t);
    }
}
