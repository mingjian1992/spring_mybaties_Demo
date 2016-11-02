package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-25 下午11:34
 *
 * @author Shengzhao Li
 */
public class PhotoUploadDto extends AbstractDto {

    private String albumGuid;
    private List<MultipartFile> files = new ArrayList<>();

    public PhotoUploadDto() {
    }

    public String getAlbumGuid() {
        return albumGuid;
    }

    public void setAlbumGuid(String albumGuid) {
        this.albumGuid = albumGuid;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
