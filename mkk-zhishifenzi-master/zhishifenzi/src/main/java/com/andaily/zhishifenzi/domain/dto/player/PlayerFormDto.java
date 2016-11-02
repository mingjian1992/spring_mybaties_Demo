package com.andaily.zhishifenzi.domain.dto.player;

import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerPosition;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 14-3-19 下午10:04
 *
 * @author Shengzhao Li
 */
public class PlayerFormDto extends PlayerDto {

    private String existName;

    public PlayerFormDto() {
    }

    public PlayerFormDto(Player player) {
        super(player);
        this.existName = player.fullName();
    }

    public PlayerFormDto(String guid) {
        this.guid = guid;
    }

    public PlayerPosition[] getPositions() {
        return PlayerPosition.values();
    }

    public boolean isNewly() {
        return super.isNewly() || MatchUtils.isCreate(guid);
    }


    public String getExistName() {
        return existName;
    }

    public void setExistName(String existName) {
        this.existName = existName;
    }

    public Player updateDomain(Player player) {
        if (StringUtils.isNotEmpty(entryDate)) {
            player.entryDate(DateUtils.getDate(entryDate));
        }
        if (StringUtils.isNotEmpty(birthday)) {
            player.birthday(DateUtils.getDate(birthday));
        }
        return player.fullName(fullName)
                .position(position)
                .email(email)
                .captain(captain)
                .number(number)
                .description(description)
                .phone(phone);
    }
}
