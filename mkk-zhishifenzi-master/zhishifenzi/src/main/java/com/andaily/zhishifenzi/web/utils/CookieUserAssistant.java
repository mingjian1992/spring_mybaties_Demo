package com.andaily.zhishifenzi.web.utils;

import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.infrastructure.PasswordHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Assistant handle save, retrieve login user information to Cookie.
 *
 * @author Shengzhao Li
 * @see org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
 */
public class CookieUserAssistant extends TokenBasedRememberMeServices {

    public static final String USER_COOKIE_KEY = "zfeszsfezdessf";
    public static final String COOKIE_PASSWORD_DELIMITER = "__";

    private static Logger log = LoggerFactory.getLogger(CookieUserAssistant.class);

    private String username = "";
    private String password = "";

    private HttpServletRequest request;

    public CookieUserAssistant(HttpServletRequest request) {
        this.request = request;
        this.setCookieName(USER_COOKIE_KEY);
    }

    /**
     * Retrieve username,password from Cookie.
     *
     * @return Current CookieUserAssistant
     */
    public CookieUserAssistant retrieve() {
        String rememberMeCookie = extractRememberMeCookie(request);
        if (StringUtils.hasText(rememberMeCookie)) {
            String[] cookieTokens = decodeCookie(rememberMeCookie);
            //username: expiry time: password: key
            if (cookieTokens.length > 2) {
                boolean tokenExpired = this.isTokenExpired(Long.valueOf(cookieTokens[1]));
                if (!tokenExpired) {
                    this.username = cookieTokens[0];
                    //decrypt, the last one is password
                    this.password = PasswordHandler.decryptReversiblePassword(reversiblePassword(cookieTokens));
                } else {
                    log.debug("The cookie saved data is expired");
                }
            } else {
                log.debug("The cookie length expected 3 or more, but it is " + cookieTokens.length);
            }
        }
        return this;
    }

    //Cut suffix
    private String reversiblePassword(String[] cookieTokens) {
        final String cookieToken = cookieTokens[cookieTokens.length - 1];
        return cookieToken.substring(0, cookieToken.indexOf(COOKIE_PASSWORD_DELIMITER));
    }

    /**
     * Save username,password to request cookie
     *
     * @param response HttpServletResponse
     * @param username Username
     * @param password Password
     * @return Current   CookieUserAssistant
     */
    public CookieUserAssistant save(HttpServletResponse response, String username, String password) {
        // SEC-949
        long expiryTime = System.currentTimeMillis() + 1000L * TWO_WEEKS_S;
        String signatureValue = this.makeTokenSignature(expiryTime, username, password);

        String[] tokens = {username, Long.toString(expiryTime), signatureValue};
        setCookie(tokens, TWO_WEEKS_S, request, response);
        return this;
    }

    @Override
    protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        //encrypt password
        String encryptPass = PasswordHandler.encryptReversiblePassword(password);
        return (username + ":" + tokenExpiryTime + ":" + encryptPass + COOKIE_PASSWORD_DELIMITER + GuidGenerator.generate());
    }

    //Clean saved cookie
    public CookieUserAssistant clean(HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (this.getCookieName().equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
