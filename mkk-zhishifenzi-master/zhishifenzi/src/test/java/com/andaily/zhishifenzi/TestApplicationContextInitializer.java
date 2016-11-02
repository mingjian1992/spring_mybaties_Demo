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
package com.andaily.zhishifenzi;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Shengzhao Li
 */
public class TestApplicationContextInitializer implements ApplicationContextInitializer<AbstractApplicationContext> {

    @Override
    public void initialize(AbstractApplicationContext applicationContext) {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        //load database.properties
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("test.properties"));

        applicationContext.addBeanFactoryPostProcessor(propertyPlaceholderConfigurer);
    }
}