package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.Date;

/**
 * 比赛预告
 *
 * @author Shengzhao Li
 */
public class MatchNotice extends AbstractDomain {

    //日期时间 格式
    public static final String DATE_WEEK_FORMAT = "MM月dd日 (E),  HH:mm";

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);

    private User creator;
    //时间
    private Date time;
    //场地
    private Stadium stadium;
    //对手
    private Club opponent;
    //备注
    private String remark;
    //是否激活,激活则在首页显示提示
    //可同时激活多个
    private boolean pending;

    private Match match;

    public MatchNotice() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            matchRepository.saveMatchNotice(this);
        } else {
            matchRepository.updateMatchNotice(this);
        }
    }

    public User creator() {
        return creator;
    }

    public MatchNotice creator(User creator) {
        this.creator = creator;
        return this;
    }

    public Date time() {
        return time;
    }

    public MatchNotice time(Date time) {
        this.time = time;
        return this;
    }

    public Stadium stadium() {
        return stadium;
    }

    public MatchNotice stadium(Stadium stadium) {
        this.stadium = stadium;
        return this;
    }

    public Club opponent() {
        return opponent;
    }

    public MatchNotice opponent(Club opponent) {
        this.opponent = opponent;
        return this;
    }

    public String remark() {
        return remark;
    }

    public MatchNotice remark(String remark) {
        this.remark = remark;
        return this;
    }

    public boolean pending() {
        return pending;
    }

    public MatchNotice pending(boolean pending) {
        this.pending = pending;
        return this;
    }

    public MatchNotice publish() {
        if (this.pending || time.before(DateUtils.now())) {
            throw new IllegalStateException("The MatchNotice is published or the time is expired");
        }
        this.pending(true);
        this.saveOrUpdate();
        //send mail
        MatchNoticeMailSender matchNoticeMailSender = new MatchNoticeMailSender(this);
        matchNoticeMailSender.send();
        LogHandler.createLog("Publish MatchNotice [" + this + "] and sent mail notice to every player", LogType.MATCH_NOTICE);
        return this;
    }

    public MatchNotice cancelPublish() {
        if (!this.pending) {
            throw new IllegalStateException("The MatchNotice is not published, do not use cancel");
        }
        this.pending(false);
        this.saveOrUpdate();
        LogHandler.createLog("Cancel publish MatchNotice [" + this + "]", LogType.MATCH_NOTICE);
        return this;
    }

    public MatchNotice archiveMe() {
        this.archived(true);
        this.saveOrUpdate();
        LogHandler.createLog("Archive MatchNotice [" + this + "]", LogType.MATCH_NOTICE);
        return this;
    }

    public Match match() {
        return match;
    }

    public MatchNotice match(Match match) {
        this.match = match;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "time=" + time +
                ", stadium=" + stadium +
                ", opponent=" + opponent +
                ", remark='" + remark + '\'' +
                ", pending=" + pending +
                ", match=" + match +
                '}';
    }
}