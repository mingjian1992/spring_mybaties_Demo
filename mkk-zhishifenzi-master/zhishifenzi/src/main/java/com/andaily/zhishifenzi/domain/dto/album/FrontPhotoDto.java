package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.commons.Photo;

/**
 * @author Shengzhao Li
 */
public class FrontPhotoDto extends PhotoDto {


    private String existUrl;

    public FrontPhotoDto() {
    }

    public FrontPhotoDto(Photo photo) {
        super(photo);
        this.existUrl = photo.url();
    }

    public String getExistUrl() {
        return existUrl;
    }

    public void setExistUrl(String existUrl) {
        this.existUrl = existUrl;
    }
}