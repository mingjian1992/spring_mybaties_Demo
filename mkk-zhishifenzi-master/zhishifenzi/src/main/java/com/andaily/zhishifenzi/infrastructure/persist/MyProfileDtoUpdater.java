package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.dto.user.MyProfileDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.User;

/**
 * 14-5-15 下午10:54
 *
 * @author Shengzhao Li
 */
public class MyProfileDtoUpdater {


    private MyProfileDto myProfileDto;

    public MyProfileDtoUpdater(MyProfileDto myProfileDto) {
        this.myProfileDto = myProfileDto;
    }

    public void update() {
        User user = SecurityUtils.currentUser();
        if (user instanceof PlayerUser) {
            updateProfile((PlayerUser) user);
        } else {
            throw new IllegalStateException("Only allow PlayerUser update profile");
        }
    }

    private void updateProfile(PlayerUser user) {
        Player player = myProfileDto.updateProfile(user.player());
        player.saveOrUpdate();
        LogHandler.createLog("Update my profile, new date: " + myProfileDto, LogType.USER_OPERATION);
    }


}
