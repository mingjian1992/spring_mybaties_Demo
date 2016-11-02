package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * 14-3-8 下午10:28
 *
 * @author Shengzhao Li
 */
public class StadiumRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private StadiumRepository stadiumRepository;


    @Test
    public void findByGuid() {
        Stadium stadium = stadiumRepository.findByGuid(GuidGenerator.generate());
        assertNull(stadium);

        stadium = new Stadium().name("太平寺2号场地")
                .contact("老唐")
                .address("太平寺")
                .homeStadium(true);
        stadium.saveOrUpdate();

        Stadium stadium1 = stadiumRepository.findByGuid(stadium.guid());
        assertNotNull(stadium1);
        assertNotNull(stadium1.contact());

        stadium1.name("ok").saveOrUpdate();

        stadium1 = stadiumRepository.findByGuid(stadium1.guid());
        assertNotNull(stadium1);
        assertEquals(stadium1.name(), "ok");

    }

    @Test
    public void findByName() {
        Stadium stadium = new Stadium().name("太平寺2号场地")
                .contact("老唐")
                .address("太平寺")
                .homeStadium(true);
        stadium.saveOrUpdate();

        Stadium stadium1 = stadiumRepository.findByName("太平寺");
        assertNull(stadium1);

    }

    @Test
    public void findAllStadiums() {
        Stadium stadium = new Stadium().name("太平寺2号场地")
                .contact("老唐")
                .address("太平寺")
                .homeStadium(true);
        stadium.saveOrUpdate();

        List<Stadium> list = stadiumRepository.findAllStadiums();
        assertEquals(list.size(), 1);

    }

    @Test
    public void findListStadiums() {
        Stadium stadium = new Stadium().name("太平寺2号场地")
                .contact("老唐")
                .address("太平寺")
                .homeStadium(true);
        stadium.saveOrUpdate();

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("name", "%2%");

        List<Stadium> list = stadiumRepository.findListStadiums(map);
        assertEquals(list.size(), 1);

        int count = stadiumRepository.totalListStadiums(map);
        assertTrue(count == 1);
    }

}
