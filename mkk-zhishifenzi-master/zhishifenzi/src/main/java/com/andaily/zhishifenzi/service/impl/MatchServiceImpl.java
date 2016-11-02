package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.dto.match.*;
import com.andaily.zhishifenzi.domain.dto.statistics.MainDataDto;
import com.andaily.zhishifenzi.domain.dto.statistics.RecordMatchDto;
import com.andaily.zhishifenzi.domain.dto.statistics.ZsfzRecordDto;
import com.andaily.zhishifenzi.domain.dto.statistics.ZsfzRecordMatchListDto;
import com.andaily.zhishifenzi.domain.match.*;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.infrastructure.loader.*;
import com.andaily.zhishifenzi.infrastructure.persist.MatchFormDtoPersister;
import com.andaily.zhishifenzi.infrastructure.persist.MatchNoticeFormDtoPersister;
import com.andaily.zhishifenzi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 14-3-16 上午1:21
 *
 * @author Shengzhao Li
 */
@Service("matchService")
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private SeasonRepository seasonRepository;


    @Override
    public MatchNoticeListDto loadMatchNoticeListDto(MatchNoticeListDto listDto) {
        MatchNoticeListDtoLoader loader = new MatchNoticeListDtoLoader(listDto);
        return loader.load();
    }

    @Override
    public MatchNoticeFormDto loadMatchNoticeFormDto(String guid) {
        MatchNoticeFormDtoLoader loader = new MatchNoticeFormDtoLoader(guid);
        return loader.load();
    }

    @Override
    public String persistMatchNoticeFormDto(MatchNoticeFormDto formDto) {
        MatchNoticeFormDtoPersister persister = new MatchNoticeFormDtoPersister(formDto);
        return persister.persist();
    }

    @Override
    public void publishMatchNotice(String guid) {
        MatchNotice notice = matchRepository.findMatchNoticeByGuid(guid);
        notice.publish();
    }

    @Override
    public void cancelPublishMatchNotice(String guid) {
        MatchNotice notice = matchRepository.findMatchNoticeByGuid(guid);
        notice.cancelPublish();
    }

    @Override
    public void archiveMatchNotice(String guid) {
        MatchNotice notice = matchRepository.findMatchNoticeByGuid(guid);
        notice.archiveMe();
    }

    @Override
    public MatchListDto loadMatchListDto(MatchListDto listDto) {
        MatchListDtoLoader loader = new MatchListDtoLoader(listDto);
        return loader.load();
    }

    @Override
    public MatchFormDto loadMatchFormDto(String guid) {
        MatchFormDtoLoader loader = new MatchFormDtoLoader(guid);
        return loader.load();
    }

    @Override
    public void persistMatchFormDto(MatchFormDto formDto) {
        MatchFormDtoPersister persister = new MatchFormDtoPersister(formDto);
        persister.persist();
    }

    @Override
    public void archiveMatch(String guid) {
        Match match = matchRepository.findByGuid(guid);
        match.archiveMe();
    }

    @Override
    public MatchDetailsDto loadMatchDetailsDto(String guid) {
        Match match = matchRepository.findByGuid(guid);
        return new MatchDetailsDto(match);
    }

    @Override
    public MatchMainDto loadMatchMainDto(MatchMainDto mainDto) {
        MatchMainDtoLoader loader = new MatchMainDtoLoader(mainDto);
        return loader.load();
    }

    @Override
    public MainDataDto loadMainDataDto(MainDataDto dataDto) {
        MainDataDtoLoader loader = new MainDataDtoLoader(dataDto);
        return loader.load();
    }

    @Override
    public MyMatchesDto loadMyMatchesDto(MyMatchesDto matchesDto) {
        MyMatchesDtoLoader loader = new MyMatchesDtoLoader(matchesDto);
        return loader.load();
    }

    @Override
    public MyDataDto loadMyDataDto() {
        MyDataDtoLoader loader = new MyDataDtoLoader();
        return loader.load();
    }

    @Override
    public ZsfzRecordDto loadZSFZRecordDto() {
        ZSFZRecordDtoLoader loader = new ZSFZRecordDtoLoader();
        return loader.load();
    }

    @Override
    public void loadZsfzRecordMatchGoalListDto(final ZsfzRecordMatchListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        listDto.load(new PaginatedLoader<RecordMatchDto>() {
            @Override
            public List<RecordMatchDto> loadList() {
                List<Match> matches = matchRepository.findGoalRecordMatches(map);
                return RecordMatchDto.toRecordDtos(matches, listDto.getGuid(), true);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalGoalRecordMatches(map);
            }
        });
    }

    @Override
    public void loadZsfzRecordMatchAssistListDto(final ZsfzRecordMatchListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        listDto.load(new PaginatedLoader<RecordMatchDto>() {
            @Override
            public List<RecordMatchDto> loadList() {
                List<Match> matches = matchRepository.findAssistRecordMatches(map);
                return RecordMatchDto.toRecordDtos(matches, listDto.getGuid(), false);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalAssistRecordMatches(map);
            }
        });
    }

    @Override
    public void loadZsfzRecordMatchJoinListDto(final ZsfzRecordMatchListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        listDto.load(new PaginatedLoader<RecordMatchDto>() {
            @Override
            public List<RecordMatchDto> loadList() {
                List<Match> matches = matchRepository.findJoinRecordMatches(map);
                return RecordMatchDto.toRecordDtos(matches);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalJoinRecordMatches(map);
            }
        });
    }

    @Override
    public void loadZsfzRecordMatchPeriodListDto(ZsfzRecordMatchListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        listDto.load(new PaginatedLoader<RecordMatchDto>() {
            @Override
            public List<RecordMatchDto> loadList() {
                List<Match> matches = matchRepository.findPeriodRecordMatches(map);
                return RecordMatchDto.toRecordDtos(matches);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalPeriodRecordMatches(map);
            }
        });
    }
}
