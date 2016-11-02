package com.andaily.zhishifenzi.web.context;

import com.andaily.zhishifenzi.web.utils.CookieUserAssistant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices.DEFAULT_PARAMETER;

/**
 * @author Shengzhao Li
 */
public class WdcyRememberMeFilter extends UsernamePasswordAuthenticationFilter {

    private static Logger log = LoggerFactory.getLogger(WdcyRememberMeFilter.class);

    public WdcyRememberMeFilter() {
        //The uri value from security.xml  #form-login > login-processing-url
        setFilterProcessesUrl("/signin");
        log.info("Initial AndailyRememberMeFilter success.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (requiresAuthentication(request, response)) {
            String username = obtainUsername(request);

            if (StringUtils.hasText(username)) {
                log.debug("Save username [" + username + "] and password to cookie");
                CookieUserAssistant cookieUserAssistant = new CookieUserAssistant(request);
                cookieUserAssistant.save(response, username, "");
            }

        }
        chain.doFilter(req, res);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        boolean result = super.requiresAuthentication(request, response);
        if (result) {
            // check remember-me parameter
            return ServletRequestUtils.getBooleanParameter(request, DEFAULT_PARAMETER, false);
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() {
        //Ignore
    }
}
