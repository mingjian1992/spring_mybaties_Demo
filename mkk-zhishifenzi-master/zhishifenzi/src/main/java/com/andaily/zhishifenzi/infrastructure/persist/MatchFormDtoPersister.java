package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.album.AlbumRepository;
import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.match.GoalDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchFormDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.match.*;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 14-4-6 下午2:19
 *
 * @author Shengzhao Li
 */
public class MatchFormDtoPersister {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    private transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);
    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);
    private transient AlbumRepository albumRepository = BeanProvider.getBean(AlbumRepository.class);


    private MatchFormDto formDto;

    public MatchFormDtoPersister(MatchFormDto formDto) {
        this.formDto = formDto;
    }

    public void persist() {
        if (formDto.isNewly()) {
            saveMatch();
        } else {
            updateMatch();
        }
    }

    //ignore notice
    private void updateMatch() {
        Match match = matchRepository.findByGuid(formDto.getGuid());
        match = formDto.toDomain(match);

        setStadium(match);
        setSeason(match);
        setOpponent(match);

        match.saveOrUpdate();
        setPlayersAndGoals(match);
        LogHandler.createLog("Update Match [" + match + "]", LogType.MATCH);
    }

    private void saveMatch() {
        Match match = formDto.toDomain(new Match())
                .creator(SecurityUtils.currentUser());
        setStadium(match);
        setSeason(match);
        setOpponent(match);
        setNotice(match);

        match.saveOrUpdate();
        final Match newMatch = matchRepository.findByGuid(match.guid());
        setPlayersAndGoals(newMatch);
        //handle photos
        checkAndPersistMatchAlbum(newMatch);
        //send mail
        CreateMatchMailSender matchMailSender = new CreateMatchMailSender(newMatch);
        matchMailSender.send();
        LogHandler.createLog("Add Match [" + match + "]", LogType.MATCH);
    }

    private void checkAndPersistMatchAlbum(Match newMatch) {
        List<Photo> photos = formDto.generatePhotos();
        if (photos.isEmpty()) {
            return;
        }

        final String matchDateAsText = DateUtils.toDateText(newMatch.matchTime());
        String albumName = matchDateAsText + " 比赛相册";
        String albumDescription = matchDateAsText + " 比赛照片, 对手: " + newMatch.opponent().name() + ", 比分: " + newMatch.homeScores() + ":" + newMatch.awayScores();
        Album album = new Album()
                .name(albumName)
                .description(albumDescription)
                .match(newMatch)
                .creator(SecurityUtils.currentUser());
        album.saveOrUpdate();

        final Album persistedAlbum = albumRepository.findByGuid(album.guid());
        for (Photo photo : photos) {
            photo.album(persistedAlbum)
                    .saveOrUpdate();
        }
    }

    private void setPlayersAndGoals(Match match) {
        Map<String, Player> allPlayers = allPlayers();
        saveMatchPlayers(allPlayers, match);
        saveGoals(allPlayers, match);
    }

    private Map<String, Player> allPlayers() {
        List<Player> allPlayers = playerRepository.findAvailablePlayers();
        Map<String, Player> playerMap = new HashMap<>();
        for (Player allPlayer : allPlayers) {
            playerMap.put(allPlayer.guid(), allPlayer);
        }
        return playerMap;
    }

    private void saveGoals(Map<String, Player> allPlayers, Match match) {
        List<Goal> goals = match.goals();
        if (goals.size() > 0) {
            //clean old
            matchRepository.deleteGoals(goals);
            goals.clear();
        }

        List<GoalDto> goalDtos = formDto.getGoals();
        for (GoalDto goalDto : goalDtos) {
            if (goalDto.isOwnGoal()) {
                goals.add(new Goal()
                        .ownGoal(true)
                        .match(match)
                        .goalTime(goalDto.getGoalTime()));
            } else {
                Player goalPlayer = allPlayers.get(goalDto.getGoalPlayer().getGuid());
                Player assPlayer = allPlayers.get(goalDto.getAssistantPlayer().getGuid());
                goals.add(new Goal()
                        .goalPlayer(goalPlayer)
                        .assistantPlayer(assPlayer)
                        .match(match)
                        .goalTime(goalDto.getGoalTime()));
            }
        }

        if (goals.size() > 0) {
            matchRepository.saveGoals(goals);
        }
    }

    private void saveMatchPlayers(Map<String, Player> allPlayers, Match match) {
        List<MatchPlayer> players = match.players();
        if (players.size() > 0) {
            //clean old
            matchRepository.deleteMatchPlayers(players);
            players.clear();
        }

        List<String> joinPlayerGuids = formDto.getJoinPlayerGuids();
        for (String joinPlayerGuid : joinPlayerGuids) {
            Player player = allPlayers.get(joinPlayerGuid);
            players.add(new MatchPlayer(match, player));
        }
        matchRepository.saveMatchPlayers(players);
    }

    private MatchNotice setNotice(Match match) {
        String guid = formDto.getNotice().getGuid();
        if (StringUtils.isNotEmpty(guid)) {
            MatchNotice notice = matchRepository.findMatchNoticeByGuid(guid);
            match.notice(notice);
            //change notice pending = false
            notice.match(match)
                    .pending(false)
                    .saveOrUpdate();
            return notice;
        }
        return null;
    }

    private void setOpponent(Match match) {
        Club club = clubRepository.findByGuid(formDto.getOpponent().getGuid());
        match.opponent(club);
    }

    private void setSeason(Match match) {
        Season season = seasonRepository.findByGuid(formDto.getSeason().getGuid());
        match.season(season);
    }

    private Match setStadium(Match match) {
        Stadium stadium = stadiumRepository.findByGuid(formDto.getStadium().getGuid());
        return match.stadium(stadium);
    }
}
