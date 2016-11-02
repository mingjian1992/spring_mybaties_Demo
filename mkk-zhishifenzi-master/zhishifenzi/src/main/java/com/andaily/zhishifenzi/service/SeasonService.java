package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonListDto;

/**
 * 14-3-16 上午1:27
 *
 * @author Shengzhao Li
 */
public interface SeasonService {

    SeasonListDto loadSeasonListDto(SeasonListDto listDto);

    String persistSeasonDto(SeasonDto seasonDto);

    void archiveSeason(String guid);
}
