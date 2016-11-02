package com.andaily.zhishifenzi.domain.dto.club;

import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;

import java.util.Map;

/**
 * 14-5-1 下午12:19
 *
 * @author Shengzhao Li
 */
public class ClubRecordDto extends DefaultPaginated<MatchDto> {

    private String guid;

    private ClubDto clubDto;

    private int winCount;
    private int eqCount;
    private int failCount;

    public ClubRecordDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("clubGuid", guid);
        return map;
    }

    public int getEqCount() {
        return eqCount;
    }

    public ClubRecordDto setEqCount(int eqCount) {
        this.eqCount = eqCount;
        return this;
    }

    public int getFailCount() {
        return failCount;
    }

    public ClubRecordDto setFailCount(int failCount) {
        this.failCount = failCount;
        return this;
    }

    public int getWinCount() {
        return winCount;
    }

    public ClubRecordDto setWinCount(int winCount) {
        this.winCount = winCount;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public ClubDto getClubDto() {
        return clubDto;
    }

    public void setClubDto(ClubDto clubDto) {
        this.clubDto = clubDto;
    }
}
