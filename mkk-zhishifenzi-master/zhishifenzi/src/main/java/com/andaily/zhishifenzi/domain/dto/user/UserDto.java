/*
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
 */
package com.andaily.zhishifenzi.domain.dto.user;

import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRole;
import com.andaily.zhishifenzi.domain.user.UserStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class UserDto extends AbstractDto {


    protected String username;
    protected String createDate;
    protected String lastLoginTime;

    protected boolean defaultUser;

    protected UserRole userRole;

    protected String others;
    protected UserStatus status;


    public UserDto() {
    }

    public UserDto(User user) {
        super(user.guid());
        this.username = user.username();
        this.createDate = DateUtils.toDateText(user.createTime());

        this.defaultUser = user.defaultUser();
        this.userRole = user.userRole();

        this.status = user.status();
        this.others = user.others();

        final Date date = user.lastLoginTime();
        if (date != null) {
            this.lastLoginTime = DateUtils.toDateTime(date);
        }

    }


    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isDefaultUser() {
        return defaultUser;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setDefaultUser(boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public static List<UserDto> toDtos(List<User> users) {
        List<UserDto> dtoList = new ArrayList<>(users.size());
        for (User user : users) {
            dtoList.add(new UserDto(user));
        }
        return dtoList;
    }
}