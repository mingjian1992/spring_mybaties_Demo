package com.andaily.zhishifenzi.domain.dto;

import com.andaily.zhishifenzi.domain.dto.album.PhotoDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeDto;
import com.andaily.zhishifenzi.domain.dto.statistics.AssistantSortDto;
import com.andaily.zhishifenzi.domain.dto.statistics.GoalSortDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class IndexDto extends AbstractDto {


    private List<MatchNoticeDto> notices = new ArrayList<>();
    //max 12
    private List<MatchDto> matches = new ArrayList<>();

    private GoalSortDto goalSortDto;
    private AssistantSortDto assistantSortDto;

    //首页滚动图片
    private List<PhotoDto> headPhotos = new ArrayList<>();

    /**
     * @see com.andaily.zhishifenzi.domain.commons.GlobalSetting#useDefaultFrontPhotos
     */
    private boolean useDefaultFrontPhotos;

    public IndexDto() {
    }

    public List<MatchNoticeDto> getNotices() {
        return notices;
    }

    public IndexDto setNotices(List<MatchNoticeDto> notices) {
        this.notices = notices;
        return this;
    }

    public List<MatchDto> getMatches() {
        return matches;
    }

    public IndexDto setMatches(List<MatchDto> matches) {
        this.matches = matches;
        return this;
    }

    public GoalSortDto getGoalSortDto() {
        return goalSortDto;
    }

    public IndexDto setGoalSortDto(GoalSortDto goalSortDto) {
        this.goalSortDto = goalSortDto;
        return this;
    }

    public AssistantSortDto getAssistantSortDto() {
        return assistantSortDto;
    }

    public IndexDto setAssistantSortDto(AssistantSortDto assistantSortDto) {
        this.assistantSortDto = assistantSortDto;
        return this;
    }

    public List<PhotoDto> getHeadPhotos() {
        return headPhotos;
    }

    public IndexDto setHeadPhotos(List<PhotoDto> headPhotos) {
        this.headPhotos = headPhotos;
        return this;
    }

    public boolean isUseDefaultFrontPhotos() {
        return useDefaultFrontPhotos;
    }

    public IndexDto setUseDefaultFrontPhotos(boolean useDefaultFrontPhotos) {
        this.useDefaultFrontPhotos = useDefaultFrontPhotos;
        return this;
    }
}
