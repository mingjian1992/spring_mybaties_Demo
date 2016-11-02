package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.dto.album.AlbumDto;

/**
 * @author Shengzhao Li
 */
public class MatchPhotosDto extends AlbumDto {


    public MatchPhotosDto() {
    }


    public MatchPhotosDto(Album album) {
        super(album);
    }
}