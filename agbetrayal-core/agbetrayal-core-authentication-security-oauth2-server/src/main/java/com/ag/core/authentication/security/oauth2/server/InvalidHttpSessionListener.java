package com.ag.core.authentication.security.oauth2.server;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@RequiredArgsConstructor
public class InvalidHttpSessionListener implements HttpSessionListener {

    private final TokenRegistry tokenRegistry;

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        tokenRegistry.destroy(se.getSession().getId());
    }
}
