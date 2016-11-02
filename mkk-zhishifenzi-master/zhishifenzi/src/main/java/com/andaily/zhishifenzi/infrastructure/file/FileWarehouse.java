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
package com.andaily.zhishifenzi.infrastructure.file;

import java.io.File;

/**
 * @author Shengzhao Li
 */

public interface FileWarehouse {

    // return file path
    String write(byte[] data);

    byte[] read(String path);

    void delete(String path);

    //The file of path
    File file(String path);

}