package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.MatchNotice;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-21 下午10:40
 *
 * @author Shengzhao Li
 */
public class MatchNoticeFormDto extends MatchNoticeDto {

    private List<StadiumDto> stadiums = new ArrayList<>();
    private List<ClubDto> clubs = new ArrayList<>();

    public MatchNoticeFormDto() {
        super();
    }

    public MatchNoticeFormDto(MatchNotice notice) {
        super(notice);
    }

    public MatchNoticeFormDto(String guid) {
        this.guid = guid;
    }

    public boolean isNewly() {
        return super.isNewly() || MatchUtils.isCreate(guid);
    }

    public List<StadiumDto> getStadiums() {
        return stadiums;
    }

    public void setStadiums(List<StadiumDto> stadiums) {
        this.stadiums = stadiums;
    }

    public List<ClubDto> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDto> clubs) {
        this.clubs = clubs;
    }

    public MatchNotice updateDomain(MatchNotice notice) {
        return notice.time(DateUtils.getDate(time, DateUtils.DATE_TIME_FORMAT))
                .remark(remark);
    }
}
