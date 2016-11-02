package com.andaily.zhishifenzi.domain.user;

import com.andaily.zhishifenzi.domain.player.Player;

/**
 * @author Shengzhao Li
 */
public class PlayerUser extends User {

    private Player player;


    public PlayerUser() {
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            userRepository.savePlayerUser(this);
        } else {
            userRepository.updateUser(this);
        }
    }

    public Player player() {
        return player;
    }

    public PlayerUser player(Player player) {
        this.player = player;
        return this;
    }
}