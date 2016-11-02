package com.andaily.zhishifenzi.domain.dto.player;

import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerPosition;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 14-3-19 下午9:07
 *
 * @author Shengzhao Li
 */
public class PlayerDto extends AbstractDto {

    protected String fullName;

    protected PlayerPosition position;
    protected boolean captain;

    protected String entryDate;
    protected String birthday;

    protected String phone;
    protected String email;

    protected String description;
    protected String number;

    protected String headPhotoGuid;

    public PlayerDto() {
    }

    public PlayerDto(Player player) {
        super(player.guid());
        this.fullName = player.fullName();
        this.position = player.position();

        this.entryDate = toDateText(player.entryDate());
        this.birthday = toDateText(player.birthday());
        this.captain = player.captain();

        this.phone = player.phone();
        this.email = player.email();
        this.number = player.number();
        this.description = player.description();

        Photo photo = player.headPhoto();
        if (photo != null) {
            this.headPhotoGuid = photo.guid();
        }
    }

    private String toDateText(Date date) {
        if (date != null) {
            return DateUtils.toDateText(date);
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHeadPhotoGuid() {
        return headPhotoGuid;
    }

    public void setHeadPhotoGuid(String headPhotoGuid) {
        this.headPhotoGuid = headPhotoGuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public static List<PlayerDto> toDtos(List<Player> players) {
        List<PlayerDto> dtos = new ArrayList<>(players.size());
        for (Player player : players) {
            dtos.add(new PlayerDto(player));
        }
        return dtos;
    }
}
