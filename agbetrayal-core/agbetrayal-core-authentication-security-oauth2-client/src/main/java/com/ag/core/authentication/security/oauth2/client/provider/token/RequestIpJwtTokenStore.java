package com.ag.core.authentication.security.oauth2.client.provider.token;

import com.ag.core.authentication.security.oauth2.exception.IllegalClientIpTokenException;
import com.ag.core.commons.util.StringUtils;
import com.ag.core.web.Webs;
import lombok.Setter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Map;

/**
 * 限制在认证中心生成的 token中，加入了 {@link #ipKey} 的值，防止用户的token 被其它ip所使用
 *
 * @author agbetrayal
 * @date 2019-12-18 17:03
 */
public class RequestIpJwtTokenStore extends JwtTokenStore {

    @Setter
    private String ipKey = "ip";

    public RequestIpJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken oAuth2AccessToken = super.readAccessToken(tokenValue);
        Map<String, Object> map = oAuth2AccessToken.getAdditionalInformation();
        if (map.containsKey(ipKey) &&
                StringUtils.notEquals(String.valueOf(map.get(ipKey)), Webs.getRemoteAddr(Webs.getHttpServletRequest()))) {
            throw new IllegalClientIpTokenException("认证Token 被盗用");
        }
        return oAuth2AccessToken;
    }
}
