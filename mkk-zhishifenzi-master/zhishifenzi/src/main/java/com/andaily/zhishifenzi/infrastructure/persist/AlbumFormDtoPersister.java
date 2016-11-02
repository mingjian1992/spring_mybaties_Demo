package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.domain.album.AlbumRepository;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.album.AlbumFormDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.file.ImageSizeAdjuster;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 14-3-22 下午5:40
 *
 * @author Shengzhao Li
 */
public class AlbumFormDtoPersister {

    private static Logger logger = LoggerFactory.getLogger(AlbumFormDtoPersister.class);
    private static final int IMAGE_MAX_WIDTH = 1024;

    protected transient AlbumRepository albumRepository = BeanProvider.getBean(AlbumRepository.class);
    private AlbumFormDto formDto;

    public AlbumFormDtoPersister() {
    }

    public AlbumFormDtoPersister(AlbumFormDto formDto) {
        this.formDto = formDto;
    }

    public String persist() {
        if (formDto.isNewly()) {
            return saveAlbum();
        } else {
            return updateAlbum();
        }
    }

    //更新时不需要处理图片
    private String updateAlbum() {
        Album album = albumRepository.findByGuid(formDto.getGuid());
        formDto.updateDomain(album);
        album.saveOrUpdate();

        LogHandler.createLog("Update album [" + album + "]", LogType.ALBUM);
        return album.guid();
    }

    private String saveAlbum() {
        Album album = formDto.updateDomain(new Album())
                .creator(SecurityUtils.currentUser());
        album.saveOrUpdate();
        //处理图片
        handlePhotos(albumRepository.findByGuid(album.guid()));
        LogHandler.createLog("Create album [" + album + "]", LogType.ALBUM);
        return album.guid();
    }

    protected void handlePhotos(Album album) {
        ImageSizeAdjuster imageSizeAdjuster = new ImageSizeAdjuster();

        List<MultipartFile> fileList = files();
        for (MultipartFile multipartFile : fileList) {
            byte[] data = retrieveData(multipartFile, imageSizeAdjuster);
            if (data != null && data.length > 0) {
                Photo photo = new Photo(multipartFile.getOriginalFilename(), data)
                        .album(album);
                photo.saveOrUpdate();
            }
        }

    }

    protected List<MultipartFile> files() {
        return formDto.getFiles();
    }

    private byte[] retrieveData(MultipartFile multipartFile, ImageSizeAdjuster imageSizeAdjuster) {
        byte[] bytes = null;
        try {
            bytes = multipartFile.getBytes();
//            if (bytes != null && bytes.length > 0) {
//                String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//                bytes = imageSizeAdjuster.imageData(bytes)
//                        .adjust(IMAGE_MAX_WIDTH, extension);
//            }
        } catch (IOException e) {
            logger.error("Retrieve Photo data failed", e);
        }
        return bytes;
    }
}
