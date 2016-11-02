package com.andaily.zhishifenzi.domain.dto;

import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-4-19 下午3:05
 *
 * @author Shengzhao Li
 */
public class BackendIndexDto extends AbstractDto {


    private String username;
    private String lastLoginTime;

    //player info
    //姓名
    private String fullName;
    private String phone;
    private String email;
    private String entryDate;

    private String position;
    private String number;
    private String description;

    private String playerGuid;
    private String headPhotoGuid;
    private List<MatchNoticeDto> notices = new ArrayList<>();

    public BackendIndexDto() {
    }

    public BackendIndexDto(User user) {
        this.username = user.username();
        this.lastLoginTime = DateUtils.toDateTime(user.lastLoginTime());

        if (user instanceof PlayerUser) {
            PlayerUser playerUser = (PlayerUser) user;
            Player player = playerUser.player();
            this.playerGuid = player.guid();
            this.fullName = player.fullName();
            this.phone = player.phone();
            this.email = player.email();

            this.entryDate = player.entryDate() != null ? DateUtils.toDateText(player.entryDate()) : null;
            this.position = player.position().getLabel();
            this.number = player.number();
            this.description = player.description();

            Photo photo = player.headPhoto();
            if (photo != null) {
                this.headPhotoGuid = photo.guid();
            }
        }
    }

    public String getShowName() {
        return StringUtils.isNotEmpty(fullName) ? fullName : username;
    }

    public String getPlayerGuid() {
        return playerGuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MatchNoticeDto> getNotices() {
        return notices;
    }

    public void setNotices(List<MatchNoticeDto> notices) {
        this.notices = notices;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getHeadPhotoGuid() {
        return headPhotoGuid;
    }
}

