package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.match.*;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerPosition;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRepository;
import com.andaily.zhishifenzi.domain.user.UserRole;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * 14-3-10 下午9:20
 *
 * @author Shengzhao Li
 */
public class MatchRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private StadiumRepository stadiumRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findGoalByGuid() {
        Goal goal = matchRepository.findGoalByGuid(GuidGenerator.generate());
        assertNull(goal);

        Player player = newPlayer();

        goal = new Goal().goalPlayer(player)
                .assistantPlayer(newPlayer())
                .goalTime(45);
        goal.saveOrUpdate();

        goal = matchRepository.findGoalByGuid(goal.guid());
        assertNotNull(goal);
        assertNotNull(goal.goalPlayer());
        assertNotNull(goal.assistantPlayer());

        goal.goalPlayer(newPlayer()).saveOrUpdate();

        goal = matchRepository.findGoalByGuid(goal.guid());
        assertNotNull(goal);
        assertNotEquals(goal.goalPlayer(), player);
    }


    @Test
    public void findListMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("result", 1);
        map.put("seasonGuid", GuidGenerator.generate());
        map.put("stadiumGuid", GuidGenerator.generate());
        map.put("matchDate", "2014-09-08");
        map.put("remark", "%23%");
        map.put("opponentGuid", GuidGenerator.generate());


        List<Match> list = matchRepository.findListMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalListMatches(map);
        assertEquals(count, 0);

    }

    @Test
    public void findGoalRecordMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("guid", GuidGenerator.generate());


        List<Match> list = matchRepository.findGoalRecordMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalGoalRecordMatches(map);
        assertEquals(count, 0);

    }

    @Test
    public void findAssistRecordMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("guid", GuidGenerator.generate());


        List<Match> list = matchRepository.findAssistRecordMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalAssistRecordMatches(map);
        assertEquals(count, 0);

    }

    @Test
    public void findJoinRecordMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("guid", GuidGenerator.generate());


        List<Match> list = matchRepository.findJoinRecordMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalJoinRecordMatches(map);
        assertEquals(count, 0);

    }

    @Test
    public void findPeriodRecordMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("start", "2010-03-06");
        map.put("end", "2011-06-19");


        List<Match> list = matchRepository.findPeriodRecordMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalPeriodRecordMatches(map);
        assertEquals(count, 0);

    }

    @Test
    public void findMyMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("seasonGuid", GuidGenerator.generate());
        map.put("result", 3);
        map.put("hasGoal", Boolean.TRUE);
        map.put("hasAssist", Boolean.TRUE);


        List<Match> list = matchRepository.findMyMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalMyMatches(map);
        assertEquals(count, 0);

    }

    @Test
    public void findScoreRanges() {
        List<Integer> list = matchRepository.findScoreRanges(GuidGenerator.generate());
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

    @Test
    public void findClubMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("clubGuid", GuidGenerator.generate());

        List<Match> list = matchRepository.findClubMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalClubMatches(map);
        assertEquals(count, 0);
    }

    @Test
    public void findStadiumMatches() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("stadiumGuid", GuidGenerator.generate());

        List<Match> list = matchRepository.findStadiumMatches(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalStadiumMatches(map);
        assertEquals(count, 0);
    }

    private Player newPlayer() {
        Player player = new Player().fullName("list" + Math.random()).position(PlayerPosition.CB);
        player.saveOrUpdate();
        player = playerRepository.findByGuid(player.guid());
        return player;
    }


    @Test
    public void deleteMatchPlayers() {
        matchRepository.deleteMatchPlayers(Collections.<MatchPlayer>emptyList());
    }

    @Test
    public void totalScores() {
        int i = matchRepository.totalScores();
        assertEquals(i, 0);
    }

    @Test
    public void totalAssists() {
        int i = matchRepository.totalAssists();
        assertEquals(i, 0);
    }

    @Test
    public void totalMatches() {
        int i = matchRepository.totalMatches();
        assertEquals(i, 0);
    }

    @Test
    public void totalPlayerScores() {
        Player player = newPlayer();

        int i = matchRepository.totalPlayerScores(player);
        assertEquals(i, 0);
    }

    @Test
    public void findPlayerMatches() {
        Player player = newPlayer();

        List<Match> list = matchRepository.findPlayerMatches(player);
        assertEquals(list.size(), 0);
    }

    @Test
    public void totalPlayerAssists() {
        Player player = newPlayer();

        int i = matchRepository.totalPlayerAssists(player);
        assertEquals(i, 0);
    }

    @Test
    public void findMatchesBySeason() {
        Season season = newSeason();
        List<Match> list = matchRepository.findMatchesBySeason(season);
        assertEquals(list.size(), 0);
    }

    @Test
    public void archiveMatchPlayers() {
        Match match = new Match();
        matchRepository.saveMatch(match);
        match = matchRepository.findByGuid(match.guid());
        matchRepository.archiveMatchPlayers(match);
    }

    @Test
    public void findLatestMatches() {
        Match match = new Match();
        matchRepository.saveMatch(match);

        List<Match> list = matchRepository.findLatestMatches(3);
        assertEquals(list.size(), 1);
    }

    @Test
    public void archiveMatchGoals() {
        Match match = new Match();
        matchRepository.saveMatch(match);
        match = matchRepository.findByGuid(match.guid());
        matchRepository.archiveMatchGoals(match);
    }

    @Test
    public void saveMatchPlayers() {
        Match match = new Match();
        matchRepository.saveMatch(match);
        match = matchRepository.findByGuid(match.guid());

        List<MatchPlayer> matchPlayers = Arrays.asList(new MatchPlayer(match, null), new MatchPlayer(match, null));
        matchRepository.saveMatchPlayers(matchPlayers);

        match = matchRepository.findByGuid(match.guid());
        assertEquals(match.players().size(), 2);
    }

    @Test
    public void findMatchNoticeByGuid() {
        MatchNotice matchNotice = matchRepository.findMatchNoticeByGuid(GuidGenerator.generate());
        assertNull(matchNotice);

        Club club = newClub();

        Stadium stadium = newStadium();

        MatchNotice notice = new MatchNotice().opponent(club)
                .stadium(stadium)
                .time(DateUtils.now()).remark("穿新球衣");
        notice.saveOrUpdate();

        matchNotice = matchRepository.findMatchNoticeByGuid(notice.guid());
        assertNotNull(matchNotice);
        assertNotNull(matchNotice.opponent());
        assertNotNull(matchNotice.stadium());
        assertNotNull(matchNotice.time());

        matchNotice.stadium(newStadium()).saveOrUpdate();

        matchNotice = matchRepository.findMatchNoticeByGuid(matchNotice.guid());
        assertNotNull(matchNotice);
        assertNotEquals(matchNotice.stadium(), stadium);

    }

    private Club newClub() {
        Club club = new Club().name("iloosd").contact("123344").remark("ooldodo");
        club.saveOrUpdate();
        club = clubRepository.findByGuid(club.guid());
        return club;
    }

    private Stadium newStadium() {
        Stadium stadium = new Stadium().name("lod").contact("3323");
        stadium.saveOrUpdate();
        stadium = stadiumRepository.findByGuid(stadium.guid());
        return stadium;
    }

    @Test
    public void findByGuid() {
        Match match = matchRepository.findByGuid(GuidGenerator.generate());
        assertNull(match);

        Club club = newClub();
        Stadium stadium = newStadium();
        Season season = newSeason();

        User user = newUser();

        Date matchTime = DateUtils.now();
        match = new Match().matchTime(matchTime)
                .opponent(club)
                .stadium(stadium)
                .remark("okok")
                .season(season).creator(user)
                .awayScores(1).homeScores(3)
                .notice(newMatchNotice(stadium, club, matchTime));
        match.saveOrUpdate();

        match = matchRepository.findByGuid(match.guid());
        assertNotNull(match);
        assertNotNull(match.creator());
        assertNotNull(match.stadium());
        assertNotNull(match.opponent());
        assertNotNull(match.season());
        assertNotNull(match.notice());

        Player player = newPlayer();
        MatchPlayer matchPlayer = new MatchPlayer(match, player);
        matchRepository.saveMatchPlayer(matchPlayer);
        Player player2 = newPlayer();
        MatchPlayer matchPlayer2 = new MatchPlayer(match, player2);
        matchRepository.saveMatchPlayer(matchPlayer2);

        Goal goal = new Goal().match(match);
        goal.saveOrUpdate();

        match = matchRepository.findByGuid(match.guid());
        assertNotNull(match);
        assertEquals(match.players().size(), 2);
        assertEquals(match.goals().size(), 1);

        match.remark("abc").saveOrUpdate();

        match = matchRepository.findByGuid(match.guid());
        assertNotNull(match);
        assertEquals(match.players().size(), 2);
        assertEquals(match.goals().size(), 1);
        assertEquals(match.remark(), "abc");
    }

    private MatchNotice newMatchNotice(Stadium stadium, Club club, Date matchTime) {
        MatchNotice notice = new MatchNotice().stadium(stadium)
                .opponent(club).time(matchTime);
        notice.saveOrUpdate();
        return matchRepository.findMatchNoticeByGuid(notice.guid());
    }

    private User newUser() {
        User user = new User().userRole(UserRole.CAPTAIN).username("test").password("1233");
        user.saveOrUpdate();
        user = userRepository.findByGuid(user.guid());
        return user;
    }

    private Season newSeason() {
        Season season = new Season().name("2013");
        season.saveOrUpdate();
        season = seasonRepository.findByGuid(season.guid());
        return season;
    }

    @Test
    public void findListMatchNotices() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("stadiumGuid", GuidGenerator.generate());
        map.put("opponentGuid", null);

        List<MatchNotice> list = matchRepository.findListMatchNotices(map);
        assertEquals(list.size(), 0);

        int count = matchRepository.totalListMatchNotices(map);
        assertEquals(count, 0);
    }

    @Test
    public void findPendingNotices() {
        List<MatchNotice> list = matchRepository.findPendingNotices();
        assertEquals(list.size(), 0);
    }
}
