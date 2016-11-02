package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.statistics.MainDataDto;
import com.andaily.zhishifenzi.domain.dto.statistics.SeasonStatisticsDto;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.generator.SeasonStatisticsDtoGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class MainDataDtoLoader {

    private transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);

    private MainDataDto dataDto;

    public MainDataDtoLoader(MainDataDto dataDto) {
        this.dataDto = dataDto;
    }

    public MainDataDto load() {
        List<Season> seasons = loadAvailableSeasons();
        Season currSeason = currentSeason(seasons);
        loadSeasonStatisticsDto(currSeason);
        return dataDto;
    }

    private void loadSeasonStatisticsDto(Season season) {
        if (season == null) {
            return;
        }
        SeasonStatisticsDtoGenerator seasonStatisticsDtoGenerator = new SeasonStatisticsDtoGenerator(season);
        dataDto.setCurrentDto(seasonStatisticsDtoGenerator.generate());
    }

    private Season currentSeason(List<Season> seasons) {
        String guid = dataDto.getGuid();
        if (StringUtils.isNotEmpty(guid)) {
            return seasonRepository.findByGuid(guid);
        }
        return seasons.isEmpty() ? null : seasons.get(0);
    }

    private List<Season> loadAvailableSeasons() {
        List<Season> seasons = seasonRepository.findBeforeSeasons(DateUtils.now());
        dataDto.setSeasons(SeasonDto.toDtos(seasons));
        return seasons;
    }


}