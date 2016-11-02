package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SeasonStatisticsDto extends AbstractDto {

    private SeasonDto seasonDto;
    private List<MatchDto> matches = new ArrayList<>();

    private GoalSortDto goalSortDto;
    private AssistantSortDto assistantSortDto;

    private int totalScores;//总进球
    private int totalConceded; //总失球
    private int totalAssistants;//总助攻

    private int winCount; //胜
    private int eqCount;   //平
    private int failCount;  //负

    private int integration;//积分

    public SeasonStatisticsDto() {
    }

    public SeasonDto getSeasonDto() {
        return seasonDto;
    }

    public SeasonStatisticsDto setSeasonDto(SeasonDto seasonDto) {
        this.seasonDto = seasonDto;
        return this;
    }

    //净胜球
    public String getPureScores() {
        int pure = totalScores - totalConceded;
        return pure > 0 ? "+" + pure : String.valueOf(pure);
    }

    public List<MatchDto> getMatches() {
        return matches;
    }

    public SeasonStatisticsDto setMatches(List<MatchDto> matches) {
        this.matches = matches;
        return this;
    }

    public GoalSortDto getGoalSortDto() {
        return goalSortDto;
    }

    public SeasonStatisticsDto setGoalSortDto(GoalSortDto goalSortDto) {
        this.goalSortDto = goalSortDto;
        return this;
    }

    public AssistantSortDto getAssistantSortDto() {
        return assistantSortDto;
    }

    public SeasonStatisticsDto setAssistantSortDto(AssistantSortDto assistantSortDto) {
        this.assistantSortDto = assistantSortDto;
        return this;
    }

    public int getTotalScores() {
        return totalScores;
    }

    public SeasonStatisticsDto setTotalScores(int totalScores) {
        this.totalScores = totalScores;
        return this;
    }

    public int getTotalConceded() {
        return totalConceded;
    }

    public SeasonStatisticsDto setTotalConceded(int totalConceded) {
        this.totalConceded = totalConceded;
        return this;
    }

    public int getTotalAssistants() {
        return totalAssistants;
    }

    public SeasonStatisticsDto setTotalAssistants(int totalAssistants) {
        this.totalAssistants = totalAssistants;
        return this;
    }


    public int getWinCount() {
        return winCount;
    }

    public SeasonStatisticsDto setWinCount(int winCount) {
        this.winCount = winCount;
        return this;
    }

    public int getEqCount() {
        return eqCount;
    }

    public SeasonStatisticsDto setEqCount(int eqCount) {
        this.eqCount = eqCount;
        return this;
    }

    public int getFailCount() {
        return failCount;
    }

    public SeasonStatisticsDto setFailCount(int failCount) {
        this.failCount = failCount;
        return this;
    }

    public int getIntegration() {
        return integration;
    }

    public SeasonStatisticsDto setIntegration(int integration) {
        this.integration = integration;
        return this;
    }
}
