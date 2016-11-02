package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeFormDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.MatchNotice;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;

import java.util.List;

/**
 * 14-3-21 下午10:45
 *
 * @author Shengzhao Li
 */
public class MatchNoticeFormDtoLoader {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    private String guid;

    public MatchNoticeFormDtoLoader(String guid) {
        this.guid = guid;
    }

    public MatchNoticeFormDto load() {
        MatchNoticeFormDto formDto = new MatchNoticeFormDto(guid);
        if (!formDto.isNewly()) {
            MatchNotice notice = matchRepository.findMatchNoticeByGuid(guid);
            formDto = new MatchNoticeFormDto(notice);
        }

        loadOpponents(formDto);
        loadStadiums(formDto);
        return formDto;
    }

    private void loadOpponents(MatchNoticeFormDto formDto) {
        List<Club> clubs = clubRepository.findAllClubs();
        formDto.setClubs(ClubDto.toDtos(clubs));
    }

    private void loadStadiums(MatchNoticeFormDto formDto) {
        List<Stadium> stadiums = stadiumRepository.findAllStadiums();
        formDto.setStadiums(StadiumDto.toDtos(stadiums));
    }
}
