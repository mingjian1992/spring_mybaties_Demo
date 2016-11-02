package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.match.*;
import com.andaily.zhishifenzi.domain.match.MatchNotice;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class MatchMainDtoLoader extends MatchListDtoLoader {


    private MatchMainDto mainDto;

    public MatchMainDtoLoader(MatchMainDto mainDto) {
        super(mainDto);
        this.mainDto = mainDto;
    }

    public MatchMainDto load() {
        mainDto = (MatchMainDto) super.load();
        statisticsData();
        return loadMatchNotices();
    }

    private MatchMainDto loadMatchNotices() {
        List<MatchNotice> notices = matchRepository.findPendingNotices();
        return mainDto.setNotices(MatchNoticeDto.toDtos(notices));
    }

    private void statisticsData() {
        int totalScores = 0;
        int totalConceded = 0; //总失球
        int totalAssistants = 0;//总助攻
        int totalOwnGoal = 0;

        int winCount = 0;
        int eqCount = 0;
        int failCount = 0;

        List<MatchDto> list = mainDto.getList();
        for (MatchDto matchDto : list) {
            int awayScores = matchDto.getAwayScores();
            totalConceded += awayScores;

            List<GoalDto> goals = matchDto.getGoals();
            for (GoalDto goal : goals) {
                if (goal.isOwnGoal()) {
                    totalOwnGoal++;
                } else {
                    if (goal.getAssistantPlayer() != null) {
                        totalAssistants++;
                    }
                    if (goal.getGoalPlayer() != null) {
                        totalScores++;
                    }
                }
            }

            int homeScores = matchDto.getHomeScores();
            if (homeScores > awayScores) {
                winCount++;
            } else if (homeScores == awayScores) {
                eqCount++;
            } else {
                failCount++;
            }
        }

        mainDto.setTotalAssistants(totalAssistants)
                .setTotalConceded(totalConceded)
                .setTotalScores(totalScores)
                .setWinCount(winCount)
                .setEqCount(eqCount)
                .setTotalOwnGoal(totalOwnGoal)
                .setFailCount(failCount);
    }
}
