package com.andaily.zhishifenzi.domain.dto.log;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.log.Log;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class LogDto extends AbstractDto {

    protected String userGuid;
    protected String username;

    protected String time;
    protected String timeAsDate;
    protected String content;

    protected LogType type;
    protected String ipAddress;

    protected boolean important;

    public LogDto() {
    }

    public LogDto(Log log) {
        super(log.guid());
        this.type = log.type();

        this.time = DateUtils.toDateTime(log.time());
        this.timeAsDate = DateUtils.toDateText(log.time());
        this.content = log.content();
        this.ipAddress = log.ipAddress();

        final User who = log.who();
        if (who == null) {
            this.username = "系统";
        } else {
            this.username = who.username();
            this.userGuid = who.guid();
        }
        this.important = log.important();
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getTimeAsDate() {
        return timeAsDate;
    }

    public void setTimeAsDate(String timeAsDate) {
        this.timeAsDate = timeAsDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public static List<LogDto> toDtos(List<Log> logs) {
        List<LogDto> logDtos = new ArrayList<>(logs.size());
        for (Log log : logs) {
            logDtos.add(new LogDto(log));
        }
        return logDtos;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}