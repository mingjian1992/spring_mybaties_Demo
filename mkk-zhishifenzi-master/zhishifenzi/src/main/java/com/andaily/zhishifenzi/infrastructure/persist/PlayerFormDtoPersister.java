package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

/**
 * 14-3-19 下午10:27
 *
 * @author Shengzhao Li
 */
public class PlayerFormDtoPersister {

    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);
    private PlayerFormDto formDto;

    public PlayerFormDtoPersister(PlayerFormDto formDto) {
        this.formDto = formDto;
    }

    public void persist() {
        if (formDto.isNewly()) {
            savePlayer();
        } else {
            updatePlayer();
        }
    }

    private void updatePlayer() {
        Player player = playerRepository.findByGuid(formDto.getGuid());
        player = formDto.updateDomain(player);
        player.saveOrUpdate();
        LogHandler.createLog("Update Player [" + player + "]", LogType.PLAYER);
    }

    private void savePlayer() {
        Player player = formDto.updateDomain(new Player());
        player.saveOrUpdate();
        LogHandler.createLog("Create Player [" + player + "]", LogType.PLAYER);
    }
}
