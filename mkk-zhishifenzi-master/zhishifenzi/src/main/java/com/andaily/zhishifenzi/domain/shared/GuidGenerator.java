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
package com.andaily.zhishifenzi.domain.shared;

import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.UUID;

/**
 * @author Shengzhao Li
 */
public abstract class GuidGenerator {

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "") + DateUtils.now().getTime();
    }
}