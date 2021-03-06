package com.ag.core.authentication.api.validatecode;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 验证码处理器
 *
 * @author zhengaiguo
 * @date 2018-07-27 13:37
 */
public interface ValidateCodeProcessor {

    String DEFAULT_CODE_PARAMETER_NAME = "validateCode";

    /**
     * 生成验证码前缀
     */
    String VALIDATE_CODE_PREFIX = "VALIDATE_CODE_PREFIX_";

    /**
     * 创建验证码
     *
     * @param request request
     * @return 验证码
     */
    String create(ServletWebRequest request) throws IOException, ServletRequestBindingException;

    /**
     * 校验验证码
     *
     * @param request request
     * @throws ValidateCodeException 验证码验证异常
     */
    void validate(ServletWebRequest request) throws ValidateCodeException, ServletRequestBindingException;
}
