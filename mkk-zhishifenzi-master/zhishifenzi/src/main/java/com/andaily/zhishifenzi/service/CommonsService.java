package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.GlobalSettingDto;
import com.andaily.zhishifenzi.domain.dto.IndexDto;
import com.andaily.zhishifenzi.domain.dto.album.FrontPhotoDto;
import com.andaily.zhishifenzi.domain.dto.log.SystemLogsDto;

/**
 * @author Shengzhao Li
 */

public interface CommonsService {

    IndexDto loadIndexDto();

    SystemLogsDto loadSystemLogsDto(SystemLogsDto systemLogsDto);

    FrontPhotoDto loadFrontPhotoDto(String guid);

    void updateFrontPhotoDto(FrontPhotoDto formDto);

    GlobalSettingDto loadGlobalSettingDto();

    void updateGlobalSetting(GlobalSettingDto settingDto);
}