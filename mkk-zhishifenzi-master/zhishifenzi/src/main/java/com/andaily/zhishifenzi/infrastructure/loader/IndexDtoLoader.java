package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.commons.CommonsRepository;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.GlobalSettingDto;
import com.andaily.zhishifenzi.domain.dto.IndexDto;
import com.andaily.zhishifenzi.domain.dto.album.PhotoDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchNotice;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.generator.AssistantSortDtoGenerator;
import com.andaily.zhishifenzi.infrastructure.generator.GoalSortDtoGenerator;
import com.andaily.zhishifenzi.service.CommonsService;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class IndexDtoLoader {


    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient CommonsRepository commonsRepository = BeanProvider.getBean(CommonsRepository.class);

    private transient CommonsService commonsService = BeanProvider.getBean(CommonsService.class);

    public IndexDtoLoader() {
    }

    public IndexDto load() {
        IndexDto indexDto = new IndexDto();

        loadMatchNotices(indexDto);
        loadLatestMatches(indexDto);
        loadGoalSortDto(indexDto);
        loadAssistantSortDto(indexDto);

        loadUseDefaultFrontPhotos(indexDto);
        loadHeadPhotos(indexDto);

        return indexDto;
    }

    private void loadUseDefaultFrontPhotos(IndexDto indexDto) {
        final GlobalSettingDto globalSettingDto = commonsService.loadGlobalSettingDto();
        indexDto.setUseDefaultFrontPhotos(globalSettingDto.isUseDefaultFrontPhotos());
    }

    private void loadHeadPhotos(IndexDto indexDto) {
        if (!indexDto.isUseDefaultFrontPhotos()) {
            List<Photo> photos = commonsRepository.findHeadPhotos();
            indexDto.setHeadPhotos(PhotoDto.toDtos(photos));
        }
    }

    private void loadAssistantSortDto(IndexDto indexDto) {
        AssistantSortDtoGenerator generator = new AssistantSortDtoGenerator();
        indexDto.setAssistantSortDto(generator.generateAll());
    }

    private void loadGoalSortDto(IndexDto indexDto) {
        GoalSortDtoGenerator generator = new GoalSortDtoGenerator();
        indexDto.setGoalSortDto(generator.generateAll());
    }

    private void loadLatestMatches(IndexDto indexDto) {
        List<Match> matches = matchRepository.findLatestMatches(12);
        indexDto.setMatches(MatchDto.toDtos(matches, true));
    }

    private void loadMatchNotices(IndexDto indexDto) {
        List<MatchNotice> notices = matchRepository.findPendingNotices();
        indexDto.setNotices(MatchNoticeDto.toDtos(notices));
    }
}
