package com.ag.core.authentication.security.oauth2.exception;

import com.ag.core.commons.util.SpringContextHolder;
import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;


/**
 * 异常信息处理器
 *
 * @author agbetrayal
 * @date 2018-08-20 08:55
 * @see DefaultWebResponseExceptionTranslator
 */
public class Oauth2DefaultWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        if (e instanceof UnsupportedGrantTypeException || e instanceof InvalidClientException) {//自定义异常
            return handleOAuth2Exception(new Oauth2UnsupportedGrantTypeException(e.getMessage(), e));
        }
        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
        Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(
                OAuth2Exception.class, causeChain);
        if (ase != null) {
            if (ase instanceof InvalidTokenException) {
                return handleOAuth2Exception(new CustomInvalidTokenException(SpringContextHolder.getMessage("invalid.token")));
            }
            return handleOAuth2Exception((OAuth2Exception) ase);
        }
        ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
                causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
        }
        ase = (Exception) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase instanceof AccessDeniedException) {
            return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
        }

        ase = (Exception) throwableAnalyzer
                .getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase instanceof HttpRequestMethodNotSupportedException) {
            return handleOAuth2Exception(new MethodNotAllowedException(ase.getMessage(), ase));
        }

        return handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));

    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {
        var status = e.getHttpErrorCode();
        var headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }
        return new ResponseEntity<>(e, headers, HttpStatus.valueOf(status));
    }

    public void setThrowableAnalyzer(ThrowableAnalyzer throwableAnalyzer) {
        this.throwableAnalyzer = throwableAnalyzer;
    }

    @SuppressWarnings("serial")
    private static class ForbiddenException extends OAuth2Exception {

        public ForbiddenException(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return SpringContextHolder.getMessage("oauth2.error.403.message");
        }

        public int getHttpErrorCode() {
            return 403;
        }

    }

    @SuppressWarnings("serial")
    private static class ServerErrorException extends OAuth2Exception {

        public ServerErrorException(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return SpringContextHolder.getMessage("oauth2.error.500.message");
        }

        public int getHttpErrorCode() {
            return 500;
        }

    }

    @SuppressWarnings("serial")
    private static class UnauthorizedException extends OAuth2Exception {

        public UnauthorizedException(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return SpringContextHolder.getMessage("oauth2.error.401.message");
        }

        public int getHttpErrorCode() {
            return 401;
        }

    }

    @SuppressWarnings("serial")
    private static class MethodNotAllowedException extends OAuth2Exception {

        public MethodNotAllowedException(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return SpringContextHolder.getMessage("oauth2.error.405.message");
        }

        public int getHttpErrorCode() {
            return 405;
        }
    }
}