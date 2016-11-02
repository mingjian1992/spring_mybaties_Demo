package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.album.AlbumRepository;
import com.andaily.zhishifenzi.domain.commons.CommonsRepository;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * 14-3-8 下午9:13
 *
 * @author Shengzhao Li
 */
public class AlbumRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private CommonsRepository commonsRepository;

    @Test
    public void findByGuid() {
        Album album = albumRepository.findByGuid(GuidGenerator.generate());
        assertNull(album);

        album = new Album().name("2014-09-09 比赛照片")
                .description("....");
        albumRepository.saveAlbum(album);

        album = albumRepository.findByGuid(album.guid());
        assertNotNull(album);
        assertNotNull(album.name());

        Photo photo = new Photo().description("test").album(album);
        commonsRepository.savePhoto(photo);

        album = albumRepository.findByGuid(album.guid());
        assertNotNull(album);
        assertEquals(album.photos().size(), 1);
    }


    @Test
    public void findByName() {
        Album album = new Album().name("2014-09-09 比赛照片")
                .description("....");
        albumRepository.saveAlbum(album);

        Album album1 = albumRepository.findByName(album.name());
        assertNotNull(album1);
    }

    @Test
    public void findAlbumByMatchGuid() {
        final Album album = albumRepository.findAlbumByMatchGuid(GuidGenerator.generate());
        assertNull(album);
    }

    @Test
    public void findMainAlbums() {
        Album album = new Album().name("2014-09-09 比赛照片")
                .description("....");
        albumRepository.saveAlbum(album);

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);

        List<Album> list = albumRepository.findMainAlbums(map);
        assertEquals(list.size(), 1);

    }

    @Test
    public void findListAlbums() {
        Album album = new Album().name("2014-09-09 比赛照片")
                .description("....");
        albumRepository.saveAlbum(album);

        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("name", null);

        List<Album> list = albumRepository.findListAlbums(map);
        assertEquals(list.size(), 1);

    }


}
