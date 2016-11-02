package com.andaily.zhishifenzi.domain.dto.user;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.Date;

/**
 * @author Shengzhao Li
 */
public class MyProfileDto extends AbstractDto {

    private String fullName;
    private String email;
    private String phone;

    private String birthday;
    private String entryDate;

    private String number;
    private String description;

    private boolean enabled;

    public MyProfileDto() {
        super();
    }

    public MyProfileDto(User user) {
        if (user instanceof PlayerUser) {
            PlayerUser playerUser = (PlayerUser) user;
            this.enabled = true;

            Player player = playerUser.player();
            this.fullName = player.fullName();
            this.email = player.email();
            this.phone = player.phone();

            this.birthday = toDateText(player.birthday());
            this.entryDate = toDateText(player.entryDate());

            this.number = player.number();
            this.description = player.description();
        }
    }

    private String toDateText(Date date) {
        if (date != null) {
            return DateUtils.toDateText(date);
        }
        return null;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Player updateProfile(Player player) {
        player.fullName(fullName)
                .phone(phone)
                .number(number)
                .description(description)
                .email(email);

        if (DateUtils.isDate(birthday)) {
            player.birthday(DateUtils.getDate(birthday));
        } else {
            player.birthday(null);
        }
        if (DateUtils.isDate(entryDate)) {
            player.entryDate(DateUtils.getDate(entryDate));
        } else {
            player.entryDate(null);
        }

        return player;
    }

    @Override
    public String toString() {
        return "{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", entryDate='" + entryDate + '\'' +
                '}';
    }
}