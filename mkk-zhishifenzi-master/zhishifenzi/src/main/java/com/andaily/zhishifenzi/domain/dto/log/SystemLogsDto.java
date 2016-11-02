package com.andaily.zhishifenzi.domain.dto.log;

import com.andaily.zhishifenzi.domain.dto.user.UserDto;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class SystemLogsDto extends DefaultPaginated<LogDto> {

    private String name;
    private LogType type;
    private String userGuid;

    private List<UserDto> users = new ArrayList<>();

    public SystemLogsDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        final Map<String, Object> map = super.queryMap();
        map.put("name", StringUtils.isEmpty(name) ? null : "%" + name + "%");
        map.put("userGuid", StringUtils.isEmpty(userGuid) ? null : userGuid);
        map.put("type", type);
        return map;
    }

    public LogType[] getTypes() {
        return LogType.values();
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}