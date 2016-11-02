package com.andaily.zhishifenzi.domain.player;

public enum PlayerPosition {

    GK("守门员"),
    CB("中后卫"),
    RB("右后卫"),
    LB("左后卫"),
    DMF("后腰"),
    AMF("前腰"),
    CF("前锋"),
    LW("左边锋"),
    RW("右边锋"),
    SW("自由人");

    private String label;

    private PlayerPosition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }
}
