package com.ag.core.authentication.weixin;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @author agbetrayal
 * @date 2019-7-18 18:01
 */
public class WeiXinAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WeiXinAuthenticationDetails> {

    @Override
    public WeiXinAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new WeiXinAuthenticationDetails(context);
    }
}
