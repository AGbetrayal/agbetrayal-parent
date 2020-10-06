package com.ag.core.authentication.security.oauth2.exception;

import com.ag.core.commons.Status;
import com.ag.core.commons.util.ArrayUtils;
import com.ag.core.commons.util.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.var;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;

/**
 * @author zag
 * @date 2018-12-28 15:29
 */
@SuppressWarnings("serial")
public class Oauth2ExceptionSerializer<T extends OAuth2Exception> extends StdSerializer<T> {

    protected Oauth2ExceptionSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        // 统一异常，所有响应返三个字段，@see com.hk.commons.JsonResult
        gen.writeNumberField("statusCode", getStatus(value));
        gen.writeStringField("message", getMessage(value));
        gen.writeObjectField("data", getData(value));
        gen.writeEndObject();
    }

    /**
     * 获取data字段值
     *
     * @param exception exception
     * @see JsonResult#getData()
     */
    protected Object getData(T exception) {
        return null;
    }

    /**
     * 获取错误消息
     *
     * @param exception exception
     * @return 错误消息
     * @see JsonResult#getMessage()
     */
    protected String getMessage(T exception) {
        return exception.getMessage();
    }

    /**
     * @param exception
     * @return
     * @see JsonResult#getStatusCode()
     */
    protected int getStatus(T exception) {
        return Status.getOrder(Status.BAD_REQUEST);
    }

    public static class Oauth2ClientStatusExceptionJackson2Serializer
            extends Oauth2ExceptionSerializer<Oauth2ClientStatusException> {

        protected Oauth2ClientStatusExceptionJackson2Serializer() {
            super(Oauth2ClientStatusException.class);
        }
    }

    public static class Oauth2TokenControlExceptionJackson2Serializer
            extends Oauth2ExceptionSerializer<TokenControlException> {

        protected Oauth2TokenControlExceptionJackson2Serializer() {
            super(TokenControlException.class);
        }
    }

    public static class CustomInvalidTokenExceptionJackson2Serializer
            extends Oauth2ExceptionSerializer<CustomInvalidTokenException> {

        protected CustomInvalidTokenExceptionJackson2Serializer() {
            super(CustomInvalidTokenException.class);
        }
    }

    public static class IllegalClientIpTokenExceptionJackson2Serializer
            extends Oauth2ExceptionSerializer<IllegalClientIpTokenException> {

        protected IllegalClientIpTokenExceptionJackson2Serializer() {
            super(IllegalClientIpTokenException.class);
        }
    }

    public static class OAuth2UnsupportedGrantedExceptionJackson2Serializer
            extends Oauth2ExceptionSerializer<Oauth2UnsupportedGrantTypeException> {

        protected OAuth2UnsupportedGrantedExceptionJackson2Serializer() {
            super(Oauth2UnsupportedGrantTypeException.class);
        }

        /**
         * 获取错误消息
         *
         * @param exception exception
         * @return 错误消息
         */
        @Override
        protected String getMessage(Oauth2UnsupportedGrantTypeException exception) {
            var errorMessage = exception.getMessage();
            var delimitArr = StringUtils.delimitedListToStringArray(errorMessage, ":");
            if (ArrayUtils.isNotEmpty(delimitArr) && delimitArr.length == 2) {
                errorMessage = delimitArr[1];
            }
            return errorMessage;
        }
    }
}
