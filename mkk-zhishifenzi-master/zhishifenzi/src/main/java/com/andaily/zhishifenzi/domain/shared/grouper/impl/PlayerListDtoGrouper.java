package com.andaily.zhishifenzi.domain.shared.grouper.impl;

import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.domain.player.PlayerPosition;
import com.andaily.zhishifenzi.domain.shared.grouper.AbstractGrouper;

import java.util.List;

/**
 * 14-3-19 下午9:21
 *
 * @author Shengzhao Li
 */
public class PlayerListDtoGrouper extends AbstractGrouper<String, PlayerDto> {

    public PlayerListDtoGrouper(List<PlayerDto> elements) {
        super(elements);
    }

    @Override
    public String groupKey(PlayerDto element) {
        PlayerPosition position = element.getPosition();
        switch (position) {
            case GK:
                return position.getLabel();
            case LB:
            case RB:
            case CB:
                return "后场";
            case LW:
            case RW:
            case CF:
                return "前场";
            default:
                return "中场";

        }
    }
}
