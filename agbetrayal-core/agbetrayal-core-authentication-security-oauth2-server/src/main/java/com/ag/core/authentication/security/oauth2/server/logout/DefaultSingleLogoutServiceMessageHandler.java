package com.ag.core.authentication.security.oauth2.server.logout;

import com.ag.core.authentication.security.oauth2.server.TokenRegistry;
import com.ag.core.authentication.security.oauth2.server.http.HttpClient;
import com.ag.core.authentication.security.oauth2.server.http.LogoutHttpMessage;
import com.ag.core.commons.util.CollectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author agbetrayal
 * @date 2019-5-18 11:56
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultSingleLogoutServiceMessageHandler implements SingleLogoutServiceMessageHandler {

    private final HttpClient httpClient;

    private final TokenRegistry tokenRegistry;

    private final ConsumerTokenServices consumerTokenServices;

    @Setter
    private LogoutMessageCreator logoutMessageCreator = new SamlCompliantLogoutMessageCreator();

    @Setter
    private boolean asynchronous = true;

    @Override
    public void handle(String tokenValue) {
        var logoutRequests = tokenRegistry.destroy(tokenValue);
        consumerTokenServices.revokeToken(tokenValue);
        if (CollectionUtils.isNotEmpty(logoutRequests)) {
            logoutRequests.forEach(this::performBackChannelLogout);
        }
    }

    private void performBackChannelLogout(LogoutRequest logoutRequest) {
        var logoutMessage = logoutMessageCreator.create(logoutRequest);
        try {
            var url = new URL(logoutRequest.getLogoutURL());
            final var msg = new LogoutHttpMessage(url, logoutMessage, asynchronous);
            log.debug("Prepared logout message to send is [{}]. Sending...", msg);
            httpClient.sendMessageToEndPoint(msg);
        } catch (MalformedURLException e) {
            log.warn("Can not create URL for LogoutUrL: {}", logoutRequest.getLogoutURL());
        }
    }
}
