package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.commons.StatisticsRepository;
import com.andaily.zhishifenzi.domain.dto.statistics.*;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.generator.LongestWinFailMatchesGenerator;

import java.util.List;

/**
 * 14-9-23 下午9:03
 *
 * @author Shengzhao Li
 */
public class ZSFZRecordDtoLoader {

    private transient StatisticsRepository statisticsRepository = BeanProvider.getBean(StatisticsRepository.class);

    public ZSFZRecordDtoLoader() {
    }

    public ZsfzRecordDto load() {
        ZsfzRecordDto recordDto = new ZsfzRecordDto();

        loadBestPlayer(recordDto);
        loadMostAssistants(recordDto);
        loadSingleMatchMostGoals(recordDto);

        loadSingleMatchMostAssists(recordDto);
        loadMostJoinMatches(recordDto);
        loadSingleMatchMostAllGoals(recordDto);

        loadLongestWinFailMatches(recordDto);

        return recordDto;
    }

    //最长连胜, 最长连败
    private void loadLongestWinFailMatches(ZsfzRecordDto recordDto) {
        LongestWinFailMatchesGenerator generator = new LongestWinFailMatchesGenerator()
                .generate();
        recordDto.setLongestFailMatches(generator.longestFailMatches())
                .setLongestFailMatchesEnd(generator.longestFailMatchesEnd())
                .setLongestFailMatchesStart(generator.longestFailMatchesStart())
                .setLongestWinMatches(generator.longestWinMatches())
                .setLongestWinMatchesEnd(generator.longestWinMatchesEnd())
                .setLongestWinMatchesStart(generator.longestWinMatchesStart());
    }


    //单场最多进球
    private void loadSingleMatchMostAllGoals(ZsfzRecordDto recordDto) {
        SingleMatchPlayerDataDto dataDto = statisticsRepository.singleMatchMostGoals();
        if (dataDto != null) {
            recordDto.setTotalSingleMatchGoals(dataDto.getCount())
                    .setTotalSingleMatchDate(DateUtils.toDateText(dataDto.getMatchTime()))
                    .setTotalSingleMatchOpponent(dataDto.getMatchOpponentName())
                    .setTotalSingleMatchGuid(dataDto.getMatchGuid());
        }
    }

    //参赛最多的队员
    private void loadMostJoinMatches(ZsfzRecordDto recordDto) {
        int totalMatches = statisticsRepository.countOfMatches();
        recordDto.setTotalMatches(totalMatches);

        SingleMatchPlayerDataDto dataDto = statisticsRepository.totalJoinMatchesPlayer();
        if (dataDto != null) {
            recordDto.setTotalJoinMatches(dataDto.getCount())
                    .setTotalJoinMatchesPlayerName(dataDto.getPlayerName())
                    .setTotalJoinMatchesPlayerGuid(dataDto.getPlayerGuid());
        }
    }

    //个人单场最多助攻
    private void loadSingleMatchMostAssists(ZsfzRecordDto recordDto) {
        SingleMatchPlayerDataDto dataDto = statisticsRepository.singleMatchPlayerMostAssists();
        if (dataDto != null) {
            recordDto.setSingleMatchAssists(dataDto.getCount())
                    .setSingleAssistsMatchGuid(dataDto.getMatchGuid())
                    .setSingleAssistsMatchOpponent(dataDto.getMatchOpponentName())
                    .setSingleAssistsPlayerName(dataDto.getPlayerName())
                    .setSingleAssistsMatchDate(DateUtils.toDateText(dataDto.getMatchTime()));
        }
    }

    //个人单场最多进球
    private void loadSingleMatchMostGoals(ZsfzRecordDto recordDto) {
        SingleMatchPlayerDataDto goalsDto = statisticsRepository.singleMatchPlayerMostGoals();
        if (goalsDto != null) {
            recordDto.setSingleMatchGoals(goalsDto.getCount())
                    .setSingleGoalsMatchGuid(goalsDto.getMatchGuid())
                    .setSingleGoalsMatchDate(DateUtils.toDateText(goalsDto.getMatchTime()))
                    .setSingleGoalsMatchOpponent(goalsDto.getMatchOpponentName())
                    .setSingleMatchGoalsPlayerName(goalsDto.getPlayerName());
        }
    }

    //助攻王
    private void loadMostAssistants(ZsfzRecordDto recordDto) {
        AssistantDto assistantDto = statisticsRepository.countMostAssistants();
        if (assistantDto != null) {
            recordDto.setTotalAssists(assistantDto.getAssistants())
                    .setTotalAssistsPlayerGuid(assistantDto.getPlayerGuid())
                    .setTotalAssistsPlayerName(assistantDto.getPlayerName());
        }
    }

    //最佳射手
    private void loadBestPlayer(ZsfzRecordDto recordDto) {
        GoalDto goalDto = statisticsRepository.countMostGoals();
        if (goalDto != null) {
            recordDto.setTotalGoals(goalDto.getGoals())
                    .setTotalGoalsPlayerGuid(goalDto.getPlayerGuid())
                    .setTotalGoalsPlayerName(goalDto.getPlayerName());
        }
    }
}
