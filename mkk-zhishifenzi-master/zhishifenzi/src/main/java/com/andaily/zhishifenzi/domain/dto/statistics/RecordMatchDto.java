package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.match.Goal;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class RecordMatchDto extends MatchDto {


    private int amount;

    public RecordMatchDto() {
    }

    public RecordMatchDto(Match match) {
        super(match, true);
    }

    public int getAmount() {
        return amount;
    }

    public RecordMatchDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }


    public static List<RecordMatchDto> toRecordDtos(List<Match> matches) {
        List<RecordMatchDto> dtos = new ArrayList<>(matches.size());
        for (Match match : matches) {
            dtos.add(new RecordMatchDto(match));
        }
        return dtos;
    }


    public static List<RecordMatchDto> toRecordDtos(List<Match> matches, String playerGuid, boolean isGoal) {
        List<RecordMatchDto> dtos = new ArrayList<>(matches.size());
        for (Match match : matches) {
            RecordMatchDto recordMatchDto = new RecordMatchDto(match)
                    .setAmount(isGoal ? calculateGoalAmount(playerGuid, match) : calculateAssistAmount(playerGuid, match));
            dtos.add(recordMatchDto);
        }
        return dtos;
    }

    //助攻次数
    private static int calculateAssistAmount(String playerGuid, Match match) {
        int amount = 0;
        List<Goal> goalList = match.goals();
        for (Goal goal : goalList) {
            Player player = goal.assistantPlayer();
            if (player != null && playerGuid.equals(player.guid())) {
                amount++;
            }
        }
        return amount;
    }

    //计算进球的数量
    private static int calculateGoalAmount(String playerGuid, Match match) {
        int amount = 0;
        List<Goal> goalList = match.goals();
        for (Goal goal : goalList) {
            Player player = goal.goalPlayer();
            if (player != null && playerGuid.equals(player.guid())) {
                amount++;
            }
        }
        return amount;
    }
}