package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.match.PlayerDataDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerAccountDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerListDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.user.UserStatus;

/**
 * 14-3-19 下午9:31
 *
 * @author Shengzhao Li
 */
public interface PlayerService {

    PlayerListDto loadPlayerListDto(PlayerListDto listDto);

    PlayerFormDto loadPlayerFormDto(String guid);

    Player loadPlayerByFullName(String fullName);

    void persistPlayerFormDto(PlayerFormDto formDto);

    void archivePlayer(String guid);

    PlayerAccountDto loadPlayerAccountDto(String playerGuid);

    //return: player guid
    String persistPlayerAccountDto(PlayerAccountDto formDto);

    String changePlayerUserStatus(String playerUserGuid, UserStatus status);

    String resetPlayerUserPassword(String guid);

    PlayerDataDto loadPlayerDataDto(String guid);

    PlayerDto loadPlayerDto(String guid);
}
