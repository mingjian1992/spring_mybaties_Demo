package com.andaily.zhishifenzi.domain.dto.match;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class MatchMainDto extends MatchListDto {


    private int totalScores;//总进球
    private int totalConceded; //总失球
    private int totalAssistants;//总助攻
    private int totalOwnGoal;

    private int winCount;
    private int eqCount;
    private int failCount;

    private List<MatchNoticeDto> notices = new ArrayList<>();

    public MatchMainDto() {
    }

    public int getTotalScores() {
        return totalScores;
    }

    public MatchMainDto setTotalScores(int totalScores) {
        this.totalScores = totalScores;
        return this;
    }

    public int getTotalConceded() {
        return totalConceded;
    }

    public MatchMainDto setTotalConceded(int totalConceded) {
        this.totalConceded = totalConceded;
        return this;
    }

    public int getTotalAssistants() {
        return totalAssistants;
    }

    public MatchMainDto setTotalAssistants(int totalAssistants) {
        this.totalAssistants = totalAssistants;
        return this;
    }

    public int getWinCount() {
        return winCount;
    }

    public MatchMainDto setWinCount(int winCount) {
        this.winCount = winCount;
        return this;
    }

    public int getEqCount() {
        return eqCount;
    }

    public MatchMainDto setEqCount(int eqCount) {
        this.eqCount = eqCount;
        return this;
    }

    public int getFailCount() {
        return failCount;
    }

    public MatchMainDto setFailCount(int failCount) {
        this.failCount = failCount;
        return this;
    }

    public int getTotalOwnGoal() {
        return totalOwnGoal;
    }

    public MatchMainDto setTotalOwnGoal(int totalOwnGoal) {
        this.totalOwnGoal = totalOwnGoal;
        return this;
    }

    public List<MatchNoticeDto> getNotices() {
        return notices;
    }

    public MatchMainDto setNotices(List<MatchNoticeDto> notices) {
        this.notices = notices;
        return this;
    }
}
