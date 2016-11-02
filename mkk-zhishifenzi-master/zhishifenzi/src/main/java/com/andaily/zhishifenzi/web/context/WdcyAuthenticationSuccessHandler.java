package com.andaily.zhishifenzi.web.context;

import com.andaily.zhishifenzi.domain.shared.security.WdcyUserDetails;
import com.andaily.zhishifenzi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shengzhao Li
 */
public class WdcyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(WdcyAuthenticationSuccessHandler.class);

    @Autowired
    private UserService userService;

    public WdcyAuthenticationSuccessHandler() {
        setDefaultTargetUrl("/b/index.zsfz");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        recordLoginTime(authentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void recordLoginTime(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof WdcyUserDetails) {
            WdcyUserDetails userDetails = (WdcyUserDetails) principal;
            logger.debug("{} already logged", userDetails.user());
            userService.recordLastLoginTime(userDetails.user());
        }
    }
}