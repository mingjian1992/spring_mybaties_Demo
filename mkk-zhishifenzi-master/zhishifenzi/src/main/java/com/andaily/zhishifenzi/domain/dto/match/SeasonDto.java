package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 14-3-15 上午12:09
 *
 * @author Shengzhao Li
 */
public class SeasonDto extends AbstractDto {

    protected String name;

    protected String start;
    protected String end;
    protected String remark;

    protected boolean finished;
    protected boolean pending;
    protected boolean created;


    public SeasonDto() {
    }

    public SeasonDto(Season season) {
        super(season.guid());
        this.name = season.name();
        this.start = DateUtils.toDateText(season.start());
        this.end = DateUtils.toDateText(season.end());
        this.remark = season.remark();

        this.finished = season.finished();
        this.pending = season.pending();
        this.created = season.created();
    }

    public SeasonDto(String guid) {
        this.guid = guid;
    }


    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public String getStartEnd() {
        return start + " / " + end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public static List<SeasonDto> toDtos(List<Season> seasons) {
        List<SeasonDto> dtos = new ArrayList<>(seasons.size());
        for (Season season : seasons) {
            dtos.add(new SeasonDto(season));
        }
        return dtos;
    }

    public Season toDomain() {
        return new Season().name(name)
                .start(DateUtils.getDate(start))
                .end(DateUtils.getDate(end))
                .remark(remark);
    }
}
