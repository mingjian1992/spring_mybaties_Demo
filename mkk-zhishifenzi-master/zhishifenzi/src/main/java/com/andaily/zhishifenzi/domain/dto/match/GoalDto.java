package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.domain.match.Goal;
import com.andaily.zhishifenzi.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-31 下午10:38
 *
 * @author Shengzhao Li
 */
public class GoalDto extends AbstractDto {

    //进球队员
    protected PlayerDto goalPlayer;
    //助攻队员
    protected PlayerDto assistantPlayer;
    //对方OG ,乌龙球
    protected boolean ownGoal;
    //进球时间
    protected int goalTime;

    public GoalDto() {
    }

    public GoalDto(Goal goal) {
        super(goal.guid());
        this.ownGoal = goal.ownGoal();
        this.goalTime = goal.goalTime();
        if (!this.ownGoal) {
            this.goalPlayer = new PlayerDto(goal.goalPlayer());

            Player assistantPlayer = goal.assistantPlayer();
            if (assistantPlayer != null) {
                this.assistantPlayer = new PlayerDto(assistantPlayer);
            }
        }
    }

    public PlayerDto getGoalPlayer() {
        return goalPlayer;
    }

    public void setGoalPlayer(PlayerDto goalPlayer) {
        this.goalPlayer = goalPlayer;
    }

    public PlayerDto getAssistantPlayer() {
        return assistantPlayer;
    }

    public void setAssistantPlayer(PlayerDto assistantPlayer) {
        this.assistantPlayer = assistantPlayer;
    }

    public boolean isOwnGoal() {
        return ownGoal;
    }

    public void setOwnGoal(boolean ownGoal) {
        this.ownGoal = ownGoal;
    }

    public int getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(int goalTime) {
        this.goalTime = goalTime;
    }

    public static List<GoalDto> toDtos(List<Goal> goals) {
        List<GoalDto> dtos = new ArrayList<>(goals.size());
        for (Goal goal : goals) {
            dtos.add(new GoalDto(goal));
        }
        return dtos;
    }
}
