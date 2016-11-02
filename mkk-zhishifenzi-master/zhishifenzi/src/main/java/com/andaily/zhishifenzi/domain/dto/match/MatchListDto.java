package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 14-3-31 下午10:51
 *
 * @author Shengzhao Li
 */
public class MatchListDto extends DefaultPaginated<MatchDto> {

    //赛季
    protected String seasonGuid;
    //胜 1,平 0 ,负 2, all 3
    protected int result = 3;

    protected String stadiumGuid;

    protected String matchDate;

    protected String remark;

    protected String opponentGuid;


    protected List<SeasonDto> seasons = new ArrayList<>();
    protected List<StadiumDto> stadiums = new ArrayList<>();
    protected List<ClubDto> clubs = new ArrayList<>();

    public MatchListDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("result", result);
        map.put("seasonGuid", StringUtils.isEmpty(seasonGuid) ? null : seasonGuid);
        map.put("stadiumGuid", StringUtils.isEmpty(stadiumGuid) ? null : stadiumGuid);
        map.put("matchDate", StringUtils.isEmpty(matchDate) ? null : matchDate);
        map.put("remark", StringUtils.isEmpty(remark) ? null : "%" + remark + "%");
        map.put("opponentGuid", StringUtils.isEmpty(opponentGuid) ? null : opponentGuid);
        return map;
    }

    public List<ClubDto> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDto> clubs) {
        this.clubs = clubs;
    }

    public String getOpponentGuid() {
        return opponentGuid;
    }

    public void setOpponentGuid(String opponentGuid) {
        this.opponentGuid = opponentGuid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SeasonDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDto> seasons) {
        this.seasons = seasons;
    }

    public List<StadiumDto> getStadiums() {
        return stadiums;
    }

    public void setStadiums(List<StadiumDto> stadiums) {
        this.stadiums = stadiums;
    }


    public String getSeasonGuid() {
        return seasonGuid;
    }

    public void setSeasonGuid(String seasonGuid) {
        this.seasonGuid = seasonGuid;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getStadiumGuid() {
        return stadiumGuid;
    }

    public void setStadiumGuid(String stadiumGuid) {
        this.stadiumGuid = stadiumGuid;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }
}
