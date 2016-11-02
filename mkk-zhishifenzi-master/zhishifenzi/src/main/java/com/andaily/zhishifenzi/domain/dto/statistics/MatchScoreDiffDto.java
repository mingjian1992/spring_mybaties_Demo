package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;

import java.util.Date;

/**
 * 14-9-25 下午9:14
 * <p/>
 * 比赛的比分比较 DTO
 *
 * @author Shengzhao Li
 */
public class MatchScoreDiffDto extends AbstractDto {

    // diff = home scores -  away scores
    private int diffScores;

    private Date matchTime;
    private String matchGuid;

    public MatchScoreDiffDto() {
    }

    public int getDiffScores() {
        return diffScores;
    }

    public void setDiffScores(int diffScores) {
        this.diffScores = diffScores;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchGuid() {
        return matchGuid;
    }

    public void setMatchGuid(String matchGuid) {
        this.matchGuid = matchGuid;
    }
}
