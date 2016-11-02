package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerPosition;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * @author Shengzhao Li
 */
public class PlayerRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void findByGuid() {
        Player player = playerRepository.findByGuid(GuidGenerator.generate());
        assertNull(player);

        final Player player1 = new Player().fullName("lisi").birthday(DateUtils.now());
        playerRepository.savePlayer(player1);

        player = playerRepository.findByGuid(player1.guid());
        assertNotNull(player);
        assertNotNull(player.fullName());
        assertNotNull(player.birthday());

        player.position(PlayerPosition.AMF).saveOrUpdate();

        player = playerRepository.findByGuid(player.guid());
        assertNotNull(player);
        assertEquals(player.position(), PlayerPosition.AMF);
    }

    @Test
    public void archivePlayerUser() {

        final Player player1 = new Player().fullName("lisi").birthday(DateUtils.now());
        playerRepository.savePlayer(player1);

        playerRepository.archivePlayerUser(player1, true);
    }

    @Test
    public void findAvailablePlayers() {
        List<Player> list = playerRepository.findAvailablePlayers();
        assertEquals(list.size(), 0);
    }

    @Test
    public void findByFullName() {
        final Player player1 = new Player().fullName("lisi").birthday(DateUtils.now());
        playerRepository.savePlayer(player1);

        Player player = playerRepository.findByFullName("lisi");
        assertNotNull(player);
    }

    @Test
    public void findListPlayers() {
        final Player player1 = new Player().fullName("lisi").birthday(DateUtils.now());
        playerRepository.savePlayer(player1);

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("name", null);

        List<Player> list = playerRepository.findListPlayers(map);
        assertEquals(list.size(), 1);
    }
}