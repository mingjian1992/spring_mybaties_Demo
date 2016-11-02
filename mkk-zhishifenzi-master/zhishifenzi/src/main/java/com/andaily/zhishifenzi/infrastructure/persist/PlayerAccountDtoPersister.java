package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.dto.player.PlayerAccountDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.UserRepository;

/**
 * 14-4-15 下午9:35
 *
 * @author Shengzhao Li
 */
public class PlayerAccountDtoPersister {

    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);
    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private PlayerAccountDto formDto;

    public PlayerAccountDtoPersister(PlayerAccountDto formDto) {
        this.formDto = formDto;
    }

    public String persist() {
        if (formDto.isNewly()) {
            return savePlayerUser();
        } else {
            return updatePlayerUser();
        }
    }

    private String updatePlayerUser() {
        PlayerUser user = (PlayerUser) userRepository.findByGuid(formDto.getGuid());
        user.others(formDto.getOthers())
                .username(formDto.getUsername());
        user.saveOrUpdate();
        LogHandler.createLog("Update the PlayerUser [" + user + "]", LogType.USER_OPERATION);
        return user.player().guid();
    }

    private String savePlayerUser() {
        PlayerUser user = formDto.toDomain();
        user.player(playerRepository.findByGuid(formDto.getPlayer().getGuid()));
        user.saveOrUpdate();
        LogHandler.createLog("Create a new PlayerUser [" + user + "]", LogType.USER_OPERATION);
        return user.player().guid();
    }
}
