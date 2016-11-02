package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.album.Album;

/**
 * 14-3-23 下午9:15
 *
 * @author Shengzhao Li
 */
public class AlbumManageDto extends AlbumDto {

    public AlbumManageDto() {
    }

    public AlbumManageDto(Album album) {
        super(album);
    }

    public boolean isOnlyOnePhoto() {
        return this.photos.size() == 1;
    }
}
