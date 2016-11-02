package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-22 下午5:06
 *
 * @author Shengzhao Li
 */
public class AlbumFormDto extends AlbumDto {

    private String existName;
    private List<MultipartFile> files = new ArrayList<>();

    public AlbumFormDto() {
        super();
    }

    public AlbumFormDto(Album album) {
        this.guid = album.guid();
        this.name = this.existName = album.name();
        this.description = album.description();
    }

    public AlbumFormDto(String guid) {
        this.guid = guid;
    }

    public boolean isNewly() {
        return super.isNewly() || MatchUtils.isCreate(guid);
    }

    public String getExistName() {
        return existName;
    }

    public void setExistName(String existName) {
        this.existName = existName;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public Album updateDomain(Album album) {
        album.name(name).description(description);
        return album;
    }
}
