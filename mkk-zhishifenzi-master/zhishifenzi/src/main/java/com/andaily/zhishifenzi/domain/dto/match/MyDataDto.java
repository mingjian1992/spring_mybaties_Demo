package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.math.BigDecimal;

/**
 * 14-4-21 下午9:32
 *
 * @author Shengzhao Li
 */
public class MyDataDto extends AbstractDto {

    private int myScores;
    private int totalScores;

    private int myAssists;
    private int totalAssists;

    private int myMatches;
    private int totalMatches;

    private int win;
    private int equals;
    private int fail;

    public MyDataDto() {
    }

    public String getToday() {
        return DateUtils.toDateText(DateUtils.now());
    }

    public int getRestScores() {
        return totalScores - myScores;
    }

    public int getRestAssists() {
        return totalAssists - myAssists;
    }

    public int getRestMatches() {
        return totalMatches - myMatches;
    }

    public int getScoresPercent() {
        return retrievePercent(myScores, totalScores);
    }

    public int getAssistsPercent() {
        return retrievePercent(myAssists, totalAssists);
    }

    public int getMatchesPercent() {
        return retrievePercent(myMatches, totalMatches);
    }

    public int getWinPercent() {
        return retrievePercent(win, myMatches);
    }


    private int retrievePercent(int a, int b) {
        if (b == 0) {
            return 0;
        }
        BigDecimal result = new BigDecimal(a * 100).divide(new BigDecimal(b), 2, BigDecimal.ROUND_HALF_EVEN);
        return result.intValue();
    }

    public int getWin() {
        return win;
    }

    public MyDataDto setWin(int win) {
        this.win = win;
        return this;
    }

    public int getMyScores() {
        return myScores;
    }

    public void setMyScores(int myScores) {
        this.myScores = myScores;
    }

    public int getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(int totalScores) {
        this.totalScores = totalScores;
    }

    public int getMyAssists() {
        return myAssists;
    }

    public void setMyAssists(int myAssists) {
        this.myAssists = myAssists;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public void setTotalAssists(int totalAssists) {
        this.totalAssists = totalAssists;
    }

    public int getMyMatches() {
        return myMatches;
    }

    public void setMyMatches(int myMatches) {
        this.myMatches = myMatches;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }


    public int getEquals() {
        return equals;
    }

    public MyDataDto setEquals(int equals) {
        this.equals = equals;
        return this;
    }

    public int getFail() {
        return fail;
    }

    public MyDataDto setFail(int fail) {
        this.fail = fail;
        return this;
    }
}
