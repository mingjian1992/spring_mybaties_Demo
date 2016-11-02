package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.album.*;
import com.andaily.zhishifenzi.domain.dto.match.MatchPhotosDto;

/**
 * 14-3-22 下午3:44
 *
 * @author Shengzhao Li
 */
public interface AlbumService {

    AlbumListDto loadAlbumListDto(AlbumListDto listDto);

    AlbumFormDto loadAlbumFormDto(String guid);

    boolean isExistAlbumByName(String name);

    String persistAlbumFormDto(AlbumFormDto formDto);

    void archiveAlbum(String guid);

    AlbumManageDto loadAlbumManageDto(String guid);

    String removePhoto(String photoGuid);

    boolean uploadPhotos(PhotoUploadDto uploadDto);

    AlbumMainDto loadAlbumMainDto(AlbumMainDto mainDto);

    FrontPhotosDto loadFrontPhotosDto();

    MatchPhotosDto loadMatchPhotosDto(String guid);
}
