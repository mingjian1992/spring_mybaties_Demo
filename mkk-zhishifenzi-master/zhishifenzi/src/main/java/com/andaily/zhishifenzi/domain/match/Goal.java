package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

/**
 * 进球
 *
 * @author Shengzhao Li
 */
public class Goal extends AbstractDomain {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);

    //进球队员
    private Player goalPlayer;
    //助攻队员
    private Player assistantPlayer;
    //对方OG ,乌龙球
    private boolean ownGoal;
    //进球时间
    private int goalTime;

    private Match match;

    public Goal() {
    }

    public Player goalPlayer() {
        return goalPlayer;
    }

    public Goal goalPlayer(Player goalPlayer) {
        this.goalPlayer = goalPlayer;
        return this;
    }

    public Player assistantPlayer() {
        return assistantPlayer;
    }

    public Goal assistantPlayer(Player assistantPlayer) {
        this.assistantPlayer = assistantPlayer;
        return this;
    }

    public boolean ownGoal() {
        return ownGoal;
    }

    public Goal ownGoal(boolean ownGoal) {
        this.ownGoal = ownGoal;
        return this;
    }

    public int goalTime() {
        return goalTime;
    }

    public Goal goalTime(int goalTime) {
        this.goalTime = goalTime;
        return this;
    }

    public Match match() {
        return match;
    }

    public Goal match(Match match) {
        this.match = match;
        return this;
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            matchRepository.saveGoal(this);
        } else {
            matchRepository.updateGoal(this);
        }
    }
}