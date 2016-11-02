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
package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.BackendIndexDto;
import com.andaily.zhishifenzi.domain.dto.file.HeadImageUploadDto;
import com.andaily.zhishifenzi.domain.dto.user.*;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.shared.security.WdcyUserDetails;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRepository;
import com.andaily.zhishifenzi.domain.user.UserStatus;
import com.andaily.zhishifenzi.infrastructure.loader.BackendIndexDtoLoader;
import com.andaily.zhishifenzi.infrastructure.loader.UserFormDtoLoader;
import com.andaily.zhishifenzi.infrastructure.loader.UserOverviewDtoLoader;
import com.andaily.zhishifenzi.infrastructure.persist.CropHeadImagePersister;
import com.andaily.zhishifenzi.infrastructure.persist.MyProfileDtoUpdater;
import com.andaily.zhishifenzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Shengzhao Li
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null || user.status().isDisabled()) {
            throw new UsernameNotFoundException("Not found any user by username[" + username + "]");
        }
        return new WdcyUserDetails(user);
    }

    @Override
    public UserFormDto loadUserFormDto(String guid) {
        UserFormDtoLoader dtoLoader = new UserFormDtoLoader(guid);
        return dtoLoader.load();
    }

    @Override
    public void persistUserFormDto(UserFormDto userFormDto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public boolean loadExistUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user != null;
    }

    @Override
    public UserOverviewDto loadUserOverviewDto(UserOverviewDto userOverviewDto) {
        UserOverviewDtoLoader loader = new UserOverviewDtoLoader(userOverviewDto);
        return loader.load();
    }

    @Override
    public void archiveUserByGuid(String guid) {
        User user = userRepository.findByGuid(guid);
        user.archiveMe(true);
    }

    @Override
    public String resetPassword(String guid) {
        User user = userRepository.findByGuid(guid);
        return user.resetPassword();
    }

    @Override
    public void recordLastLoginTime(User user) {
        userRepository.updateLastLoginTime(user.guid());
    }


    @Override
    public ChangePasswordDto loadChangePasswordDto() {
        String username = SecurityUtils.currentUser().username();
        return new ChangePasswordDto(username);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {
        User currentUser = SecurityUtils.currentUser();
        currentUser.changePassword(changePasswordDto);
    }

    @Override
    public void changeUserStatus(String guid, UserStatus status) {
        final User user = userRepository.findByGuid(guid);
        user.changeStatus(status);
    }

    @Override
    public UserDetailsDto loadUserDetailsDto(String guid) {
        final User user = userRepository.findByGuid(guid);
        return new UserDetailsDto(user);
    }

    @Override
    public BackendIndexDto loadBackendIndexDto() {
        BackendIndexDtoLoader loader = new BackendIndexDtoLoader();
        return loader.load();
    }

    @Override
    public MyProfileDto loadMyProfileDto() {
        User user = SecurityUtils.currentUser();
        return new MyProfileDto(user);
    }

    @Override
    public void updateMyProfile(MyProfileDto formDto) {
        MyProfileDtoUpdater updater = new MyProfileDtoUpdater(formDto);
        updater.update();
    }

    @Override
    public String saveHeadImageUploadDto(HeadImageUploadDto uploadDto) {
        if (!uploadDto.isImage()) {
            return "-1";
        }
        Photo photo = uploadDto.toPhoto();
        photo.saveOrUpdate();
        return photo.guid();
    }

    @Override
    public void persistCropHeadImage(HeadImageUploadDto uploadDto) {
        CropHeadImagePersister persister = new CropHeadImagePersister(uploadDto);
        persister.persist();
    }
}