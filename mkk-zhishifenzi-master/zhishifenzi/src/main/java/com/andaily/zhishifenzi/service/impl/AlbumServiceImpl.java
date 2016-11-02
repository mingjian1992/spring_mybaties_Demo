package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.album.AlbumRepository;
import com.andaily.zhishifenzi.domain.commons.CommonsRepository;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.album.*;
import com.andaily.zhishifenzi.domain.dto.match.MatchPhotosDto;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.infrastructure.persist.AlbumFormDtoPersister;
import com.andaily.zhishifenzi.infrastructure.persist.PhotoUploadDtoPersister;
import com.andaily.zhishifenzi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 14-3-22 下午3:44
 *
 * @author Shengzhao Li
 */
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private CommonsRepository commonsRepository;

    @Override
    public AlbumListDto loadAlbumListDto(AlbumListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        return listDto.load(new PaginatedLoader<AlbumDto>() {
            @Override
            public List<AlbumDto> loadList() {
                List<Album> albums = albumRepository.findListAlbums(map);
                return AlbumDto.toDtos(albums);
            }

            @Override
            public int loadTotalSize() {
                return albumRepository.totalListAlbums(map);
            }
        });
    }

    @Override
    public AlbumFormDto loadAlbumFormDto(String guid) {
        AlbumFormDto formDto = new AlbumFormDto(guid);
        if (!formDto.isNewly()) {
            Album album = albumRepository.findByGuid(guid);
            formDto = new AlbumFormDto(album);
        }
        return formDto;
    }

    @Override
    public boolean isExistAlbumByName(String name) {
        Album album = albumRepository.findByName(name);
        return album != null;
    }

    @Override
    @CacheEvict(value = "geckoFileCache", allEntries = false)
    public String persistAlbumFormDto(AlbumFormDto formDto) {
        AlbumFormDtoPersister persister = new AlbumFormDtoPersister(formDto);
        return persister.persist();
    }

    @Override
    @CacheEvict(value = "geckoFileCache", allEntries = false)
    public void archiveAlbum(String guid) {
        Album album = albumRepository.findByGuid(guid);
        album.archiveMe();
    }

    @Override
    public AlbumManageDto loadAlbumManageDto(String guid) {
        Album album = albumRepository.findByGuid(guid);
        return new AlbumManageDto(album);
    }

    @Override
    @CacheEvict(value = "geckoFileCache", allEntries = false)
    public String removePhoto(String photoGuid) {
        Photo photo = (Photo) commonsRepository.findGeckoFileByGuid(photoGuid);
        return photo.remove();
    }

    @Override
    public boolean uploadPhotos(PhotoUploadDto uploadDto) {
        PhotoUploadDtoPersister persister = new PhotoUploadDtoPersister(uploadDto);
        return persister.persistPhotos();
    }

    @Override
    public AlbumMainDto loadAlbumMainDto(AlbumMainDto mainDto) {
        final Map<String, Object> map = mainDto.queryMap();
        return mainDto.load(new PaginatedLoader<AlbumDto>() {
            @Override
            public List<AlbumDto> loadList() {
                List<Album> albums = albumRepository.findMainAlbums(map);
                return AlbumDto.toDtos(albums);
            }

            @Override
            public int loadTotalSize() {
                return 0;      //ignore
            }
        });
    }

    @Override
    public FrontPhotosDto loadFrontPhotosDto() {
        List<Photo> photos = commonsRepository.findHeadPhotos();
        return new FrontPhotosDto().setPhotos(PhotoDto.toDtos(photos));
    }

    @Override
    public MatchPhotosDto loadMatchPhotosDto(String guid) {
        Album album = albumRepository.findAlbumByMatchGuid(guid);
        return album == null ? new MatchPhotosDto() : new MatchPhotosDto(album);
    }
}
