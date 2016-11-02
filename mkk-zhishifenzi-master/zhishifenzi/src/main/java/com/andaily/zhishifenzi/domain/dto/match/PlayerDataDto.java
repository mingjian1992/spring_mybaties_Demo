package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;

/**
 * 14-5-8 下午11:24
 *
 * @author Shengzhao Li
 */
public class PlayerDataDto extends MyDataDto {

    private PlayerDto playerDto;

    public PlayerDataDto() {
    }

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

    public PlayerDataDto setPlayerDto(PlayerDto playerDto) {
        this.playerDto = playerDto;
        return this;
    }
}
