package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.MatchNotice;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 14-3-21 下午9:21
 *
 * @author Shengzhao Li
 */
public class MatchNoticeDto extends AbstractDto {



    //时间
    protected String time;
    protected String previewTime;
    //场地
    protected StadiumDto stadium;
    //对手
    protected ClubDto opponent;
    //备注
    protected String remark;
    //是否激活,激活则在首页显示提示
    //可同时激活多个
    protected boolean pending;

    //比赛时间是否已过去
    protected boolean pastTime;

    public MatchNoticeDto() {
    }

    public MatchNoticeDto(MatchNotice notice) {
        super(notice.guid());
        Date time1 = notice.time();
        this.time = DateUtils.toDateTime(time1);
        this.pastTime = time1.before(DateUtils.now());
        this.previewTime = DateUtils.toDateText(time1, MatchNotice.DATE_WEEK_FORMAT);

        this.stadium = new StadiumDto(notice.stadium());
        this.opponent = new ClubDto(notice.opponent());
        this.remark = notice.remark();
        this.pending = notice.pending();
    }

    //3月2日，10：00，太平寺 4 号场地，对手：昌达足球队。穿新球衣。
    public String getPreviewText() {
        StringBuilder sb = new StringBuilder();
        sb.append(previewTime).append("，  ")
                .append(stadium.getName()).append("， 对手:  ")
                .append(opponent.getName()).append("。")
                .append(remark);
        return sb.toString();
    }

    public String getPreviewTime() {
        return previewTime;
    }

    public void setPreviewTime(String previewTime) {
        this.previewTime = previewTime;
    }

    public boolean isPastTime() {
        return pastTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StadiumDto getStadium() {
        return stadium;
    }

    public void setStadium(StadiumDto stadium) {
        this.stadium = stadium;
    }

    public ClubDto getOpponent() {
        return opponent;
    }

    public void setOpponent(ClubDto opponent) {
        this.opponent = opponent;
    }

    public String getRemark() {
        return remark;
    }

    public String getShortRemark() {
        if (remark != null && remark.length() > 30) {
            return remark.substring(0, 30) + "...";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public static List<MatchNoticeDto> toDtos(List<MatchNotice> notices) {
        List<MatchNoticeDto> dtos = new ArrayList<>(notices.size());
        for (MatchNotice notice : notices) {
            dtos.add(new MatchNoticeDto(notice));
        }
        return dtos;
    }
}
