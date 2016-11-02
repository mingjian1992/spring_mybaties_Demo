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


import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.file.FileWarehouse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

/**
 * 定义一个文件信息
 *
 * @author Shengzhao Li
 */
public class GeckoFile extends AbstractDomain {

    protected transient FileWarehouse fileWarehouse = BeanProvider.getBean(FileWarehouse.class);
    protected transient CommonsRepository commonsRepository = BeanProvider.getBean(CommonsRepository.class);

    //Original file name
    protected String name;

    protected String path;
    //e.g. txt;   file suffix
    protected String suffix;

    //If temp file or not, default false
    protected boolean temp = false;
    //File original size, unit: byte
    protected long size;

    protected transient byte[] data;

    public GeckoFile() {
    }

    public GeckoFile(String name, byte[] data) {
        Assert.notNull(name, "name is null");
        this.name = name;
        this.suffix = FilenameUtils.getExtension(name);
        this.data = data;
        this.size = data.length;
    }

    public String name() {
        return name;
    }

    //Update suffix at the same time
    public GeckoFile name(String name) {
        this.name = name;
        this.suffix = FilenameUtils.getExtension(name);
        return this;
    }

    public String suffix() {
        return suffix;
    }

    public byte[] data() {
        if (data == null) {
            data = fileWarehouse.read(path);
        }
        return data;
    }

    public void saveOrUpdate() {
        this.path = fileWarehouse.write(this.data);
        if (isNewly()) {
            commonsRepository.saveGeckoFile(this);
        } else {
            commonsRepository.updateGeckoFile(this);
        }
    }

    public String path() {
        return path;
    }

    public void delete() {
        fileWarehouse.delete(this.path);
        commonsRepository.deleteGeckoFile(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{temp=").append(temp);
        sb.append(", id='").append(id).append('\'');
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public boolean temp() {
        return temp;
    }

    public GeckoFile temp(boolean temp) {
        this.temp = temp;
        return this;
    }

    public long size() {
        return size;
    }

    public GeckoFile size(long size) {
        this.size = size;
        return this;
    }

    public boolean isPhoto() {
        return false;
    }

}