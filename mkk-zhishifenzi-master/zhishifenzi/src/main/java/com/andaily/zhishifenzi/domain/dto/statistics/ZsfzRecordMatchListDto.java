package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;

import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class ZsfzRecordMatchListDto extends DefaultPaginated<RecordMatchDto> {

    //playerGuid
    private String guid;
    //player name
    private String name;

    private String start;
    private String end;

    public ZsfzRecordMatchListDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        final Map<String, Object> map = super.queryMap();
        map.put("guid", guid);
        map.put("start", start);
        map.put("end", end);
        return map;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}