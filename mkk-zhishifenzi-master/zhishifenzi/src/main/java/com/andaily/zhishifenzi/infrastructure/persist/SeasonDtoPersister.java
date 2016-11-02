package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

/**
 * 14-3-16 上午2:27
 *
 * @author Shengzhao Li
 */
public class SeasonDtoPersister {

    private transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);
    private SeasonDto seasonDto;

    public SeasonDtoPersister(SeasonDto seasonDto) {
        this.seasonDto = seasonDto;
    }

    public String persist() {
        if (seasonDto.isNewly()) {
            return saveSeason();
        } else {
            return updateSeason();
        }
    }

    private String updateSeason() {
        Season season = seasonRepository.findByGuid(seasonDto.getGuid());
        season.name(seasonDto.getName())
                .start(DateUtils.getDate(seasonDto.getStart()))
                .end(DateUtils.getDate(seasonDto.getEnd()))
                .remark(seasonDto.getRemark())
                .saveOrUpdate();
        LogHandler.createLog("Update Season [" + season + "]", LogType.SEASON);
        return season.guid();
    }

    private String saveSeason() {
        Season season = seasonDto.toDomain();
        season.saveOrUpdate();
        LogHandler.createLog("Create Season [" + season + "]", LogType.SEASON);
        return season.guid();
    }
}
