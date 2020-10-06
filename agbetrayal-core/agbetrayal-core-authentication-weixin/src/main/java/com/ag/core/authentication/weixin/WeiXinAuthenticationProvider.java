package com.ag.core.authentication.weixin;

import com.ag.core.authentication.api.PostAuthenticationHandler;
import com.ag.core.authentication.api.UserPrincipal;
import com.ag.core.authentication.api.enums.ThirdAccountType;
import com.ag.core.commons.util.AssertUtils;
import lombok.RequiredArgsConstructor;
import lombok.var;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;
import java.util.Map;


/**
 * 微信二维码认证提供者
 *
 * @author agbetrayal
 * @date 2018年2月8日上午11:25:39
 */
@RequiredArgsConstructor
public class WeiXinAuthenticationProvider implements AuthenticationProvider {

    private final PostAuthenticationHandler<UserPrincipal, WxMpUser> authenticationHandler;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var wxMpUser = (WxMpUser) authentication.getPrincipal();
        UserPrincipal userPrincipal;
        if (null == authenticationHandler) {
            userPrincipal = new UserPrincipal(null, wxMpUser.getNickname(), null);
            Map<String, String> thirdOpenId = new HashMap<>();
            thirdOpenId.put(ThirdAccountType.wx.name(), wxMpUser.getOpenId());
            // userPrincipal.setThirdOpenId(Map.of(ThirdAccountType.wx.name(), wxMpUser.getOpenId()));
            userPrincipal.setThirdOpenId(thirdOpenId);
        } else {
            userPrincipal = authenticationHandler.handler((WxMpUser) authentication.getPrincipal());
            AssertUtils.notNull(userPrincipal, "principal Must not be null.");
        }
        return new WeiXinAuthenticationToken(userPrincipal, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WeiXinAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
