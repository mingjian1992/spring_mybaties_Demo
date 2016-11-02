package com.andaily.zhishifenzi.domain.dto.file;

import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.infrastructure.file.ImageUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 14-6-4 下午11:04
 *
 * @author Shengzhao Li
 */
public class HeadImageUploadDto extends AbstractDto {

    private MultipartFile file;
    private String fileGuid;

    private int width;
    private int height;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public HeadImageUploadDto() {
    }

    public boolean isEmptyFile() {
        return (file == null || file.isEmpty());
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileGuid() {
        return fileGuid;
    }

    public void setFileGuid(String fileGuid) {
        this.fileGuid = fileGuid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public boolean isImage() {
        if (isEmptyFile()) {
            return false;
        }
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return ImageUtils.IMAGE_EXTENSIONS.contains(extension);
    }

    public Photo toPhoto() {
        if (isEmptyFile()) {
            return null;
        }
        try {
            return (Photo) new Photo(file.getOriginalFilename(), file.getBytes())
                    .temp(true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
