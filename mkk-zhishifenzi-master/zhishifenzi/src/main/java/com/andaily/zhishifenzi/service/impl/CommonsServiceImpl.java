package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.commons.CommonsRepository;
import com.andaily.zhishifenzi.domain.commons.GlobalSetting;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.GlobalSettingDto;
import com.andaily.zhishifenzi.domain.dto.IndexDto;
import com.andaily.zhishifenzi.domain.dto.album.FrontPhotoDto;
import com.andaily.zhishifenzi.domain.dto.log.SystemLogsDto;
import com.andaily.zhishifenzi.infrastructure.loader.IndexDtoLoader;
import com.andaily.zhishifenzi.infrastructure.loader.SystemLogsDtoLoader;
import com.andaily.zhishifenzi.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shengzhao Li
 */
@Service("commonsService")
public class CommonsServiceImpl implements CommonsService {


    @Autowired
    private CommonsRepository commonsRepository;

    @Override
    public IndexDto loadIndexDto() {
        IndexDtoLoader indexDtoLoader = new IndexDtoLoader();
        return indexDtoLoader.load();
    }

    @Override
    public SystemLogsDto loadSystemLogsDto(SystemLogsDto systemLogsDto) {
        SystemLogsDtoLoader loader = new SystemLogsDtoLoader(systemLogsDto);
        return loader.load();
    }

    @Override
    public FrontPhotoDto loadFrontPhotoDto(String guid) {
        Photo photo = (Photo) commonsRepository.findGeckoFileByGuid(guid);
        if (!photo.headPhoto()) {
            throw new IllegalStateException("The photo is not head photo from guid [" + guid + "]");
        }
        return new FrontPhotoDto(photo);
    }

    @Override
    public void updateFrontPhotoDto(FrontPhotoDto formDto) {
        Photo photo = (Photo) commonsRepository.findGeckoFileByGuid(formDto.getGuid());
        photo.url(formDto.getUrl())
                .description(formDto.getDescription())
                .saveOrUpdate();
    }

    @Override
    @Cacheable(value = "globalSettingCache")
    public GlobalSettingDto loadGlobalSettingDto() {
        GlobalSetting setting = commonsRepository.findGlobalSetting();
        return new GlobalSettingDto(setting);
    }

    @Override
    @CacheEvict(value = "globalSettingCache", allEntries = true)
    public void updateGlobalSetting(GlobalSettingDto settingDto) {
        GlobalSetting setting = commonsRepository.findGlobalSetting();
        setting.useDefaultFrontPhotos(settingDto.isUseDefaultFrontPhotos())
                .saveOrUpdate();
    }
}
