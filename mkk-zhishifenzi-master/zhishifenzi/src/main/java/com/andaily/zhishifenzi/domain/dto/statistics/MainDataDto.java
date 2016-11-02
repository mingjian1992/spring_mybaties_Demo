package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class MainDataDto extends AbstractDto {

    private List<SeasonDto> seasons = new ArrayList<>();
    private SeasonStatisticsDto currentDto;


    public MainDataDto() {
    }

    public List<SeasonDto> getSeasons() {
        return seasons;
    }

    public MainDataDto setSeasons(List<SeasonDto> seasons) {
        this.seasons = seasons;
        return this;
    }

    public SeasonStatisticsDto getCurrentDto() {
        return currentDto;
    }

    public MainDataDto setCurrentDto(SeasonStatisticsDto currentDto) {
        this.currentDto = currentDto;
        return this;
    }
}
