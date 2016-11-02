package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.match.Match;

/**
 * @author Shengzhao Li
 */
public class MatchDetailsDto extends MatchDto {


    public MatchDetailsDto() {
        super();
    }

    public MatchDetailsDto(Match match) {
        super(match, true);
    }

    //2014-03-01 10:30 [3:1] VS 电脑城足球队
    public String getTitle() {
        StringBuilder sb = new StringBuilder();
        return sb.append(matchTime).append(" [")
                .append(homeScores).append(":").append(awayScores)
                .append("]  VS ").append(opponent.getName()).toString();
    }
}
