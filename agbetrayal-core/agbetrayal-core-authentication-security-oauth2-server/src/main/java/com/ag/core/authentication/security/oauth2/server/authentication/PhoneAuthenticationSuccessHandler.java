package com.ag.core.authentication.security.oauth2.server.authentication;

import com.ag.core.authentication.security.oauth2.AuthenticationType;
import com.ag.core.web.Webs;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 手机号登陆认证成功后的处理
 *
 * @author agbetrayal
 * @date 2019-7-1 16:38
 */
@RequiredArgsConstructor
public class PhoneAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * 客户端 id
     */
    private final String clientId;

    /**
     * client Secret
     */
    private final String clientSecret;

    /**
     * authorizationServerTokenServices
     */
    private final AuthorizationServerTokenServices authorizationServerTokenServices;

    /**
     * clientDetailsService
     */
    private final ClientDetailsService clientDetailsService;

    /**
     * passwordEncoder
     */
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        if (Webs.isAndroid(request) || Webs.isIPhone(request) || Webs.isAjax(request)) {
            var clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (null == clientDetails) {
                throw new UnapprovedClientAuthenticationException("clientId不存在:" + clientId);
            } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
            }
            var tokenRequest = new TokenRequest(Collections.emptyMap(), clientId, clientDetails.getScope(),
                    AuthenticationType.password.name());// 这里的授权码模式可以随便写一个
            var oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            var oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            var token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            Webs.writeJson(response, HttpServletResponse.SC_OK, token);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
