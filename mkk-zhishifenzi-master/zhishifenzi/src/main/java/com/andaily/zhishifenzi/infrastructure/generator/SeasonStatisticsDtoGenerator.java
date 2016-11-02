package com.andaily.zhishifenzi.infrastructure.generator;

import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.statistics.*;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SeasonStatisticsDtoGenerator {


    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);

    private AssistantSortDtoGenerator assistantSortDtoGenerator = new AssistantSortDtoGenerator();
    private GoalSortDtoGenerator goalSortDtoGenerator = new GoalSortDtoGenerator();

    private Season season;

    public SeasonStatisticsDtoGenerator(Season season) {
        this.season = season;
    }

    public SeasonStatisticsDto generate() {
        SeasonStatisticsDto statisticsDto = new SeasonStatisticsDto()
                .setSeasonDto(new SeasonDto(season));

        generateGoalSortDto(statisticsDto);
        generateAssistantSortDto(statisticsDto);
        generateByMatches(statisticsDto);

        return statisticsDto;
    }

    private void generateByMatches(SeasonStatisticsDto statisticsDto) {
        List<MatchDto> matchDtos = statisticsDto.getMatches();
        List<Match> matches = matchRepository.findMatchesBySeason(season);

        int totalConceded = 0;
        int integration = 0;

        int winCount = 0; //胜
        int eqCount = 0;   //平
        int failCount = 0;  //负
        for (Match match : matches) {
            matchDtos.add(new MatchDto(match, true));
            int homeScores = match.homeScores();
            int awayScores = match.awayScores();
            totalConceded += awayScores;

            if (homeScores > awayScores) {
                winCount++;
                integration += 3;
            } else if (homeScores == awayScores) {
                eqCount++;
                integration += 1;
            } else {
                failCount++;
            }
        }

        statisticsDto.setTotalConceded(totalConceded)
                .setIntegration(integration)
                .setWinCount(winCount)
                .setEqCount(eqCount)
                .setFailCount(failCount);
    }

    private void generateAssistantSortDto(SeasonStatisticsDto statisticsDto) {
        AssistantSortDto assistantSortDto = assistantSortDtoGenerator.generateBySeason(season);
        statisticsDto.setAssistantSortDto(assistantSortDto);

        int totalAssistants = 0;
        for (AssistantDto assistantDto : assistantSortDto.getAssistants()) {
            totalAssistants += assistantDto.getAssistants();
        }
        statisticsDto.setTotalAssistants(totalAssistants);
    }

    private void generateGoalSortDto(SeasonStatisticsDto statisticsDto) {
        GoalSortDto sortDto = goalSortDtoGenerator.generateBySeason(season);
        statisticsDto.setGoalSortDto(sortDto);

        int totalGoals = 0;
        for (GoalDto goalDto : sortDto.getGoals()) {
            totalGoals += goalDto.getGoals();
        }
        statisticsDto.setTotalScores(totalGoals);
    }
}
