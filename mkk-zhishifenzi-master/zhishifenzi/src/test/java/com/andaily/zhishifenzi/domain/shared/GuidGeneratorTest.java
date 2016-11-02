package com.andaily.zhishifenzi.domain.shared;

import org.testng.annotations.Test;

/**
 * @author Shengzhao Li
 */
public class GuidGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(GuidGenerator.generate());
        }
    }
}