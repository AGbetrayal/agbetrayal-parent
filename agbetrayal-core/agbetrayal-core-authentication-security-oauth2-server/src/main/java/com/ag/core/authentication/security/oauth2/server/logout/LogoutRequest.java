package com.ag.core.authentication.security.oauth2.server.logout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author agbetrayal
 * @date 2019-5-18 13:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest implements Serializable {

    private String clientId;

    private String logoutURL;

    private String accessToken;

}
