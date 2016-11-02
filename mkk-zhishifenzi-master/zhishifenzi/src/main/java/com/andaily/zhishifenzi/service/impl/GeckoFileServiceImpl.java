package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.commons.CommonsRepository;
import com.andaily.zhishifenzi.domain.commons.GeckoFile;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.album.PhotoDto;
import com.andaily.zhishifenzi.domain.dto.file.GeckoFileDto;
import com.andaily.zhishifenzi.domain.dto.file.SyncPhotosDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.service.GeckoFileService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
@Service("geckoFileService")
public class GeckoFileServiceImpl implements GeckoFileService {

    @Autowired
    private CommonsRepository commonsRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Cacheable(value = "geckoFileCache")
    public GeckoFileDto loadFileByGuid(String guid) {
        GeckoFile geckoFile = commonsRepository.findGeckoFileByGuid(guid);
        return new GeckoFileDto(geckoFile, true);
    }

    @Override
    public PhotoDto loadPlayerPhotoByGuid(String guid) {
        if (StringUtils.isEmptyOrWhitespaceOnly(guid)) {
            return null;
        }
        Player player = playerRepository.findByGuid(guid);
        Photo photo = player.headPhoto();
        return photo != null ? new PhotoDto(photo, true) : null;
    }

    @Override
    public SyncPhotosDto loadSyncPhotosDto(SyncPhotosDto syncPhotosDto) {
        final Map<String, Object> map = syncPhotosDto.queryMap();
        return syncPhotosDto.load(new PaginatedLoader<PhotoDto>() {
            @Override
            public List<PhotoDto> loadList() {
                List<Photo> photos = commonsRepository.findSyncPhotos(map);
                return PhotoDto.toDtos(photos);
            }

            @Override
            public int loadTotalSize() {
                return commonsRepository.totalSyncPhotos(map);
            }
        });
    }

    @Override
    public PhotoDto loadPhotoDtoByGuid(String guid) {
        Photo photo = (Photo) commonsRepository.findGeckoFileByGuid(guid);
        return new PhotoDto(photo, false);
    }

    @Override
    public boolean transferPhotoToCloud(String guid, boolean deleteLocalPhoto) {
        Photo photo = (Photo) commonsRepository.findGeckoFileByGuid(guid);
        return photo.transferToCloud(deleteLocalPhoto);
    }

    @Override
    public PhotoDto loadPhotoDtoIncludeDataByGuid(String guid) {
        Photo photo = (Photo) commonsRepository.findGeckoFileByGuid(guid);
        return new PhotoDto(photo, true);
    }

}