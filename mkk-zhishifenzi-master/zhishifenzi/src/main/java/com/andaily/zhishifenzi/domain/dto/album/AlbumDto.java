package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.match.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-22 下午3:25
 *
 * @author Shengzhao Li
 */
public class AlbumDto extends AbstractDto {


    //相册名称
    protected String name;
    //描述
    protected String description;
    //照片
    protected List<PhotoDto> photos = new ArrayList<>();

    protected String firstPhotoGuid;
    //refer match
    protected String matchGuid;

    public AlbumDto() {
    }

    public AlbumDto(Album album) {
        super(album.guid());
        this.name = album.name();
        this.description = album.description();

        List<Photo> photoList = album.photos();
        this.photos = PhotoDto.toDtos(photoList);
        //Be sure have one photo
        this.firstPhotoGuid = photoList.get(0).guid();

        final Match match = album.match();
        if (match != null) {
            this.matchGuid = match.guid();
        }
    }

    public String getMatchGuid() {
        return matchGuid;
    }

    public void setMatchGuid(String matchGuid) {
        this.matchGuid = matchGuid;
    }

    public String getFirstPhotoGuid() {
        return firstPhotoGuid;
    }

    public int getPhotoSize() {
        return photos.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
    }

    public static List<AlbumDto> toDtos(List<Album> albums) {
        List<AlbumDto> dtos = new ArrayList<>(albums.size());
        for (Album album : albums) {
            dtos.add(new AlbumDto(album));
        }
        return dtos;
    }
}
