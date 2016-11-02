package com.andaily.zhishifenzi.domain.dto.player;

import com.andaily.zhishifenzi.domain.dto.user.UserDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.domain.user.UserRole;
import com.andaily.zhishifenzi.infrastructure.PasswordHandler;
import com.andaily.zhishifenzi.infrastructure.PinyinUtils;

/**
 * 14-4-15 下午9:11
 *
 * @author Shengzhao Li
 */
public class PlayerAccountDto extends UserDto {

    private PlayerDto player;

    private String password;
    private String rePassword;
    private String existUsername;

    public PlayerAccountDto() {
    }

    public PlayerAccountDto(PlayerUser playerUser) {
        super(playerUser);
        this.existUsername = playerUser.username();
        this.player = new PlayerDto(playerUser.player());
    }

    public PlayerAccountDto(Player player) {
        this.player = new PlayerDto(player);
        this.username = PinyinUtils.chineseToPinyin(player.fullName());
        initialUserRole(player);
    }

    private void initialUserRole(Player player) {
        if (player.captain()) {
            this.userRole = UserRole.CAPTAIN;
        } else {
            this.userRole = UserRole.PLAYER;
        }
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public PlayerAccountDto setPlayer(PlayerDto player) {
        this.player = player;
        return this;
    }

    public PlayerUser toDomain() {
        return (PlayerUser) new PlayerUser()
                .username(username)
                .userRole(userRole)
                .password(PasswordHandler.encryptPassword(password))
                .others(others);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExistUsername() {
        return existUsername;
    }

    public void setExistUsername(String existUsername) {
        this.existUsername = existUsername;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
