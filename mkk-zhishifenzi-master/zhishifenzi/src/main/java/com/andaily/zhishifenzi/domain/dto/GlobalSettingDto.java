package com.andaily.zhishifenzi.domain.dto;

import com.andaily.zhishifenzi.domain.commons.GlobalSetting;

/**
 * @author Shengzhao Li
 */
public class GlobalSettingDto extends AbstractDto {


    //是否使用系统默认的首页照片, 默认true
    private boolean useDefaultFrontPhotos = true;

    public GlobalSettingDto() {
    }

    public GlobalSettingDto(GlobalSetting setting) {
        super(setting.guid());
        this.useDefaultFrontPhotos = setting.useDefaultFrontPhotos();
    }


    public boolean isUseDefaultFrontPhotos() {
        return useDefaultFrontPhotos;
    }

    public void setUseDefaultFrontPhotos(boolean useDefaultFrontPhotos) {
        this.useDefaultFrontPhotos = useDefaultFrontPhotos;
    }


}