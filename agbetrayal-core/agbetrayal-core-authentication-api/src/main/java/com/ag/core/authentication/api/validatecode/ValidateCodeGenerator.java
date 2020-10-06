package com.ag.core.authentication.api.validatecode;

/**
 * 验证码生成器
 *
 * @author zhengaiguo
 * @date 2018-07-27 13:47
 */
public interface ValidateCodeGenerator<C extends ValidateCode> {

    C generate();
}
