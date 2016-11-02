package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.commons.StatisticsRepository;
import com.andaily.zhishifenzi.domain.dto.statistics.AssistantDto;
import com.andaily.zhishifenzi.domain.dto.statistics.GoalDto;
import com.andaily.zhishifenzi.domain.dto.statistics.MatchScoreDiffDto;
import com.andaily.zhishifenzi.domain.dto.statistics.SingleMatchPlayerDataDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Shengzhao Li
 */
public class StatisticsRepositoryMyBatisTest extends AbstractRepositoryTest {


    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    public void countAllGoals() {
        List<GoalDto> goalDtos = statisticsRepository.countAllGoals();
        assertEquals(goalDtos.size(), 0);
    }

    @Test
    public void findAllMatchScoresDiff() {
        List<MatchScoreDiffDto> list = statisticsRepository.findAllMatchScoresDiff();
        assertTrue(list.isEmpty());
    }

    @Test
    public void totalJoinMatchesPlayer() {
        SingleMatchPlayerDataDto dataDto = statisticsRepository.totalJoinMatchesPlayer();
        assertTrue(dataDto == null);
    }

    @Test
    public void singleMatchMostGoals() {
        SingleMatchPlayerDataDto dataDto = statisticsRepository.singleMatchMostGoals();
        assertTrue(dataDto == null);
    }

    @Test
    public void countOfMatches() {
        int i = statisticsRepository.countOfMatches();
        assertTrue(i == 0);
    }

    @Test
    public void singleMatchPlayerMostGoals() {
        SingleMatchPlayerDataDto singleMatchPlayerGoalsDto = statisticsRepository.singleMatchPlayerMostGoals();
        assertNull(singleMatchPlayerGoalsDto);
    }

    @Test
    public void singleMatchPlayerMostAssists() {
        SingleMatchPlayerDataDto singleMatchPlayerGoalsDto = statisticsRepository.singleMatchPlayerMostAssists();
        assertNull(singleMatchPlayerGoalsDto);
    }

    @Test
    public void countMostGoals() {
        GoalDto goalDto = statisticsRepository.countMostGoals();
        assertNull(goalDto);
    }

    @Test
    public void countMostAssistants() {
        AssistantDto assistantDto = statisticsRepository.countMostAssistants();
        assertNull(assistantDto);
    }

    @Test
    public void countAllAssistants() {
        List<AssistantDto> goalDtos = statisticsRepository.countAllAssistants();
        assertEquals(goalDtos.size(), 0);
    }


    @Test
    public void countSeasonGoals() {
        Season season = new Season();
        seasonRepository.saveSeason(season);
        season = seasonRepository.findByGuid(season.guid());

        Match match = new Match().season(season);
        match.saveOrUpdate();


        List<GoalDto> goalDtos = statisticsRepository.countSeasonGoals(season);
        assertEquals(goalDtos.size(), 0);
    }

    @Test
    public void countSeasonAssistants() {
        Season season = new Season();
        seasonRepository.saveSeason(season);
        season = seasonRepository.findByGuid(season.guid());

        Match match = new Match().season(season);
        match.saveOrUpdate();


        List<AssistantDto> goalDtos = statisticsRepository.countSeasonAssistants(season);
        assertEquals(goalDtos.size(), 0);
    }
}
