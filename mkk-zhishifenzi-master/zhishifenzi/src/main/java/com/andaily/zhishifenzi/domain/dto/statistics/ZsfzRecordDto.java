package com.andaily.zhishifenzi.domain.dto.statistics;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;

/**
 * 14-9-23 下午8:45
 *
 * @author Shengzhao Li
 */
public class ZsfzRecordDto extends AbstractDto {

    //最佳射手
    private int totalGoals;
    private String totalGoalsPlayerGuid;
    private String totalGoalsPlayerName;

    //助攻王
    private int totalAssists;
    private String totalAssistsPlayerGuid;
    private String totalAssistsPlayerName;

    //个人单场最多进球
    private int singleMatchGoals;
    private String singleGoalsMatchGuid;
    private String singleGoalsMatchDate;
    private String singleGoalsMatchOpponent;
    private String singleMatchGoalsPlayerName;

    //个人单场最多助攻
    private int singleMatchAssists;
    private String singleAssistsMatchGuid;
    private String singleAssistsMatchDate;
    private String singleAssistsMatchOpponent;
    private String singleAssistsPlayerName;

    //参赛最多的队员
    private int totalJoinMatches;
    private int totalMatches;
    private String totalJoinMatchesPlayerGuid;
    private String totalJoinMatchesPlayerName;

    //单场最多进球
    private int totalSingleMatchGoals;
    private String totalSingleMatchGuid;
    private String totalSingleMatchDate;
    private String totalSingleMatchOpponent;

    //最长连胜
    private int longestWinMatches;
    private String longestWinMatchesStart;
    private String longestWinMatchesEnd;

    //最长连败
    private int longestFailMatches;
    private String longestFailMatchesStart;
    private String longestFailMatchesEnd;


    public ZsfzRecordDto() {
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public ZsfzRecordDto setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
        return this;
    }

    public String getTotalGoalsPlayerGuid() {
        return totalGoalsPlayerGuid;
    }

    public ZsfzRecordDto setTotalGoalsPlayerGuid(String totalGoalsPlayerGuid) {
        this.totalGoalsPlayerGuid = totalGoalsPlayerGuid;
        return this;
    }

    public String getTotalGoalsPlayerName() {
        return totalGoalsPlayerName;
    }

