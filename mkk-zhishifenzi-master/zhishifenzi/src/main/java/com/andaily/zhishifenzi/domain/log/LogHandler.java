package com.andaily.zhishifenzi.domain.log;


import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.ThreadLocalHolder;
import org.springframework.util.Assert;

/**
 * @author Shengzhao Li
 */
public abstract class LogHandler {


    /**
     * Create a new log and persist it right now.
     *
     * @param content Log content,required
     * @param type    Log type,required
     */
    public static void createLog(String content, LogType type) {
        Assert.notNull(content, "Content is required");
        Assert.notNull(type, "Log type is required");
        Log log = new Log(SecurityUtils.currentUser(), DateUtils.now(), content)
                .type(type)
                .ipAddress(ThreadLocalHolder.clientIp());
        log.saveOrUpdate();
    }


}