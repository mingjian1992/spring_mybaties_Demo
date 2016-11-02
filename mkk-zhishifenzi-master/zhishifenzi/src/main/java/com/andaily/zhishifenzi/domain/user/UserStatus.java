package com.andaily.zhishifenzi.domain.user;

/**
 * @author Shengzhao Li
 */

public enum UserStatus {

    ENABLED("启用"),
    DISABLED("禁用");

    private String label;

    private UserStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }

    public boolean isEnabled() {
        return ENABLED.equals(this);
    }

    public boolean isDisabled() {
        return DISABLED.equals(this);
    }
}