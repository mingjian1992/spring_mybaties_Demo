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
package com.andaily.zhishifenzi.web.controller.user;

import com.andaily.zhishifenzi.domain.dto.user.UserDetailsDto;
import com.andaily.zhishifenzi.domain.dto.user.UserOverviewDto;
import com.andaily.zhishifenzi.domain.user.UserStatus;
import com.andaily.zhishifenzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping("overview.wdcy")
    public String overview(UserOverviewDto userOverviewDto, Model model) {
        userOverviewDto = userService.loadUserOverviewDto(userOverviewDto);
        model.addAttribute("userOverviewDto", userOverviewDto);
        return "user/user_overview";
    }

    @RequestMapping("archive/{guid}.wdcy")
    public String archive(@PathVariable("guid") String guid) {
        userService.archiveUserByGuid(guid);
        return "redirect:../overview.wdcy?alert=archiveSuccess";
    }

    @RequestMapping("details/{guid}.wdcy")
    public String details(@PathVariable("guid") String guid, Model model) {
        UserDetailsDto detailsDto = userService.loadUserDetailsDto(guid);
        model.addAttribute("detailsDto", detailsDto);
        return "user/user_details";
    }

    @RequestMapping("change_status/{guid}/{status}.wdcy")
    public String changeStatus(@PathVariable("guid") String guid, @PathVariable("status") UserStatus status) {
        userService.changeUserStatus(guid, status);
        return "redirect:../../overview.wdcy?alert=" + (status.isEnabled() ? "enableAlertInfo" : "disableAlertInfo");
    }


    //reset password
    @RequestMapping("reset_password/{guid}.wdcy")
    public String reset(@PathVariable("guid") String guid, Model model) {
        String newPass = userService.resetPassword(guid);
        model.addAttribute("newPass", newPass);
        return "user/reset_password_finished";
    }


}