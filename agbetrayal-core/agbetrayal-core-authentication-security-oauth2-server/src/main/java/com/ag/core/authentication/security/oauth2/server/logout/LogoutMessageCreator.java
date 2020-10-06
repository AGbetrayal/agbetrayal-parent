package com.ag.core.authentication.security.oauth2.server.logout;

/**
 * @author agbetrayal
 * @date 2019-5-18 11:49
 */
public interface LogoutMessageCreator {

    String create(LogoutRequest logoutRequest);
}
