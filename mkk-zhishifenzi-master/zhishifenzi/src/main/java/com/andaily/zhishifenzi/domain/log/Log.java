package com.andaily.zhishifenzi.domain.log;


import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.user.User;

import java.util.Date;

/**
 * @author Shengzhao Li
 */
public class Log extends AbstractDomain {

    protected transient LogRepository logRepository = BeanProvider.getBean(LogRepository.class);


    protected User creator;

    //who
    protected User who;
    //what time
    protected Date time;
    //do what
    protected String content;

    protected LogType type;
    //IP address, optional
    protected String ipAddress;
    //Is important or not
    protected boolean important;

    public Log() {
    }

    public Log(User who, Date time, String content) {
        this.who = who;
        this.time = time;
        this.content = content;
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            logRepository.saveLog(this);
        } else {
            logRepository.updateLog(this);
        }
    }


    public LogType type() {
        return type;
    }

    public Log type(LogType type) {
        this.type = type;
        return this;
    }


    public User creator() {
        return creator;
    }

    public Log creator(User creator) {
        this.creator = creator;
        return this;
    }

    public User who() {
        return who;
    }

    public Log who(User who) {
        this.who = who;
        return this;
    }

    public Date time() {
        return time;
    }

    public Log time(Date time) {
        this.time = time;
        return this;
    }

    public Log content(String content) {
        this.content = content;
        return this;
    }

    public String content() {
        return content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Log");
        sb.append("{creator=").append(creator);
        sb.append(", id=").append(id);
        sb.append(", time=").append(time);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String ipAddress() {
        return ipAddress;
    }

    public Log ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }


    public boolean important() {
        return important;
    }

    public Log important(boolean important) {
        this.important = important;
        return this;
    }
}