package com.ag.core.service.exception;

import com.ag.core.commons.JsonResult;
import lombok.Getter;
import org.apache.http.HttpStatus;

/**
 * Http Status Code ：200
 *
 * @author zhengaiguo
 * @date 2018-08-03 09:52
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

    /**
     * http 状态码
     */
    @Getter
    private int statusCode = HttpStatus.SC_OK;

    /**
     * 返回数据
     */
    @Getter
    private final JsonResult<?> result;

    public ServiceException(String message) {
        super(message);
        this.result = JsonResult.error(message);
    }

    public ServiceException(JsonResult<?> result) {
        super(result.getMessage());
        this.result = result;
    }

    public ServiceException(int statusCode, JsonResult<?> result) {
        super(result.getMessage());
        this.result = result;
        this.statusCode = statusCode;

    }

    public ServiceException(String message, Throwable t) {
        super(message, t);
        this.result = JsonResult.error(message);
    }
}
