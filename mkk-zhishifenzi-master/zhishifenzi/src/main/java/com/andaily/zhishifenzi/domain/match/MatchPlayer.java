package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.player.Player;

/**
 * @author Shengzhao Li
 */
public class MatchPlayer extends AbstractDomain {

    private Match match;

    private Player player;

    public MatchPlayer() {
    }

    public MatchPlayer(Match match, Player player) {
        this.match = match;
        this.player = player;
    }

    public Match match() {
        return match;
    }

    public Player player() {
        return player;
    }
}