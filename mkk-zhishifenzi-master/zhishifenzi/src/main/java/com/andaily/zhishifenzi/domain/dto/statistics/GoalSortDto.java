package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class GoalSortDto extends AbstractDto {

    //If season is null, which mean all seasons
    private SeasonDto season;

    private List<GoalDto> goals = new ArrayList<>();


    public GoalSortDto() {
    }

    public SeasonDto getSeason() {
        return season;
    }

    public GoalSortDto setSeason(SeasonDto season) {
        this.season = season;
        return this;
    }

    public List<GoalDto> getGoals() {
        return goals;
    }

    public GoalSortDto setGoals(List<GoalDto> goals) {
        this.goals = goals;
        return this;
    }
}
