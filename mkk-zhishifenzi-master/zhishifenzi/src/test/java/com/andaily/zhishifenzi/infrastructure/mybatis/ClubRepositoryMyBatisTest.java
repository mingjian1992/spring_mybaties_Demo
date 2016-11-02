package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;


/**
 * 14-3-8 下午9:52
 *
 * @author Shengzhao Li
 */
public class ClubRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;

    @Test
    public void findByGuid() {
        Club club = clubRepository.findByGuid(GuidGenerator.generate());
        assertNull(club);

        club = new Club().name("清江足球队").contact("123 dosofdldl").remark("朋友 介绍的球队 ");
        club.saveOrUpdate();

        club = clubRepository.findByGuid(club.guid());
        assertNotNull(club);
        assertNotNull(club.name());

        club.name("abc");
        club.saveOrUpdate();

        club = clubRepository.findByGuid(club.guid());
        assertNotNull(club);
        assertEquals(club.name(), "abc");

    }

    @Test
    public void findAllClubs() {
        List<Club> list = clubRepository.findAllClubs();
        assertEquals(list.size(), 0);

    }

    @Test
    public void findByName() {
        Club club = new Club().name("清江足球队").contact("123 dosofdldl").remark("朋友 介绍的球队 ");
        club.saveOrUpdate();

        club = clubRepository.findByName("清江足球队");
        assertNotNull(club);
        assertNotNull(club.name());

    }

    @Test
    public void findListClubs() {
        Club club = new Club().name("清江足球队").contact("123 dosofdldl").remark("朋友 介绍的球队 ");
        club.saveOrUpdate();

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);

        List<Club> list = clubRepository.findListClubs(map);
        assertEquals(list.size(), 1);

        int i = clubRepository.totalListClubs(map);
        assertTrue(i == 1);

    }
}
