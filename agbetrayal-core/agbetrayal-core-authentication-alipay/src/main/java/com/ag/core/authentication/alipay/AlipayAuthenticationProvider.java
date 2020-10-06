package com.ag.core.authentication.alipay;

import com.ag.core.authentication.api.PostAuthenticationHandler;
import com.ag.core.authentication.api.UserPrincipal;
import com.ag.core.authentication.api.enums.ThirdAccountType;
import com.ag.core.commons.util.AssertUtils;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zag
 * @date 2019/3/5 17:50
 */
@RequiredArgsConstructor
public class AlipayAuthenticationProvider implements AuthenticationProvider {

    private final PostAuthenticationHandler<UserPrincipal, String> authenticationHandler;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var alipayResponse = (AlipayResponse) authentication.getPrincipal();
        UserPrincipal principal = null;
        if (null != authenticationHandler) {
            if (alipayResponse instanceof AlipayUserInfoShareResponse) {
                principal = authenticationHandler.handler(((AlipayUserInfoShareResponse) alipayResponse).getUserId());
            } else if (alipayResponse instanceof AlipaySystemOauthTokenResponse) {
                principal = authenticationHandler.handler(((AlipaySystemOauthTokenResponse) alipayResponse).getUserId());
            }
        } else {
            principal = new UserPrincipal();
            Map<String, String> thirdOpenId = new HashMap<>();
            if (alipayResponse instanceof AlipayUserInfoShareResponse) {
                AlipayUserInfoShareResponse shareResponse = (AlipayUserInfoShareResponse) alipayResponse;
                // principal.setThirdOpenId(Map.of(ThirdAccountType.ali.name(), shareResponse.getUserId()));  jdk9  用于简单的创建不可变的少量元素的集合
                thirdOpenId.put(ThirdAccountType.ali.name(), shareResponse.getUserId());
                // principal.setThirdOpenId(thirdOpenId);
                principal.setAccount(shareResponse.getUserId());
                principal.setRealName(shareResponse.getUserName());
                principal.setIconPath(shareResponse.getAvatar());
                principal.setEmail(shareResponse.getEmail());
                principal.setPhone(shareResponse.getPhone());
                //TODO 设置其它用户信息
            } else if (alipayResponse instanceof AlipaySystemOauthTokenResponse) {
                thirdOpenId.put(ThirdAccountType.ali.name(), ((AlipaySystemOauthTokenResponse) alipayResponse).getUserId());
                // principal.setThirdOpenId(Map.of(ThirdAccountType.ali.name(), ((AlipaySystemOauthTokenResponse) alipayResponse).getUserId()));
            }
            principal.setThirdOpenId(thirdOpenId);
        }
        AssertUtils.notNull(principal, "认证信息不能为空.");
        return new AlipayAuthenticationToken(principal, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AlipayAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
