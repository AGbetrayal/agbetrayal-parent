package com.ag.core.authentication.weixin;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author agbetrayal
 * @date 2019-7-18 18:01
 */
public class WeiXinAuthenticationDetails extends WebAuthenticationDetails {

    public WeiXinAuthenticationDetails(HttpServletRequest request) {
        super(request);
    }
}
