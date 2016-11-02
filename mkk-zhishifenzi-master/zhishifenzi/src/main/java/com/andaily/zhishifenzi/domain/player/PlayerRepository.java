package com.andaily.zhishifenzi.domain.player;

import com.andaily.zhishifenzi.domain.shared.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */

public interface PlayerRepository extends Repository {

    Player findByGuid(String guid);

    void savePlayer(Player player);

    void updatePlayer(Player player);

    List<Player> findListPlayers(Map<String, Object> map);

    Player findByFullName(String fullName);

    void archivePlayerUser(@Param("player") Player player, @Param("archived") boolean archived);

    List<Player> findAvailablePlayers();
}