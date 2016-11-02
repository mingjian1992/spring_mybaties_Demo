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

import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRole;
import com.andaily.zhishifenzi.infrastructure.PasswordHandler;

/**
 * @author Shengzhao Li
 */
public class UserFormDto extends UserDto {

    private String password;
    private String rePassword;

    private String existUsername;

    public UserFormDto() {
        //set default value
        this.userRole = UserRole.PLAYER;
    }

    public UserFormDto(User user) {
        super(user);
        this.existUsername = user.username();
    }

    public UserFormDto(String guid) {
        this();
        this.guid = guid;
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public UserRole[] getUserRoles() {
        return UserRole.values();
    }

    public User toDomain() {
        String encryptPass = PasswordHandler.encryptPassword(password);
        return new User(username, encryptPass)
                .userRole(userRole)
                .others(others);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getExistUsername() {
        return existUsername;
    }

    public void setExistUsername(String existUsername) {
        this.existUsername = existUsername;
    }

}