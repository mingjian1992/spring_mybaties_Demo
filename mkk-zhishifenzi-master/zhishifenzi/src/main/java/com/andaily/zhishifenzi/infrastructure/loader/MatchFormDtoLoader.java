package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchFormDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeFormDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.*;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;

import java.util.List;

/**
 * 14-4-3 下午11:48
 *
 * @author Shengzhao Li
 */
public class MatchFormDtoLoader {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    private transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);
    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);


    private String guid;

    public MatchFormDtoLoader(String guid) {
        this.guid = guid;
    }

    public MatchFormDto load() {
        MatchFormDto formDto = new MatchFormDto(guid);
        if (!formDto.isNewly()) {
            Match match = matchRepository.findByGuid(guid);
            formDto = new MatchFormDto(match);
        }

        loadClubs(formDto);
        loadStadiums(formDto);
        loadSeasons(formDto);
        loadPlayers(formDto);
        loadNotices(formDto);
        return formDto;
    }

    private void loadNotices(MatchFormDto formDto) {
        List<MatchNotice> notices = matchRepository.findPendingNotices();
        formDto.setNotices(MatchNoticeDto.toDtos(notices));
    }

    private void loadPlayers(MatchFormDto formDto) {
        List<Player> players = playerRepository.findAvailablePlayers();
        formDto.setPlayers(PlayerDto.toDtos(players));
    }

    private void loadSeasons(MatchFormDto formDto) {
        List<Season> seasons = seasonRepository.findAllSeasons();
        formDto.setSeasons(SeasonDto.toDtos(seasons));

        if (formDto.getSeason() == null) {
            for (SeasonDto s : formDto.getSeasons()) {
                if (s.isPending()) {
                    formDto.setSeason(new SeasonDto(s.getGuid()));
                    break;
                }
            }
        }
    }


    private void loadClubs(MatchFormDto formDto) {
        List<Club> clubs = clubRepository.findAllClubs();
        formDto.setClubs(ClubDto.toDtos(clubs));
    }

    private void loadStadiums(MatchFormDto formDto) {
        List<Stadium> stadiums = stadiumRepository.findAllStadiums();
        formDto.setStadiums(StadiumDto.toDtos(stadiums));
    }
}
