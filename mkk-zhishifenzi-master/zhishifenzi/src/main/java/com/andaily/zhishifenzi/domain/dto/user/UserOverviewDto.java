package com.andaily.zhishifenzi.domain.dto.user;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class UserOverviewDto extends DefaultPaginated<UserDto> {

    private String username;
    private String deptGuid;


    public UserOverviewDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("username", (StringUtils.isEmpty(username) ? null : "%" + username + "%"));
        map.put("deptGuid", (StringUtils.isEmpty(deptGuid) ? null : deptGuid));
        return map;
    }

    public String getDeptGuid() {
        return deptGuid;
    }

    public void setDeptGuid(String deptGuid) {
        this.deptGuid = deptGuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}