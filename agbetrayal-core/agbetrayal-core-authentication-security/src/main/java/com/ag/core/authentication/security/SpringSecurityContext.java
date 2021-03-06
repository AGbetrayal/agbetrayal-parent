package com.ag.core.authentication.security;

import com.ag.core.authentication.api.SecurityContext;
import com.ag.core.authentication.api.UserPrincipal;
import lombok.var;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 使用 Spring Security 获取当前登陆用户信息
 *
 * @author agbetrayal
 */
public class SpringSecurityContext implements SecurityContext {

    @Override
    public UserPrincipal getPrincipal() {
        if (isAuthenticated()) {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            return (UserPrincipal) authentication.getPrincipal();
        }
        throw new AuthenticationServiceException("未认证的用户");
    }

    /**
     * 当前用户是否有登陆，不包括匿名用户
     *
     * @return 登陆返回true, 否则返回false
     */
    @Override
    public boolean isAuthenticated() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }

}
