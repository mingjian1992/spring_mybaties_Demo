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
package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.commons.GlobalSetting;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import com.andaily.zhishifenzi.domain.commons.CommonsRepository;
import com.andaily.zhishifenzi.domain.commons.GeckoFile;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * @author Shengzhao Li
 */
public class CommonsRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private CommonsRepository commonsRepository;


    @Test
    public void savePhoto() {
        Photo photo = new Photo("abc.txt", "abc".getBytes())
                .headPhoto(false)
                .url("http://www.ssoso.jsp")
                .description("test");
        commonsRepository.savePhoto(photo);

        GeckoFile p2 = commonsRepository.findGeckoFileByGuid(photo.guid());
        assertNotNull(p2);
        assertTrue(p2 instanceof Photo);

        photo = (Photo) p2;
        photo.description("desc");

        commonsRepository.updatePhoto(photo);

        p2 = commonsRepository.findGeckoFileByGuid(photo.guid());
        assertNotNull(p2);
        assertEquals(((Photo) p2).description(), "desc");
    }

    @Test
    public void findGeckoFileByGuid() {
        String guid = GuidGenerator.generate();
        GeckoFile geckoFile = commonsRepository.findGeckoFileByGuid(guid);
        assertNull(geckoFile);

        GeckoFile file = new GeckoFile("abc.txt", "abc".getBytes());
        commonsRepository.saveGeckoFile(file);

        geckoFile = commonsRepository.findGeckoFileByGuid(file.guid());
        assertNotNull(geckoFile);
        assertNotNull(geckoFile.name());

    }

    @Test
    public void deleteGeckoFile() {
        GeckoFile geckoFile;

        GeckoFile file = new GeckoFile("abc.txt", "abc".getBytes());
        commonsRepository.saveGeckoFile(file);

        geckoFile = commonsRepository.findGeckoFileByGuid(file.guid());
        assertNotNull(geckoFile);
        assertNotNull(geckoFile.name());

        commonsRepository.deleteGeckoFile(geckoFile);

        geckoFile = commonsRepository.findGeckoFileByGuid(file.guid());
        assertNull(geckoFile);
    }

    @Test
    public void findHeadPhotos() {

        final List<Photo> list = commonsRepository.findHeadPhotos();
        assertTrue(list.isEmpty());
    }

    @Test
    public void updateGlobalSetting() {

        commonsRepository.updateGlobalSetting(new GlobalSetting());
    }

    @Test
    public void findGlobalSetting() {
        commonsRepository.findGlobalSetting();
    }


    @Test
    public void findSyncPhotos() {

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("displayType", true);

        final List<Photo> list = commonsRepository.findSyncPhotos(map);
        assertTrue(list.isEmpty());


        final int count = commonsRepository.totalSyncPhotos(map);
        assertTrue(count == 0);
    }


}