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
package com.andaily.zhishifenzi.domain.user;

import com.andaily.zhishifenzi.domain.shared.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */

public interface UserRepository extends Repository {

    User findByGuid(String guid);

    void saveUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

    void archiveUser(@Param("guid") String guid, @Param("archived") boolean archived);

    List<User> findOverviewUsers(Map<String, Object> map);

    int totalOverviewUsers(Map<String, Object> map);

    /**
     * 该查询不添加 archived = 0 这个条件.
     * 但{@link #findByUsername(String)} 加了这个条件
     *
     * @param username Username
     * @return User
     */
    User findUserByUsername(String username);

    void updateLastLoginTime(String guid);

    /**
     * The user list not include <i>defaultUser</i> and DISABLED user.
     *
     * @return User list
     */
    List<User> findSimpleUsers();

    List<User> findUsersByGuids(@Param("guids") List<String> guids);

    List<User> findAvailableUsers();

    void savePlayerUser(PlayerUser playerUser);

    PlayerUser findPlayerUser(String playerGuid);
}