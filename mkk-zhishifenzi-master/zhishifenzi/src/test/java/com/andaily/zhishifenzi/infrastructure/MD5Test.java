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

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.testng.annotations.Test;

/**
 * @author Shengzhao Li
 */
public class MD5Test {


    @Test
    public void encode() {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encode = encoder.encodePassword("honyee2013", null);
        System.out.println(encode);
    }

}