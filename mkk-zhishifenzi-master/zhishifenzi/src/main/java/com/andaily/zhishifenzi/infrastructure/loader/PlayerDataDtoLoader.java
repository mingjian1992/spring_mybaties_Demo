package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.match.MyDataDto;
import com.andaily.zhishifenzi.domain.dto.match.PlayerDataDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

/**
 * 14-5-8 下午11:26
 *
 * @author Shengzhao Li
 */
public class PlayerDataDtoLoader extends MyDataDtoLoader {

    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);
    private String guid;

    public PlayerDataDtoLoader(String guid) {
        this.guid = guid;
    }

    @Override
    protected void initialPlayer() {
        this.player = playerRepository.findByGuid(guid);
    }

    @Override
    protected MyDataDto createDataDto() {
        return new PlayerDataDto();
    }

    public PlayerDataDto load() {
        PlayerDataDto dataDto = (PlayerDataDto) super.load();
        return dataDto.setPlayerDto(new PlayerDto(player));
    }
}
