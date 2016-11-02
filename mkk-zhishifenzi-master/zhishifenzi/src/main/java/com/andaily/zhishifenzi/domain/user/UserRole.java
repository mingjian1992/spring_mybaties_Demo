/*
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
 */
package com.andaily.zhishifenzi.domain.user;

/**
 * @author Shengzhao Li
 */

public enum UserRole {


    ADMIN("管理员"),
    CAPTAIN("队长"),
    COACH("教练"),
    PLAYER("队员");

    private String label;

    private UserRole(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }

    public boolean isPlayer() {
        return PLAYER.equals(this);
    }

    public boolean isCaptain() {
        return CAPTAIN.equals(this);
    }

}