    public ZsfzRecordDto setTotalGoalsPlayerName(String totalGoalsPlayerName) {
        this.totalGoalsPlayerName = totalGoalsPlayerName;
        return this;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public ZsfzRecordDto setTotalAssists(int totalAssists) {
        this.totalAssists = totalAssists;
        return this;
    }

    public String getTotalAssistsPlayerGuid() {
        return totalAssistsPlayerGuid;
    }

    public ZsfzRecordDto setTotalAssistsPlayerGuid(String totalAssistsPlayerGuid) {
        this.totalAssistsPlayerGuid = totalAssistsPlayerGuid;
        return this;
    }

    public String getTotalAssistsPlayerName() {
        return totalAssistsPlayerName;
    }

    public ZsfzRecordDto setTotalAssistsPlayerName(String totalAssistsPlayerName) {
        this.totalAssistsPlayerName = totalAssistsPlayerName;
        return this;
    }

    public int getSingleMatchGoals() {
        return singleMatchGoals;
    }

    public ZsfzRecordDto setSingleMatchGoals(int singleMatchGoals) {
        this.singleMatchGoals = singleMatchGoals;
        return this;
    }

    public String getSingleGoalsMatchGuid() {
        return singleGoalsMatchGuid;
    }

    public ZsfzRecordDto setSingleGoalsMatchGuid(String singleGoalsMatchGuid) {
        this.singleGoalsMatchGuid = singleGoalsMatchGuid;
        return this;
    }

    public String getSingleGoalsMatchDate() {
        return singleGoalsMatchDate;
    }

    public ZsfzRecordDto setSingleGoalsMatchDate(String singleGoalsMatchDate) {
        this.singleGoalsMatchDate = singleGoalsMatchDate;
        return this;
    }

    public String getSingleGoalsMatchOpponent() {
        return singleGoalsMatchOpponent;
    }

    public ZsfzRecordDto setSingleGoalsMatchOpponent(String singleGoalsMatchOpponent) {
        this.singleGoalsMatchOpponent = singleGoalsMatchOpponent;
        return this;
    }

    public String getSingleMatchGoalsPlayerName() {
        return singleMatchGoalsPlayerName;
    }

    public ZsfzRecordDto setSingleMatchGoalsPlayerName(String singleMatchGoalsPlayerName) {
        this.singleMatchGoalsPlayerName = singleMatchGoalsPlayerName;
        return this;
    }

    public int getSingleMatchAssists() {
        return singleMatchAssists;
    }

    public ZsfzRecordDto setSingleMatchAssists(int singleMatchAssists) {
        this.singleMatchAssists = singleMatchAssists;
        return this;
    }

    public String getSingleAssistsMatchGuid() {
        return singleAssistsMatchGuid;
    }

    public ZsfzRecordDto setSingleAssistsMatchGuid(String singleAssistsMatchGuid) {
        this.singleAssistsMatchGuid = singleAssistsMatchGuid;
        return this;
    }

    public String getSingleAssistsMatchDate() {
        return singleAssistsMatchDate;
    }

    public ZsfzRecordDto setSingleAssistsMatchDate(String singleAssistsMatchDate) {
        this.singleAssistsMatchDate = singleAssistsMatchDate;
        return this;
    }

    public String getSingleAssistsMatchOpponent() {
        return singleAssistsMatchOpponent;
    }

    public ZsfzRecordDto setSingleAssistsMatchOpponent(String singleAssistsMatchOpponent) {
        this.singleAssistsMatchOpponent = singleAssistsMatchOpponent;
        return this;
    }

    public String getSingleAssistsPlayerName() {
        return singleAssistsPlayerName;
    }

    public ZsfzRecordDto setSingleAssistsPlayerName(String singleAssistsPlayerName) {
        this.singleAssistsPlayerName = singleAssistsPlayerName;
        return this;
    }

    public int getTotalJoinMatches() {
        return totalJoinMatches;
    }

    public ZsfzRecordDto setTotalJoinMatches(int totalJoinMatches) {
        this.totalJoinMatches = totalJoinMatches;
        return this;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public String getTotalJoinMatchesPlayerGuid() {
        return totalJoinMatchesPlayerGuid;
    }

    public ZsfzRecordDto setTotalJoinMatchesPlayerGuid(String totalJoinMatchesPlayerGuid) {
        this.totalJoinMatchesPlayerGuid = totalJoinMatchesPlayerGuid;
        return this;
    }

    public String getTotalJoinMatchesPlayerName() {
        return totalJoinMatchesPlayerName;
    }

    public ZsfzRecordDto setTotalJoinMatchesPlayerName(String totalJoinMatchesPlayerName) {
        this.totalJoinMatchesPlayerName = totalJoinMatchesPlayerName;
        return this;
    }

    public int getTotalSingleMatchGoals() {
        return totalSingleMatchGoals;
    }

    public ZsfzRecordDto setTotalSingleMatchGoals(int totalSingleMatchGoals) {
        this.totalSingleMatchGoals = totalSingleMatchGoals;
        return this;
    }

    public String getTotalSingleMatchGuid() {
        return totalSingleMatchGuid;
    }

    public ZsfzRecordDto setTotalSingleMatchGuid(String totalSingleMatchGuid) {
        this.totalSingleMatchGuid = totalSingleMatchGuid;
        return this;
    }

    public String getTotalSingleMatchDate() {
        return totalSingleMatchDate;
    }

    public ZsfzRecordDto setTotalSingleMatchDate(String totalSingleMatchDate) {
        this.totalSingleMatchDate = totalSingleMatchDate;
        return this;
    }

    public String getTotalSingleMatchOpponent() {
        return totalSingleMatchOpponent;
    }

    public ZsfzRecordDto setTotalSingleMatchOpponent(String totalSingleMatchOpponent) {
        this.totalSingleMatchOpponent = totalSingleMatchOpponent;
        return this;
    }

    public int getLongestWinMatches() {
        return longestWinMatches;
    }

    public ZsfzRecordDto setLongestWinMatches(int longestWinMatches) {
        this.longestWinMatches = longestWinMatches;
        return this;
    }

    public String getLongestWinMatchesStart() {
        return longestWinMatchesStart;
    }

    public ZsfzRecordDto setLongestWinMatchesStart(String longestWinMatchesStart) {
        this.longestWinMatchesStart = longestWinMatchesStart;
        return this;
    }

    public String getLongestWinMatchesEnd() {
        return longestWinMatchesEnd;
    }

    public ZsfzRecordDto setLongestWinMatchesEnd(String longestWinMatchesEnd) {
        this.longestWinMatchesEnd = longestWinMatchesEnd;
        return this;
    }

    public int getLongestFailMatches() {
        return longestFailMatches;
    }

    public ZsfzRecordDto setLongestFailMatches(int longestFailMatches) {
        this.longestFailMatches = longestFailMatches;
        return this;
    }

    public String getLongestFailMatchesStart() {
        return longestFailMatchesStart;
    }

    public ZsfzRecordDto setLongestFailMatchesStart(String longestFailMatchesStart) {
        this.longestFailMatchesStart = longestFailMatchesStart;
        return this;
    }

    public String getLongestFailMatchesEnd() {
        return longestFailMatchesEnd;
    }

    public ZsfzRecordDto setLongestFailMatchesEnd(String longestFailMatchesEnd) {
        this.longestFailMatchesEnd = longestFailMatchesEnd;
        return this;
    }
}
