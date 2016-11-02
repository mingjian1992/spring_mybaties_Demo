package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;

/**
 * @author Shengzhao Li
 */
public class GoalDto extends AbstractDto {

    private String playerGuid;
    private String playerName;

    private int goals;


    public GoalDto() {
    }

    public String getPlayerGuid() {
        return playerGuid;
    }

    public GoalDto setPlayerGuid(String playerGuid) {
        this.playerGuid = playerGuid;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public GoalDto setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public int getGoals() {
        return goals;
    }

    public GoalDto setGoals(int goals) {
        this.goals = goals;
        return this;
    }
}
