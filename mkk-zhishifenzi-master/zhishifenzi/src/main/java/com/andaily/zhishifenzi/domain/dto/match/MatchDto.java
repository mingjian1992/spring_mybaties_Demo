package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchPlayer;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-31 下午10:03
 *
 * @author Shengzhao Li
 */
public class MatchDto extends AbstractDto {

    //时间
    protected String matchTime;

    //主队进球
    protected int homeScores;
    //客队进球数
    protected int awayScores;
    /**
     * 选择该比赛的预告信息,
     * 若选择则对手,时间与场地的信息从它获取
     */
    protected MatchNoticeDto notice;

    //赛季
    protected SeasonDto season;

    //对手
    protected ClubDto opponent;
    //场地
    protected StadiumDto stadium;

    //进球信息
    protected List<GoalDto> goals = new ArrayList<>();

    //比赛队员
    protected List<PlayerDto> players = new ArrayList<>();

    //备注,总结信息
    protected String remark;
    //五人制
    protected boolean futsal;

    public MatchDto() {
    }

    public MatchDto(Match match) {
        this(match, false);
    }

    public MatchDto(Match match, boolean full) {
        super(match.guid());
        this.matchTime = DateUtils.toDateTime(match.matchTime());
        this.homeScores = match.homeScores();
        this.awayScores = match.awayScores();

        this.notice = match.notice() != null ? new MatchNoticeDto(match.notice()) : null;
        this.season = new SeasonDto(match.season());
        this.opponent = new ClubDto(match.opponent());

        this.stadium = new StadiumDto(match.stadium());
        this.remark = match.remark();
        this.futsal = match.futsal();

        if (full) {
            this.goals = GoalDto.toDtos(match.goals());
            List<MatchPlayer> matchPlayers = match.players();
            for (MatchPlayer matchPlayer : matchPlayers) {
                this.players.add(new PlayerDto(matchPlayer.player()));
            }
        }
    }

    public int getMatchPlayersSize() {
        return this.players.size();
    }

    public String getGoalPlayerNames() {
        StringBuilder sb = new StringBuilder();
        for (GoalDto goal : goals) {
            if (goal.isOwnGoal()) {
                sb.append("OG,");
            } else {
                sb.append(goal.getGoalPlayer().getFullName());
                PlayerDto assistantPlayer = goal.getAssistantPlayer();
                if (assistantPlayer != null) {
                    sb.append("(").append(assistantPlayer.getFullName()).append(")");
                } else {
                    sb.append("(无)");
                }
                sb.append(",");
            }
        }
        if (goals.isEmpty()) {
            sb.append("----");
        }
        int length = sb.length();
        return length > 0 ? sb.toString().substring(0, length - 1) : sb.toString();
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public int getHomeScores() {
        return homeScores;
    }

    public void setHomeScores(int homeScores) {
        this.homeScores = homeScores;
    }

    public int getAwayScores() {
        return awayScores;
    }

    public void setAwayScores(int awayScores) {
        this.awayScores = awayScores;
    }

    public MatchNoticeDto getNotice() {
        return notice;
    }

    public void setNotice(MatchNoticeDto notice) {
        this.notice = notice;
    }

    public SeasonDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }

    public ClubDto getOpponent() {
        return opponent;
    }

    public void setOpponent(ClubDto opponent) {
        this.opponent = opponent;
    }

    public StadiumDto getStadium() {
        return stadium;
    }

    public void setStadium(StadiumDto stadium) {
        this.stadium = stadium;
    }

    public List<GoalDto> getGoals() {
        return goals;
    }

    public int getGoalsSize() {
        return goals.size();
    }

    public void setGoals(List<GoalDto> goals) {
        this.goals = goals;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public String getRemark() {
        return remark;
    }

    public String getShortRemark() {
        if (remark != null && remark.length() > 20) {
            return remark.substring(0, 20) + "...";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isFutsal() {
        return futsal;
    }

    public void setFutsal(boolean futsal) {
        this.futsal = futsal;
    }

    public static List<MatchDto> toDtos(List<Match> matches, boolean full) {
        List<MatchDto> dtos = new ArrayList<>(matches.size());
        for (Match match : matches) {
            dtos.add(new MatchDto(match, full));
        }
        return dtos;
    }
}
