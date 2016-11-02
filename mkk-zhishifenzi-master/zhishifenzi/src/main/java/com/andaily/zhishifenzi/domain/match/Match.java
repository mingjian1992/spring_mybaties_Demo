package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 比赛
 *
 * @author Shengzhao Li
 */
public class Match extends AbstractDomain {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);

    //时间
    private Date matchTime;

    //主队进球
    private int homeScores;
    //客队进球数
    private int awayScores;
    /**
     * 选择该比赛的预告信息,
     * 若选择则对手,时间与场地的信息从它获取
     */
    private MatchNotice notice;

    //赛季
    private Season season;

    //对手
    private Club opponent;
    //场地
    private Stadium stadium;

    //进球信息
    private List<Goal> goals = new ArrayList<>();

    //比赛队员
    private List<MatchPlayer> players = new ArrayList<>();

    //备注,总结信息
    private String remark;
    //五人制
    private boolean futsal;

    private User creator;

    public Match() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            matchRepository.saveMatch(this);
        } else {
            matchRepository.updateMatch(this);
        }
    }

    public Date matchTime() {
        return matchTime;
    }

    public Match matchTime(Date matchTime) {
        this.matchTime = matchTime;
        return this;
    }

    public int homeScores() {
        return homeScores;
    }

    public Match homeScores(int homeScores) {
        this.homeScores = homeScores;
        return this;
    }

    public int awayScores() {
        return awayScores;
    }

    public Match awayScores(int awayScores) {
        this.awayScores = awayScores;
        return this;
    }

    public MatchNotice notice() {
        return notice;
    }

    public Match notice(MatchNotice notice) {
        this.notice = notice;
        return this;
    }

    public Season season() {
        return season;
    }

    public Match season(Season season) {
        this.season = season;
        return this;
    }

    public Club opponent() {
        return opponent;
    }

    public Match opponent(Club opponent) {
        this.opponent = opponent;
        return this;
    }

    public Stadium stadium() {
        return stadium;
    }

    public Match stadium(Stadium stadium) {
        this.stadium = stadium;
        return this;
    }

    public List<Goal> goals() {
        return goals;
    }

    public List<MatchPlayer> players() {
        return players;
    }

    public String remark() {
        return remark;
    }

    public Match remark(String remark) {
        this.remark = remark;
        return this;
    }

    public boolean futsal() {
        return futsal;
    }

    public Match futsal(boolean futsal) {
        this.futsal = futsal;
        return this;
    }

    public User creator() {
        return creator;
    }

    public Match creator(User creator) {
        this.creator = creator;
        return this;
    }

    public Match archiveMe() {
        this.archived(true);
        this.saveOrUpdate();
        //archive goals and players
        matchRepository.archiveMatchPlayers(this);
        matchRepository.archiveMatchGoals(this);

        LogHandler.createLog("Archive Match [" + this + "]", LogType.MATCH);
        return this;
    }
}