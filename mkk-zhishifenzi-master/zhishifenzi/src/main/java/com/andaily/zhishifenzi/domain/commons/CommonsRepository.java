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
package com.andaily.zhishifenzi.domain.commons;

import com.andaily.zhishifenzi.domain.shared.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */

public interface CommonsRepository extends Repository {


    void saveGeckoFile(GeckoFile geckoFile);

    void updateGeckoFile(GeckoFile geckoFile);

    GeckoFile findGeckoFileByGuid(String guid);

    void deleteGeckoFile(GeckoFile geckoFile);

    void savePhoto(Photo photo);

    void updatePhoto(Photo photo);

    //Find head photos:  headPhoto = true
    List<Photo> findHeadPhotos();

    void updateGlobalSetting(GlobalSetting setting);

    //Only one  GlobalSetting
    GlobalSetting findGlobalSetting();

    List<Photo> findSyncPhotos(Map<String, Object> map);

    int totalSyncPhotos(Map<String, Object> map);
}