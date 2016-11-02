package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.Date;

/**
 * 赛季
 *
 * @author Shengzhao Li
 */
public class Season extends AbstractDomain {

    private transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);

    //赛季
    private String name;
    //时间段
    private Date start;
    private Date end;

    //备注
    private String remark;

    public Season() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            seasonRepository.saveSeason(this);
        } else {
            seasonRepository.updateSeason(this);
        }
    }

    public String name() {
        return name;
    }

    public Season name(String name) {
        this.name = name;
        return this;
    }

    public Date start() {
        return start;
    }

    public Season start(Date start) {
        this.start = start;
        return this;
    }

    public Date end() {
        return end;
    }

    public Season end(Date end) {
        this.end = end;
        return this;
    }

    public String remark() {
        return remark;
    }

    public Season remark(String remark) {
        this.remark = remark;
        return this;
    }

    public boolean finished() {
        return end.before(DateUtils.now());
    }

    public boolean pending() {
        Date now = DateUtils.now();
        return (!end.before(now)) && (!start.after(now));
    }

    public boolean created() {
        return start.after(DateUtils.now());
    }

    public Season archiveMe() {
        this.archived(true);
        this.saveOrUpdate();
        seasonRepository.archiveSeasonMatches(this);
        LogHandler.createLog("Archive Season [" + this + "], archive the season Matches too.", LogType.SEASON);
        return this;
    }
}