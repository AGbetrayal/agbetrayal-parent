package com.ag.core.authentication.security.oauth2.client.session;

import lombok.RequiredArgsConstructor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 单点退出 过滤器
 *
 * @author agbetrayal
 * @date 2019-5-18 10:47
 */
@RequiredArgsConstructor
public class SingleSignOutFilter extends HttpFilter {

    private final SingleSignOutHandler singleSignOutHandler;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (singleSignOutHandler.process(request)) {
            chain.doFilter(request, response);
        }
    }

}
