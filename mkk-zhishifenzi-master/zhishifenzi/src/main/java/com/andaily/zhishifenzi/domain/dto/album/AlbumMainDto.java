package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class AlbumMainDto extends DefaultPaginated<AlbumDto> {


    private String guid;
    private AlbumDto currentAlbum;

    public AlbumMainDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        return super.queryMap();
    }


    @Override
    public void afterLoad() {
        if (StringUtils.isNotEmpty(guid)) {
            for (AlbumDto albumDto : list) {
                if (albumDto.getGuid().equals(guid)) {
                    currentAlbum = albumDto;
                    break;
                }
            }
        } else {
            if (!list.isEmpty()) {
                currentAlbum = list.get(0);
                guid = currentAlbum.getGuid();
            }
        }

    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public AlbumDto getCurrentAlbum() {
        return currentAlbum;
    }

    public void setCurrentAlbum(AlbumDto currentAlbum) {
        this.currentAlbum = currentAlbum;
    }
}
