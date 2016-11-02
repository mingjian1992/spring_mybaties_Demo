package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.shared.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 14-3-9 上午10:47
 *
 * @author Shengzhao Li
 */
public interface SeasonRepository extends Repository {

    Season findByGuid(String guid);

    void saveSeason(Season season);

    void updateSeason(Season season);

    List<Season> findListSeasons(Map<String, Object> map);

    int totalListSeasons(Map<String, Object> map);

    void archiveSeasonMatches(Season season);

    List<Season> findAllSeasons();

    List<Season> findBeforeSeasons(Date date);
}
