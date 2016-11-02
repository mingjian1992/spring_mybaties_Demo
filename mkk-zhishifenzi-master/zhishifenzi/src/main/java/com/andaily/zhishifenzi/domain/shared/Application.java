package com.andaily.zhishifenzi.domain.shared;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 14-12-11
 *
 * @author Shengzhao Li
 */
public class Application implements InitializingBean {


    private static String host;

    public Application() {
    }

    public static String host() {
        return host;
    }

    public void setHost(String host) {
        if (host != null && !host.endsWith("/")) {
            host += "/";
        }
        Application.host = host;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(host, "host is null");
    }
}
