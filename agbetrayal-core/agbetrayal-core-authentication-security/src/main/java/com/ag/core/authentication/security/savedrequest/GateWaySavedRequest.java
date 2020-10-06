package com.ag.core.authentication.security.savedrequest;

import com.ag.core.commons.util.StringUtils;
import lombok.var;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author agbetrayal
 * @date 2018-08-28 10:28
 */
public class GateWaySavedRequest extends DefaultSavedRequest {

    /**
     *
     */
    private static final long serialVersionUID = -5777948680216763455L;

    /**
     *
     */
    private final String gateWayUrl;

    public GateWaySavedRequest(String gateWayUrl, HttpServletRequest request, PortResolver portResolver) {
        super(request, portResolver);
        this.gateWayUrl = gateWayUrl;
    }

    @Override
    public String getRedirectUrl() {
        var requestURI = super.getRequestURI();
        if (!StringUtils.startsWith(requestURI, "/")) {
            requestURI = "/" + requestURI;
        }
        return gateWayUrl + requestURI;
    }

    @Override
    public String getRequestURL() {
        return gateWayUrl;
    }
}
