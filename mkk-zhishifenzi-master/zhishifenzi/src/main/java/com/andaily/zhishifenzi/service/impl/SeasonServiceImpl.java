package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonListDto;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.infrastructure.loader.SeasonListDtoLoader;
import com.andaily.zhishifenzi.infrastructure.persist.SeasonDtoPersister;
import com.andaily.zhishifenzi.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 14-3-16 上午1:27
 *
 * @author Shengzhao Li
 */
@Service("seasonService")
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public SeasonListDto loadSeasonListDto(SeasonListDto listDto) {
        SeasonListDtoLoader loader = new SeasonListDtoLoader(listDto);
        return loader.load();
    }

    @Override
    public String persistSeasonDto(SeasonDto seasonDto) {
        SeasonDtoPersister persister = new SeasonDtoPersister(seasonDto);
        return persister.persist();
    }

    @Override
    public void archiveSeason(String guid) {
        Season season = seasonRepository.findByGuid(guid);
        season.archiveMe();
    }
}
