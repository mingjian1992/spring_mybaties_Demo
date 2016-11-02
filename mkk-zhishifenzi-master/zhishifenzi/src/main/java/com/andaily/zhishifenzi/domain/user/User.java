package com.andaily.zhishifenzi.domain.user;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.dto.user.ChangePasswordDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.PasswordHandler;


import java.util.Date;

/**
 * @author Shengzhao Li
 */
public class User extends AbstractDomain {

    protected transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);


    //登录名
    protected String username;
    protected String password;

    //Default user is initial when create database, do not delete
    protected boolean defaultUser = false;

    protected Date lastLoginTime;

    protected UserRole userRole;


    //状态: 默认是启用
    protected UserStatus status = UserStatus.ENABLED;
    //用户有关的其他信息
    protected String others;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean defaultUser() {
        return defaultUser;
    }

    public String username() {
        return username;
    }


    public String password() {
        return password;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{username='").append(username).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", guid='").append(guid).append('\'');
        sb.append(", defaultUser='").append(defaultUser).append('\'');
        sb.append('}');
        return sb.toString();
    }


    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            userRepository.saveUser(this);
        } else {
            userRepository.updateUser(this);
        }
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public String resetPassword() {
        String newOriginalPass = PasswordHandler.randomPassword();
        this.password = PasswordHandler.encryptPassword(newOriginalPass);
        this.saveOrUpdate();
        LogHandler.createLog("Rest user[" + this + "] password", LogType.USER_OPERATION);
        return newOriginalPass;
    }


    public Date lastLoginTime() {
        return lastLoginTime;
    }


    public UserRole userRole() {
        return userRole;
    }

    public User userRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }


    public User password(String pass) {
        this.password = pass;
        return this;
    }

    public void archiveMe(boolean archived) {
        if (defaultUser) {
            throw new IllegalStateException("Forbidden archive default user");
        }
        userRepository.archiveUser(guid(), archived);
    }

    public User changePassword(ChangePasswordDto changePasswordDto) {
        this.password = PasswordHandler.encryptPassword(changePasswordDto.getNewPassword());
        this.saveOrUpdate();
        LogHandler.createLog("Change password", LogType.USER_OPERATION);
        return this;
    }

    public UserStatus status() {
        return status;
    }

    public String others() {
        return others;
    }

    public User others(String others) {
        this.others = others;
        return this;
    }

    public User changeStatus(UserStatus status) {
        this.status = status;
        this.saveOrUpdate();
        LogHandler.createLog("Change user[" + this + "] status to [" + status + "]", LogType.USER_OPERATION);
        return this;
    }


}