package com.ag.core.authentication.security.oauth2.server.logout;

import com.ag.core.authentication.security.oauth2.utils.AccessTokenUtils;
import com.ag.core.commons.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class ConsumerTokenLogoutHandler implements LogoutHandler {

    private final ConsumerTokenServices consumerTokenServices;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var accessToken = AccessTokenUtils.getAccessToken(request);
        if (StringUtils.isNotEmpty(accessToken)) {
            consumerTokenServices.revokeToken(accessToken);
        }
    }
}
