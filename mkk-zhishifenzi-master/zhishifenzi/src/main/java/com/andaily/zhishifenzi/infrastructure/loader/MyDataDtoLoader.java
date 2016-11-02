package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.match.MyDataDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.User;

import java.util.List;

/**
 * 14-4-21 下午9:38
 *
 * @author Shengzhao Li
 */
public class MyDataDtoLoader {

    protected transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);

    protected Player player;

    public MyDataDtoLoader() {
    }

    protected void initialPlayer() {
        User user = SecurityUtils.currentUser();
        if (user instanceof PlayerUser) {
            this.player = ((PlayerUser) user).player();
        }
    }

    public MyDataDto load() {
        initialPlayer();

        MyDataDto dataDto = createDataDto();
        loadScores(dataDto);
        loadAssists(dataDto);
        loadMyMatches(dataDto);
        return dataDto;
    }

    protected MyDataDto createDataDto() {
        return new MyDataDto();
    }

    private void loadMyMatches(MyDataDto dataDto) {
        dataDto.setTotalMatches(matchRepository.totalMatches());
        List<Match> matches = matchRepository.findPlayerMatches(player);
        dataDto.setMyMatches(matches.size());

        int winCount = 0;
        int eqCount = 0;
        int failCount = 0;

        for (Match match : matches) {
            int awayScores = match.awayScores();
            int homeScores = match.homeScores();
            if (homeScores > awayScores) {
                winCount++;
            } else if (homeScores == awayScores) {
                eqCount++;
            } else {
                failCount++;
            }
        }
        dataDto.setWin(winCount)
                .setEquals(eqCount)
                .setFail(failCount);
    }

    private void loadAssists(MyDataDto dataDto) {
        dataDto.setTotalAssists(matchRepository.totalAssists());
        dataDto.setMyAssists(matchRepository.totalPlayerAssists(player));
    }

    private void loadScores(MyDataDto dataDto) {
        dataDto.setTotalScores(matchRepository.totalScores());
        dataDto.setMyScores(matchRepository.totalPlayerScores(player));
    }
}
