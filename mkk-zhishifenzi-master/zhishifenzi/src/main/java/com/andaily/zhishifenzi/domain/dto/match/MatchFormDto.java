package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchPlayer;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 14-4-3 下午11:42
 *
 * @author Shengzhao Li
 */
public class MatchFormDto extends MatchDto {

    private List<MatchNoticeDto> notices = new ArrayList<>();
    private List<StadiumDto> stadiums = new ArrayList<>();
    private List<ClubDto> clubs = new ArrayList<>();

    private List<SeasonDto> seasons = new ArrayList<>();

    private List<String> joinPlayerGuids = new ArrayList<>();

    //比赛照片
    private List<MultipartFile> photos = new ArrayList<>();

    public MatchFormDto() {
        super();
    }

    public MatchFormDto(Match match) {
        super(match, true);

        List<MatchPlayer> matchPlayers = match.players();
        for (MatchPlayer matchPlayer : matchPlayers) {
            joinPlayerGuids.add(matchPlayer.player().guid());
        }
    }

    public MatchFormDto(String guid) {
        this.guid = guid;
    }

    public List<String> getJoinPlayerGuids() {
        return joinPlayerGuids;
    }

    public void setJoinPlayerGuids(List<String> joinPlayerGuids) {
        this.joinPlayerGuids = joinPlayerGuids;
    }

    public boolean isNewly() {
        return super.isNewly() || MatchUtils.isCreate(guid);
    }

    public List<MatchNoticeDto> getNotices() {
        return notices;
    }

    public void setNotices(List<MatchNoticeDto> notices) {
        this.notices = notices;
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

    public List<SeasonDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDto> seasons) {
        this.seasons = seasons;
    }

    public Match toDomain(Match match) {
        return match.remark(remark)
                .homeScores(homeScores)
                .awayScores(awayScores)
                .futsal(futsal)
                .matchTime(DateUtils.getDate(matchTime, DateUtils.DATE_TIME_FORMAT));
    }

    public List<MultipartFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<MultipartFile> photos) {
        this.photos = photos;
    }

    public List<Photo> generatePhotos() {
        List<Photo> photoList = new ArrayList<>();
        for (MultipartFile photo : photos) {
            if (photo != null && !photo.isEmpty()) {
                try {
                    Photo photo1 = new Photo(photo.getOriginalFilename(), photo.getBytes());
                    photoList.add(photo1);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return photoList;
    }
}
