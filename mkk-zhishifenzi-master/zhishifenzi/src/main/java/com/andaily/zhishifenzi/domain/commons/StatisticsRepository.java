package com.andaily.zhishifenzi.domain.commons;

import com.andaily.zhishifenzi.domain.dto.statistics.AssistantDto;
import com.andaily.zhishifenzi.domain.dto.statistics.GoalDto;
import com.andaily.zhishifenzi.domain.dto.statistics.MatchScoreDiffDto;
import com.andaily.zhishifenzi.domain.dto.statistics.SingleMatchPlayerDataDto;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.shared.Repository;

import java.util.List;

/**
 * @author Shengzhao Li
 */

public interface StatisticsRepository extends Repository {

    List<GoalDto> countAllGoals();

    List<GoalDto> countSeasonGoals(Season season);

    List<AssistantDto> countAllAssistants();

    List<AssistantDto> countSeasonAssistants(Season season);

    //统计 最佳射手
    GoalDto countMostGoals();

    // 统计 助攻王
    AssistantDto countMostAssistants();

    //个人单场最多进球
    SingleMatchPlayerDataDto singleMatchPlayerMostGoals();

    //个人单场最多助攻
    SingleMatchPlayerDataDto singleMatchPlayerMostAssists();

    //所有比赛
    int countOfMatches();

    //参赛最多的队员
    SingleMatchPlayerDataDto totalJoinMatchesPlayer();

    //单场最多进球
    SingleMatchPlayerDataDto singleMatchMostGoals();

    //比分 diff
    List<MatchScoreDiffDto> findAllMatchScoresDiff();
}