package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.match.*;
import com.andaily.zhishifenzi.domain.dto.statistics.MainDataDto;
import com.andaily.zhishifenzi.domain.dto.statistics.ZsfzRecordDto;
import com.andaily.zhishifenzi.domain.dto.statistics.ZsfzRecordMatchListDto;

/**
 * 14-3-16 上午1:21
 *
 * @author Shengzhao Li
 */
public interface MatchService {

    MatchNoticeListDto loadMatchNoticeListDto(MatchNoticeListDto listDto);

    MatchNoticeFormDto loadMatchNoticeFormDto(String guid);

    String persistMatchNoticeFormDto(MatchNoticeFormDto formDto);

    void publishMatchNotice(String guid);

    void cancelPublishMatchNotice(String guid);

    void archiveMatchNotice(String guid);

    MatchListDto loadMatchListDto(MatchListDto listDto);

    MatchFormDto loadMatchFormDto(String guid);

    void persistMatchFormDto(MatchFormDto formDto);

    void archiveMatch(String guid);

    MatchDetailsDto loadMatchDetailsDto(String guid);

    MatchMainDto loadMatchMainDto(MatchMainDto mainDto);

    MainDataDto loadMainDataDto(MainDataDto dataDto);

    MyMatchesDto loadMyMatchesDto(MyMatchesDto matchesDto);

    MyDataDto loadMyDataDto();

    ZsfzRecordDto loadZSFZRecordDto();

    void loadZsfzRecordMatchGoalListDto(ZsfzRecordMatchListDto listDto);

    void loadZsfzRecordMatchAssistListDto(ZsfzRecordMatchListDto listDto);

    void loadZsfzRecordMatchJoinListDto(ZsfzRecordMatchListDto listDto);

    void loadZsfzRecordMatchPeriodListDto(ZsfzRecordMatchListDto listDto);
}
