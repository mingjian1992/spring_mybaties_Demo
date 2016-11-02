package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.album.AlbumRepository;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.album.PhotoUploadDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-27 下午10:01
 *
 * @author Shengzhao Li
 */
public class PhotoUploadDtoPersister extends AlbumFormDtoPersister {


    private PhotoUploadDto uploadDto;

    public PhotoUploadDtoPersister(PhotoUploadDto uploadDto) {
        this.uploadDto = uploadDto;
    }

    public boolean persistPhotos() {
        List<MultipartFile> files = uploadDto.getFiles();
        if (files.isEmpty()) {
            return true;
        }
        Album album = albumRepository.findByGuid(uploadDto.getAlbumGuid());
        handlePhotos(album);
        LogHandler.createLog("Upload [" + files.size() + "] photos", LogType.ALBUM);
        return true;
    }

    @Override
    protected List<MultipartFile> files() {
        return uploadDto.getFiles();
    }
}
