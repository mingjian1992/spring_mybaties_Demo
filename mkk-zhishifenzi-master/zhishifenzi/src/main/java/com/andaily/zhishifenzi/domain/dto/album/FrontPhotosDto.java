package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class FrontPhotosDto extends AbstractDto {

    private List<PhotoDto> photos = new ArrayList<>();

    public FrontPhotosDto() {
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public FrontPhotosDto setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
        return this;
    }
}