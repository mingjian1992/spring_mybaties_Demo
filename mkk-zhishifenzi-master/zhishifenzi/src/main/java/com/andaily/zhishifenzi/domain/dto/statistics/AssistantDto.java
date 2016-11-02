package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;

/**
 * @author Shengzhao Li
 */
public class AssistantDto extends AbstractDto {

    private String playerGuid;
    private String playerName;

    private int assistants;


    public AssistantDto() {
    }

    public String getPlayerGuid() {
        return playerGuid;
    }

    public void setPlayerGuid(String playerGuid) {
        this.playerGuid = playerGuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAssistants() {
        return assistants;
    }

    public AssistantDto setAssistants(int assistants) {
        this.assistants = assistants;
        return this;
    }
}
