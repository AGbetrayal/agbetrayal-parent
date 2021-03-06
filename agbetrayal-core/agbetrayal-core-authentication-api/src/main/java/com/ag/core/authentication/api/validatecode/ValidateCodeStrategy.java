package com.ag.core.authentication.api.validatecode;

import org.springframework.web.context.request.RequestAttributes;

/**
 * 验证码存储器
 *
 * @author zhengaiguo
 * @date 2018-07-27 14:06
 */
public interface ValidateCodeStrategy {

    /**
     * 验证码存储
     *
     * @param request request
     * @param name    name
     * @param value   value
     */
    <C extends ValidateCode> void save(RequestAttributes request, String name, C value);

    /**
     * 获取验证码
     *
     * @param request request
     * @param name    name
     * @return value
     */
    <C extends ValidateCode> C get(RequestAttributes request, String name, Class<C> clazz);

    /**
     * 删除验证码
     *
     * @param request request
     * @param name    name
     */
    void remove(RequestAttributes request, String name);
}
