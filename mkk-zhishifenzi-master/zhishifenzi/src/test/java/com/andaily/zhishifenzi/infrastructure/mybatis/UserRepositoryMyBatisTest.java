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
package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRepository;
import com.andaily.zhishifenzi.domain.user.UserRole;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author Shengzhao Li
 */
public class UserRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerRepository playerRepository;


    @Test
    public void findOverviewUsers() {
        User user = userRepository.findByGuid("oood");
        assertNull(user);

        user = new User("user", "123").userRole(UserRole.CAPTAIN);
        userRepository.saveUser(user);

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("username", "%us%");

        List<User> list = userRepository.findOverviewUsers(map);
        assertEquals(list.size(), 1);

    }

    @Test
    public void findSimpleUsers() {
        User user = userRepository.findByGuid("oood");
        assertNull(user);

        user = new User("user", "123").userRole(UserRole.PLAYER);
        userRepository.saveUser(user);

        final List<User> users = userRepository.findSimpleUsers();
        assertEquals(users.size(), 1);

    }

    @Test
    public void savePlayerUser() {
        Player player = new Player().fullName("lisi");
        playerRepository.savePlayer(player);

        PlayerUser user = (PlayerUser) new PlayerUser()
                .player(playerRepository.findByGuid(player.guid()))
                .userRole(UserRole.PLAYER)
                .username("user")
                .password("123");
        userRepository.savePlayerUser(user);

        final User user1 = userRepository.findByGuid(user.guid());
        assertNotNull(user1);
        assertTrue(user1 instanceof PlayerUser);

    }

    @Test
    public void findUsersByGuids() {
        User user = userRepository.findByGuid("oood");
        assertNull(user);

        user = new User("user", "123").userRole(UserRole.PLAYER);
        userRepository.saveUser(user);

        final List<User> users = userRepository.findUsersByGuids(Arrays.asList(user.guid()));
        assertEquals(users.size(), 1);

    }


    @Test
    public void totalOverviewUsers() {
        User user = userRepository.findByGuid("oood");
        assertNull(user);

        user = new User("user", "123").userRole(UserRole.PLAYER);
        userRepository.saveUser(user);

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("username", "%us%");

        int count = userRepository.totalOverviewUsers(map);
        assertEquals(count, 1);

    }


    @Test
    public void findByGuid() {
        User user = userRepository.findByGuid("oood");
        assertNull(user);

        user = new User("user", "123").userRole(UserRole.PLAYER);
        userRepository.saveUser(user);

        user = userRepository.findByGuid(user.guid());
        assertNotNull(user);


    }


    @Test
    public void updateUser() {
        User user = new User("user", "123").userRole(UserRole.PLAYER);
        userRepository.saveUser(user);

        user = userRepository.findByGuid(user.guid());
        assertNotNull(user);

        String newEmail = "test@honyee.cc";
        userRepository.updateUser(user);

        user = userRepository.findByGuid(user.guid());
        assertNotNull(user);
    }


    @Test
    public void findByUsername() {
        String username = "user";
        User user = new User(username, "123").userRole(UserRole.PLAYER);
        userRepository.saveUser(user);

        User result = userRepository.findByUsername(username);
        assertNotNull(result);
    }

    @Test
    public void findPlayerUser() {
        PlayerUser playerUser = userRepository.findPlayerUser(GuidGenerator.generate());
        assertNull(playerUser);
    }


}