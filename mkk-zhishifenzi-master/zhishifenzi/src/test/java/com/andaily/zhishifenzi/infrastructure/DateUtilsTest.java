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

import java.util.Date;

import static org.testng.Assert.*;

/**
 * @author Shengzhao Li
 */
public class DateUtilsTest {

    @Test
    public void isToday() throws Exception {
        boolean today = DateUtils.isToday(DateUtils.now());
        assertTrue(today);

        today = DateUtils.isToday(DateUtils.getDate("2013-03-09"));
        assertFalse(today);
    }

    @Test
    public void isDate() throws Exception {
        boolean isDate = DateUtils.isDate(null);
        assertFalse(isDate);

        isDate = DateUtils.isDate("2013-09-11");
        assertTrue(isDate);

        isDate = DateUtils.isDate("20130911");
        assertFalse(isDate);

        final String text = DateUtils.toDateText(DateUtils.now(), DateUtils.DATE_WEEK_FORMAT);
        System.out.println(text);
    }

    @Test
    public void isDateTime() throws Exception {
        assertTrue(DateUtils.isDateTime("2013-09-09 11:23"));

    }

    /**
     * OOps!  what happened, waiting................
     */
    @Test(enabled = false)
    public void periodAsDays() {
        Date start = DateUtils.now();
        Date end = DateUtils.now();

        long days = DateUtils.periodAsDays(start, end);
        System.out.println("Period: " + days);
        assertEquals(days, 0);

        start = DateUtils.getDate("2013-03-01 12:23:23", DateUtils.DEFAULT_DATE_TIME_FORMAT);
        end = DateUtils.getDate("2013-03-11 16:00:11", DateUtils.DEFAULT_DATE_TIME_FORMAT);
        System.out.println("Start [" + DateUtils.toDateTime(start) + "], End [" + DateUtils.toDateTime(end) + "]");

        days = DateUtils.periodAsDays(start, end);
        System.out.println("Period: " + days);
        assertEquals(days, 10);
    }
}