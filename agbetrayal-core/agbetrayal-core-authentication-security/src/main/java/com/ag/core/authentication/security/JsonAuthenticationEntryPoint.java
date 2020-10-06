package com.ag.core.authentication.security;

import com.ag.core.commons.JsonResult;
import com.ag.core.commons.util.SpringContextHolder;
import com.ag.core.web.Webs;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author agbetrayal
 * @date 2019/12/16 14:15
 */
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Webs.writeJson(response, HttpServletResponse.SC_OK, JsonResult.unauthorized(SpringContextHolder.getMessage("user.unauthorized")));
    }
}
