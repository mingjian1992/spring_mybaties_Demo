package com.andaily.zhishifenzi.domain.log;

/**
 * @author Shengzhao Li
 */

public enum LogType {

    SYSTEM("系统"),
    USER_OPERATION("用户操作"),      //user operation
    PLAYER("球员"),
    STADIUM("球场"),
    CLUB("对手"),

    ALBUM("相册"),
    MATCH_NOTICE("比赛预告"),
    SEASON("赛季"),

    MATCH("比赛");


    private String label;

    private LogType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }
}