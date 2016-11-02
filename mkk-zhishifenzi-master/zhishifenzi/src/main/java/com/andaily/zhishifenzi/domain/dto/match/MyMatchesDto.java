package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 14-4-19 下午5:09
 *
 * @author Shengzhao Li
 */
public class MyMatchesDto extends DefaultPaginated<MatchDto> {

    private String seasonGuid;
    private List<SeasonDto> seasons = new ArrayList<>();
    //胜 1,平 0 ,负 2, all 3
    private int result = 3;

    private Boolean hasGoal;
    private Boolean hasAssist;

    public MyMatchesDto() {
        setPerPageSize(15);
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("seasonGuid", StringUtils.isEmpty(seasonGuid) ? null : seasonGuid);
        map.put("result", result);
        map.put("hasGoal", hasGoal);
        map.put("hasAssist", hasAssist);
        return map;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getSeasonGuid() {
        return seasonGuid;
    }

    public void setSeasonGuid(String seasonGuid) {
        this.seasonGuid = seasonGuid;
    }

    public List<SeasonDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDto> seasons) {
        this.seasons = seasons;
    }

    public Boolean getHasGoal() {
        return hasGoal;
    }

    public void setHasGoal(Boolean hasGoal) {
        this.hasGoal = hasGoal;
    }

    public Boolean getHasAssist() {
        return hasAssist;
    }

    public void setHasAssist(Boolean hasAssist) {
        this.hasAssist = hasAssist;
    }
}
