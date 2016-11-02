package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * 14-3-9 上午11:01
 *
 * @author Shengzhao Li
 */
public class SeasonRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private SeasonRepository seasonRepository;


    @Test
    public void findListSeasons() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);

        List<Season> list = seasonRepository.findListSeasons(map);
        assertEquals(list.size(), 0);

        int i = seasonRepository.totalListSeasons(map);
        assertTrue(i == 0);
    }

    @Test
    public void archiveSeasonMatches() {
        Season season = new Season().name("2013 赛季").start(DateUtils.now())
                .end(DateUtils.now()).remark("Cheer up");
        season.saveOrUpdate();

        seasonRepository.archiveSeasonMatches(season);
    }

    @Test
    public void findAllSeasons() {
        List<Season> allSeasons = seasonRepository.findAllSeasons();
        assertEquals(allSeasons.size(), 0);
    }

    @Test
    public void findBeforeSeasons() {
        Date date = DateUtils.getDate("2014-04-07");
        Season season = new Season().name("2014 赛季").start(DateUtils.getDate("2014-01-01"))
                .end(DateUtils.getDate("2014-12-31")).remark("Cheer up");
        season.saveOrUpdate();

        new Season().name("2015")
                .start(DateUtils.getDate("2015-01-01"))
                .end(DateUtils.getDate("2015-12-31"))
                .remark("test")
                .saveOrUpdate();

        List<Season> list = seasonRepository.findBeforeSeasons(date);
        assertEquals(list.size(), 1);
    }

    @Test
    public void findByGuid() {
        Season season = seasonRepository.findByGuid(GuidGenerator.generate());
        assertNull(season);

        season = new Season().name("2013 赛季").start(DateUtils.now())
                .end(DateUtils.now()).remark("Cheer up");
        season.saveOrUpdate();

        season = seasonRepository.findByGuid(season.guid());
        assertNotNull(season);
        assertNotNull(season.start());

        season.remark("test").saveOrUpdate();

        season = seasonRepository.findByGuid(season.guid());
        assertNotNull(season);
        assertEquals(season.remark(), "test");
    }
}
