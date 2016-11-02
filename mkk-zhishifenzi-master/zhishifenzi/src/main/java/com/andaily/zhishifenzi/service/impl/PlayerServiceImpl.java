package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.dto.match.PlayerDataDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerAccountDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerListDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.UserRepository;
import com.andaily.zhishifenzi.domain.user.UserStatus;
import com.andaily.zhishifenzi.infrastructure.loader.PlayerDataDtoLoader;
import com.andaily.zhishifenzi.infrastructure.persist.PlayerAccountDtoPersister;
import com.andaily.zhishifenzi.infrastructure.persist.PlayerFormDtoPersister;
import com.andaily.zhishifenzi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 14-3-19 下午9:31
 *
 * @author Shengzhao Li
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PlayerListDto loadPlayerListDto(PlayerListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        return listDto.load(new PaginatedLoader<PlayerDto>() {
            @Override
            public List<PlayerDto> loadList() {
                List<Player> players = playerRepository.findListPlayers(map);
                return PlayerDto.toDtos(players);
            }

            @Override
            public int loadTotalSize() {
                return 0;
            }
        });
    }

    @Override
    public PlayerFormDto loadPlayerFormDto(String guid) {
        PlayerFormDto formDto = new PlayerFormDto(guid);
        if (!formDto.isNewly()) {
            Player player = playerRepository.findByGuid(guid);
            formDto = new PlayerFormDto(player);
        }
        return formDto;
    }

    @Override
    public Player loadPlayerByFullName(String fullName) {
        return playerRepository.findByFullName(fullName);
    }

    @Override
    public void persistPlayerFormDto(PlayerFormDto formDto) {
        PlayerFormDtoPersister persister = new PlayerFormDtoPersister(formDto);
        persister.persist();
    }

    @Override
    public void archivePlayer(String guid) {
        Player player = playerRepository.findByGuid(guid);
        player.archiveMe();
    }

    @Override
    public PlayerAccountDto loadPlayerAccountDto(String playerGuid) {
        PlayerAccountDto accountDto;
        PlayerUser playerUser = userRepository.findPlayerUser(playerGuid);
        if (playerUser != null) {
            accountDto = new PlayerAccountDto(playerUser);
        } else {
            Player player = playerRepository.findByGuid(playerGuid);
            accountDto = new PlayerAccountDto(player);
        }
        return accountDto;
    }

    @Override
    public String persistPlayerAccountDto(PlayerAccountDto formDto) {
        PlayerAccountDtoPersister persister = new PlayerAccountDtoPersister(formDto);
        return persister.persist();
    }

    @Override
    public String changePlayerUserStatus(String playerUserGuid, UserStatus status) {
        PlayerUser playerUser = (PlayerUser) userRepository.findByGuid(playerUserGuid);
        playerUser.changeStatus(status);
        return playerUser.player().guid();
    }

    @Override
    public String resetPlayerUserPassword(String guid) {
        PlayerUser playerUser = (PlayerUser) userRepository.findByGuid(guid);
        return playerUser.resetPassword();
    }

    @Override
    public PlayerDataDto loadPlayerDataDto(String guid) {
        PlayerDataDtoLoader loader = new PlayerDataDtoLoader(guid);
        return loader.load();
    }

    @Override
    public PlayerDto loadPlayerDto(String guid) {
        Player player = playerRepository.findByGuid(guid);
        return new PlayerDto(player);
    }
}
