package com.andaily.zhishifenzi.domain.commons;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Shengzhao Li
 */
public class PhotoTest {


    @Test
    public void testRemove() throws Exception {
        String url = "http://sss.sdd/sdo.jpg";
        String key = url.substring(url.lastIndexOf("/") + 1, url.length());

        assertEquals(key, "sdo.jpg");
    }
}