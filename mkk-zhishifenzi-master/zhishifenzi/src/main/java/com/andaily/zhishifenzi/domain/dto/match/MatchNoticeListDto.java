package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 14-3-21 下午9:30
 *
 * @author Shengzhao Li
 */
public class MatchNoticeListDto extends DefaultPaginated<MatchNoticeDto> {

    private String stadiumGuid;
    private String opponentGuid;

    private List<StadiumDto> stadiums = new ArrayList<>();
    private List<ClubDto> clubs = new ArrayList<>();

    public MatchNoticeListDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("stadiumGuid", StringUtils.isEmpty(stadiumGuid) ? null : stadiumGuid);
        map.put("opponentGuid", StringUtils.isEmpty(opponentGuid) ? null : opponentGuid);
        return map;
    }

    public List<StadiumDto> getStadiums() {
        return stadiums;
    }

    public void setStadiums(List<StadiumDto> stadiums) {
        this.stadiums = stadiums;
    }

    public List<ClubDto> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDto> clubs) {
        this.clubs = clubs;
    }

    public String getStadiumGuid() {
        return stadiumGuid;
    }

    public void setStadiumGuid(String stadiumGuid) {
        this.stadiumGuid = stadiumGuid;
    }

    public String getOpponentGuid() {
        return opponentGuid;
    }

    public void setOpponentGuid(String opponentGuid) {
        this.opponentGuid = opponentGuid;
    }
}
