package com.andaily.zhishifenzi.domain.commons;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

/**
 * 全局设置对象, Only one.
 *
 * @author Shengzhao Li
 */
public class GlobalSetting extends AbstractDomain {

    private transient CommonsRepository commonsRepository = BeanProvider.getBean(CommonsRepository.class);

    //是否使用系统默认的首页照片, 默认true
    private boolean useDefaultFrontPhotos = true;


    //default
    public GlobalSetting() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            throw new UnsupportedOperationException("Only one GlobalSetting instance");
        } else {
            commonsRepository.updateGlobalSetting(this);
        }
    }

    public boolean useDefaultFrontPhotos() {
        return useDefaultFrontPhotos;
    }

    public GlobalSetting useDefaultFrontPhotos(boolean useDefaultFrontPhotos) {
        this.useDefaultFrontPhotos = useDefaultFrontPhotos;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{id=").append(id);
        sb.append(",useDefaultFrontPhotos=").append(useDefaultFrontPhotos);
        sb.append('}');
        return sb.toString();
    }
}