package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;

import java.util.Date;

/**
 * 14-9-23 下午10:15
 * <p/>
 * 球员单场比赛数据DTO  (进球,助攻)
 *
 * @author Shengzhao Li
 */
public class SingleMatchPlayerDataDto extends AbstractDto {

    private int count;
    private String matchGuid;
    private Date matchTime;
    private String matchOpponentName;

    private String playerName;
    private String playerGuid;

    public SingleMatchPlayerDataDto() {
    }

    public String getPlayerGuid() {
        return playerGuid;
    }

    public void setPlayerGuid(String playerGuid) {
        this.playerGuid = playerGuid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMatchGuid() {
        return matchGuid;
    }

    public void setMatchGuid(String matchGuid) {
        this.matchGuid = matchGuid;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchOpponentName() {
        return matchOpponentName;
    }

    public void setMatchOpponentName(String matchOpponentName) {
        this.matchOpponentName = matchOpponentName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
