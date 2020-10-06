package com.ag.core.authentication.security.authentication.sms;

import com.ag.core.authentication.api.PostAuthenticationHandler;
import com.ag.core.authentication.api.UserPrincipal;
import com.ag.core.commons.util.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 短信验证 Provider
 *
 * @author agbetrayal
 * @date 2018-07-26 16:41
 */
@RequiredArgsConstructor
public class SMSAuthenticationProvider implements AuthenticationProvider {

    private final PostAuthenticationHandler<UserPrincipal, String> authenticationHandler;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SMSAuthenticationToken token = (SMSAuthenticationToken) authentication;
        UserPrincipal principal = authenticationHandler.handler(token.getPrincipal().toString());
        AssertUtils.notNull(principal, "principal Must not be null.");
        SMSAuthenticationToken authenticationToken = new SMSAuthenticationToken(principal, null);
        authenticationToken.setDetails(token.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SMSAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
