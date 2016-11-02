package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeFormDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.match.MatchNotice;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;

/**
 * 14-3-21 下午11:49
 *
 * @author Shengzhao Li
 */
public class MatchNoticeFormDtoPersister {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    private MatchNoticeFormDto formDto;

    public MatchNoticeFormDtoPersister(MatchNoticeFormDto formDto) {
        this.formDto = formDto;
    }

    public String persist() {
        if (formDto.isNewly()) {
            return saveMatchNotice();
        } else {
            return updateMatchNotice();
        }
    }

    private String updateMatchNotice() {
        MatchNotice matchNotice = matchRepository.findMatchNoticeByGuid(formDto.getGuid());
        formDto.updateDomain(matchNotice);
        setStadiumAndOpponent(matchNotice)
                .saveOrUpdate();
        LogHandler.createLog("Update MatchNotice [" + matchNotice + "]", LogType.MATCH_NOTICE);
        return matchNotice.guid();
    }

    private String saveMatchNotice() {
        MatchNotice notice = formDto.updateDomain(new MatchNotice())
                .creator(SecurityUtils.currentUser());
        setStadiumAndOpponent(notice)
                .saveOrUpdate();
        LogHandler.createLog("Create MatchNotice [" + notice + "]", LogType.MATCH_NOTICE);
        return notice.guid();
    }

    private MatchNotice setStadiumAndOpponent(MatchNotice notice) {
        Stadium stadium = stadiumRepository.findByGuid(formDto.getStadium().getGuid());
        Club club = clubRepository.findByGuid(formDto.getOpponent().getGuid());
        return notice.stadium(stadium).opponent(club);
    }
}
