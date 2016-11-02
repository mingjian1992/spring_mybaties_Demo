package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.commons.CommonsRepository;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.file.HeadImageUploadDto;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.infrastructure.file.ImageUtils;
import com.andaily.zhishifenzi.infrastructure.generator.AdjustmentScaler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 14-6-7 上午12:09
 *
 * @author Shengzhao Li
 */
public class CropHeadImagePersister {

    public static final int MAX_WIDTH = 600;
    public static final int MAX_HEIGHT = 300;

    private transient CommonsRepository commonsRepository = BeanProvider.getBean(CommonsRepository.class);
    private HeadImageUploadDto uploadDto;

    public CropHeadImagePersister(HeadImageUploadDto uploadDto) {
        this.uploadDto = uploadDto;
    }

    public void persist() {
        Photo photo = (Photo) commonsRepository.findGeckoFileByGuid(uploadDto.getFileGuid());
        byte[] data = photo.data();

        try {
            final BufferedImage origImg = ImageIO.read(new ByteArrayInputStream(data));
            final int origWidth = origImg.getWidth();
            final int origHeight = origImg.getHeight();

            AdjustmentScaler adjustmentScaler = new AdjustmentScaler(origWidth, origHeight, MAX_WIDTH, MAX_HEIGHT);
            float widthScale = adjustmentScaler.widthScale();
            float heightScale = adjustmentScaler.heightScale();

            int newWidth = newWidth(widthScale);
            int newHeight = newHeight(heightScale);
            int newX = newX(widthScale);
            int newY = newY(heightScale);

            boolean pngOrGif = pngOrGif(photo);
            final byte[] newBytes = ImageUtils.crop(origImg, newWidth, newHeight, newX, newY, pngOrGif);

            persistAndClean(photo, newBytes);
        } catch (IOException e) {
            throw new IllegalStateException("Save new image failed", e);
        }
    }

    private void persistAndClean(Photo photo, byte[] newBytes) {
        Photo newPhoto = new Photo(photo.name(), newBytes);
        newPhoto.saveOrUpdate();
        PlayerUser user = (PlayerUser) SecurityUtils.currentUser();
        user.player().headPhoto((Photo) commonsRepository.findGeckoFileByGuid(newPhoto.guid()))
                .saveOrUpdate();
        //clean old photo
        photo.delete();
    }

    private boolean pngOrGif(Photo photo) {
        String suffix = photo.suffix();
        return (suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("gif"));
    }

    private int newY(float scale) {
        final int y1 = uploadDto.getY1();
        return (int) (y1 * scale);
    }

    private int newX(float scale) {
        final int x1 = uploadDto.getX1();
        return (int) (x1 * scale);
    }

    private int newHeight(float scale) {
        final int height = uploadDto.getHeight();
        return (int) (height * scale);
    }


    private int newWidth(float scale) {
        final int width = uploadDto.getWidth();
        return (int) (width * scale);
    }
}
