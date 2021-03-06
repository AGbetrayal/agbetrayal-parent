package com.ag.core.authentication.security.oauth2.server.session;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * <pre>
 * session 认证策略，认证成功后将 session id 替换
 *
 * 并在配置文件中添加如下Bean:
 *     /@Bean
 *     public ApplicationListener<SessionFixationProtectionEvent> sessionFixationProtectionEvent(){
 *     	return event -> {
 *     		AbstractAuthenticationToken authentication = (AbstractAuthenticationToken)event.getAuthentication();
 *     		authentication.setDetails(new WebAuthenticationDetails(Webs.getHttpServletRequest()));
 *                        };
 *     }
 * </pre>
 *
 * @see WebAuthenticationDetails#getSessionId()
 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#authenticationDetailsSource
 * @see org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy
 */
@Slf4j
public class CreateSessionAuthenticationStrategy implements SessionAuthenticationStrategy, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    private final Method changeSessionIdMethod;

    /**
     * 是否允许创建 session
     */
    @Setter
    private boolean alwaysCreateSession = true;

    public CreateSessionAuthenticationStrategy() {
        var changeSessionIdMethod = ReflectionUtils
                .findMethod(HttpServletRequest.class, "changeSessionId");
        if (changeSessionIdMethod == null) {
            throw new IllegalStateException(
                    "HttpServletRequest.changeSessionId is undefined. Are you using a Servlet 3.1+ environment?");
        }
        this.changeSessionIdMethod = changeSessionIdMethod;
    }

    private HttpSession applySessionFixation(HttpServletRequest request) {
        ReflectionUtils.invokeMethod(this.changeSessionIdMethod, request);
        return request.getSession();
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        var hadSessionAlready = request.getSession(false) != null;
        if (!hadSessionAlready && !alwaysCreateSession) {
            return;
        }
        var session = request.getSession();
        var originalSessionId = session.getId();
        if (hadSessionAlready && request.isRequestedSessionIdValid()) {
            String newSessionId;
            Object mutex = WebUtils.getSessionMutex(session);
            synchronized (mutex) {
                session = applySessionFixation(request);
                newSessionId = session.getId();
            }

            if (originalSessionId.equals(newSessionId)) {
                log.warn("Your servlet container did not change the session ID when a new session was created. You will"
                        + " not be adequately protected against session-fixation attacks");
            }
        }
        pushSessionEvent(originalSessionId, session, authentication);
    }

    private void pushSessionEvent(String originalSessionId, HttpSession newSession, Authentication auth) {
        if (null != applicationEventPublisher) {
            applicationEventPublisher.publishEvent(new SessionFixationProtectionEvent(auth,
                    originalSessionId, newSession.getId()));
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
