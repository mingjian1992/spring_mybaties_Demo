package com.andaily.zhishifenzi.domain.dto.stadium;

import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;

import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class StadiumRecordDto extends DefaultPaginated<MatchDto> {

    private String guid;

    private StadiumDto stadiumDto;

    public StadiumRecordDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("stadiumGuid", guid);
        return map;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public StadiumDto getStadiumDto() {
        return stadiumDto;
    }

    public StadiumRecordDto setStadiumDto(StadiumDto stadiumDto) {
        this.stadiumDto = stadiumDto;
        return this;
    }
}
