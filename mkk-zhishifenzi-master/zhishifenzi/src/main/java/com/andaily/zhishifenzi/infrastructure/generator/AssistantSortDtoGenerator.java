package com.andaily.zhishifenzi.infrastructure.generator;

import com.andaily.zhishifenzi.domain.commons.StatisticsRepository;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.statistics.AssistantDto;
import com.andaily.zhishifenzi.domain.dto.statistics.AssistantSortDto;
import com.andaily.zhishifenzi.domain.dto.statistics.GoalDto;
import com.andaily.zhishifenzi.domain.dto.statistics.GoalSortDto;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class AssistantSortDtoGenerator {


    private transient StatisticsRepository statisticsRepository = BeanProvider.getBean(StatisticsRepository.class);


    public AssistantSortDtoGenerator() {
    }


    //Generate all seasons
    public AssistantSortDto generateAll() {
        AssistantSortDto sortDto = new AssistantSortDto();
        return loadGoals(sortDto);
    }

    private AssistantSortDto loadGoals(AssistantSortDto sortDto) {
        List<AssistantDto> assistantDtos = statisticsRepository.countAllAssistants();
        return sortDto.setAssistants(assistantDtos);
    }


    public AssistantSortDto generateBySeason(Season season) {
        AssistantSortDto sortDto = new AssistantSortDto()
                .setSeason(new SeasonDto(season));
        return loadSeasonGoals(sortDto, season);
    }

    private AssistantSortDto loadSeasonGoals(AssistantSortDto sortDto, Season season) {
        List<AssistantDto> assistantDtos = statisticsRepository.countSeasonAssistants(season);
        return sortDto.setAssistants(assistantDtos);
    }
}
