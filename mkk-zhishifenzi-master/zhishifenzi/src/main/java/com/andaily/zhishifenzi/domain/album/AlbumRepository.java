package com.andaily.zhishifenzi.domain.album;

import com.andaily.zhishifenzi.domain.shared.Repository;

import java.util.List;
import java.util.Map;

/**
 * 14-3-8 下午8:53
 *
 * @author Shengzhao Li
 */
public interface AlbumRepository extends Repository {

    Album findByGuid(String guid);

    void saveAlbum(Album album);

    void updateAlbum(Album album);

    List<Album> findListAlbums(Map<String, Object> map);

    int totalListAlbums(Map<String, Object> map);

    Album findByName(String name);

    List<Album> findMainAlbums(Map<String, Object> map);

    Album findAlbumByMatchGuid(String guid);
}
