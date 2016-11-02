package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.shared.Repository;
import com.andaily.zhishifenzi.domain.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 14-3-9 上午11:15
 *
 * @author Shengzhao Li
 */
public interface MatchRepository extends Repository {

    Match findByGuid(String guid);

    void saveMatch(Match match);

    void updateMatch(Match match);

    Goal findGoalByGuid(String guid);

    void saveGoal(Goal goal);

    void updateGoal(Goal goal);

    MatchNotice findMatchNoticeByGuid(String guid);

    void saveMatchNotice(MatchNotice matchNotice);

    void updateMatchNotice(MatchNotice matchNotice);

    void saveMatchPlayer(MatchPlayer matchPlayer);

    List<MatchNotice> findListMatchNotices(Map<String, Object> map);

    int totalListMatchNotices(Map<String, Object> map);

    List<Match> findListMatches(Map<String, Object> map);

    int totalListMatches(Map<String, Object> map);

    List<MatchNotice> findPendingNotices();

    void deleteMatchPlayers(@Param("players") List<MatchPlayer> players);

    void saveMatchPlayers(@Param("players") List<MatchPlayer> players);

    void deleteGoals(@Param("goals") List<Goal> goals);

    void saveGoals(@Param("goals") List<Goal> goals);

    void archiveMatchPlayers(Match match);

    void archiveMatchGoals(Match match);

    List<Match> findLatestMatches(int limited);

    List<Match> findMatchesBySeason(@Param("season") Season season);

    List<Match> findMyMatches(Map<String, Object> map);

    int totalMyMatches(Map<String, Object> map);

    int totalScores();

    int totalPlayerScores(Player player);

    int totalAssists();

    int totalPlayerAssists(Player player);

    int totalMatches();

    List<Match> findPlayerMatches(Player player);

    List<Match> findClubMatches(Map<String, Object> map);

    int totalClubMatches(Map<String, Object> map);

    List<Match> findStadiumMatches(Map<String, Object> map);

    int totalStadiumMatches(Map<String, Object> map);

    List<Integer> findScoreRanges(String clubGuid);

    List<Match> findGoalRecordMatches(Map<String, Object> map);

    int totalGoalRecordMatches(Map<String, Object> map);

    List<Match> findAssistRecordMatches(Map<String, Object> map);

    int totalAssistRecordMatches(Map<String, Object> map);

    List<Match> findJoinRecordMatches(Map<String, Object> map);

    int totalJoinRecordMatches(Map<String, Object> map);

    List<Match> findPeriodRecordMatches(Map<String, Object> map);

    int totalPeriodRecordMatches(Map<String, Object> map);
}
