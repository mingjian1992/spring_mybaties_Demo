package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.file.GeckoFileDto;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-22 下午3:26
 *
 * @author Shengzhao Li
 */
public class PhotoDto extends GeckoFileDto {

    //图片完整的URL,如: http://sss.sdd/sdo.jpg
    private String url;

    private String description;
    //是否为 首页显示的图片
    private boolean headPhoto;


    private String albumGuid;
    private String albumName;

    public PhotoDto() {
    }

    public PhotoDto(Photo photo) {
        this(photo, false);
    }

    public PhotoDto(Photo photo, boolean includeData) {
        super(photo);
        this.url = photo.url();
        this.description = photo.description();
        this.headPhoto = photo.headPhoto();

        final Album album = photo.album();
        if (album != null) {
            this.albumGuid = album.guid();
            this.albumName = album.name();
        }
        if (includeData && StringUtils.isEmpty(this.url)) {
            this.data = photo.data();
        }
    }

    public String getAlbumGuid() {
        return albumGuid;
    }

    public void setAlbumGuid(String albumGuid) {
        this.albumGuid = albumGuid;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(boolean headPhoto) {
        this.headPhoto = headPhoto;
    }

    public static List<PhotoDto> toDtos(List<Photo> photos) {
        List<PhotoDto> dtos = new ArrayList<>(photos.size());
        for (Photo photo : photos) {
            dtos.add(new PhotoDto(photo));
        }
        return dtos;
    }
}
