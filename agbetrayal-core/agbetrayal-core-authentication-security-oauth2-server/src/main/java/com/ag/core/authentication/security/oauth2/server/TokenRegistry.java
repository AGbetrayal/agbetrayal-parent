package com.ag.core.authentication.security.oauth2.server;

import com.ag.core.authentication.security.oauth2.server.logout.LogoutRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;


/**
 * token 存储
 */
public interface TokenRegistry {

    /**
     * 添加 token
     *
     * @param authentication authentication
     * @param accessToken    accessToken
     */
    void addAccessToken(OAuth2Authentication authentication, OAuth2AccessToken accessToken);

    /**
     * 销毁
     *
     * @param tokenValue
     * @return
     */
    List<LogoutRequest> destroy(String tokenValue);


}
