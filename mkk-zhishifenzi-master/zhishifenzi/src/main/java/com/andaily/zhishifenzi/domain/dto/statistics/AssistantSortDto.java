package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class AssistantSortDto extends AbstractDto {

    //If season is null, which mean all seasons
    private SeasonDto season;

    private List<AssistantDto> assistants = new ArrayList<>();


    public AssistantSortDto() {
    }

    public SeasonDto getSeason() {
        return season;
    }

    public AssistantSortDto setSeason(SeasonDto season) {
        this.season = season;
        return this;
    }

    public List<AssistantDto> getAssistants() {
        return assistants;
    }

    public AssistantSortDto setAssistants(List<AssistantDto> assistants) {
        this.assistants = assistants;
        return this;
    }
}
