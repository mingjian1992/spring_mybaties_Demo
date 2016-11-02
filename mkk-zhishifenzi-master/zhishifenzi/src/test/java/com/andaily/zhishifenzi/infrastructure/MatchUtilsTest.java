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
package com.andaily.zhishifenzi.infrastructure;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Shengzhao Li
 */
public class MatchUtilsTest {


    @Test
    public void isEmail() {

        boolean result = MatchUtils.isEmail("addd");
        assertFalse(result);

        result = MatchUtils.isEmail("addd@honyee.cc");
        assertTrue(result);
    }

}