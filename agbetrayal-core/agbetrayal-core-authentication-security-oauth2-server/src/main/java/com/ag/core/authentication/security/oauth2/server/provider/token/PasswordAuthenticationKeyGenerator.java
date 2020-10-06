package com.ag.core.authentication.security.oauth2.server.provider.token;

import com.ag.core.authentication.security.oauth2.AuthenticationType;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Collections;
import java.util.Map;

public class PasswordAuthenticationKeyGenerator extends AbstractAuthenticationKeyGenerator {

    public PasswordAuthenticationKeyGenerator() {
        super(AuthenticationType.password.name());
    }

    @Override
    protected Map<String, ?> extractKeyMap(OAuth2Authentication authentication) {
        return Collections.singletonMap(OAuth2Utils.CLIENT_ID, authentication.getOAuth2Request().getClientId());
    }
}
