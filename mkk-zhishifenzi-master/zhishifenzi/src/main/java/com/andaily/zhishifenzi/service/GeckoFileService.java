package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.album.PhotoDto;
import com.andaily.zhishifenzi.domain.dto.file.GeckoFileDto;
import com.andaily.zhishifenzi.domain.dto.file.SyncPhotosDto;

/**
 * @author Shengzhao Li
 */

public interface GeckoFileService {

    GeckoFileDto loadFileByGuid(String guid);

    PhotoDto loadPlayerPhotoByGuid(String guid);

    SyncPhotosDto loadSyncPhotosDto(SyncPhotosDto syncPhotosDto);

    PhotoDto loadPhotoDtoByGuid(String guid);

    boolean transferPhotoToCloud(String guid, boolean deleteLocalPhoto);

    PhotoDto loadPhotoDtoIncludeDataByGuid(String guid);
}