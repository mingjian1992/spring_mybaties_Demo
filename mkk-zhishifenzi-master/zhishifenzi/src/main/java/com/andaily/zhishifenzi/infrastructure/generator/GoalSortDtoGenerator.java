package com.andaily.zhishifenzi.infrastructure.generator;

import com.andaily.zhishifenzi.domain.commons.StatisticsRepository;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.statistics.GoalDto;
import com.andaily.zhishifenzi.domain.dto.statistics.GoalSortDto;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class GoalSortDtoGenerator {


    private transient StatisticsRepository statisticsRepository = BeanProvider.getBean(StatisticsRepository.class);


    public GoalSortDtoGenerator() {
    }


    //Generate all seasons
    public GoalSortDto generateAll() {
        GoalSortDto sortDto = new GoalSortDto();
        return loadGoals(sortDto);
    }

    private GoalSortDto loadGoals(GoalSortDto sortDto) {
        List<GoalDto> goals = statisticsRepository.countAllGoals();
        return sortDto.setGoals(goals);
    }


    public GoalSortDto generateBySeason(Season season) {
        GoalSortDto sortDto = new GoalSortDto()
                .setSeason(new SeasonDto(season));
        return loadSeasonGoals(sortDto, season);
    }

    private GoalSortDto loadSeasonGoals(GoalSortDto sortDto, Season season) {
        List<GoalDto> goals = statisticsRepository.countSeasonGoals(season);
        return sortDto.setGoals(goals);
    }
}
