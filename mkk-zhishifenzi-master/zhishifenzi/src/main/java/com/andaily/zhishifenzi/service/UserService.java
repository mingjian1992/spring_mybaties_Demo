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
package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.BackendIndexDto;
import com.andaily.zhishifenzi.domain.dto.file.HeadImageUploadDto;
import com.andaily.zhishifenzi.domain.dto.user.*;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Shengzhao Li
 */
public interface UserService extends UserDetailsService {

    UserFormDto loadUserFormDto(String guid);

    void persistUserFormDto(UserFormDto userFormDto);

    boolean loadExistUsername(String username);

    UserOverviewDto loadUserOverviewDto(UserOverviewDto userOverviewDto);

    void archiveUserByGuid(String guid);

    String resetPassword(String guid);

    void recordLastLoginTime(User user);


    ChangePasswordDto loadChangePasswordDto();

    void changePassword(ChangePasswordDto changePasswordDto);

    void changeUserStatus(String guid, UserStatus status);

    UserDetailsDto loadUserDetailsDto(String guid);

    BackendIndexDto loadBackendIndexDto();

    MyProfileDto loadMyProfileDto();

    void updateMyProfile(MyProfileDto formDto);

    String saveHeadImageUploadDto(HeadImageUploadDto uploadDto);

    void persistCropHeadImage(HeadImageUploadDto uploadDto);
}