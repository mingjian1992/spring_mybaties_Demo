package com.andaily.zhishifenzi.domain.dto.file;

import com.andaily.zhishifenzi.domain.commons.GeckoFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Shengzhao Li
 */
public class CKEditorFileDto extends GeckoFileDto {

    private MultipartFile upload;


    public CKEditorFileDto() {
    }

    public MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(MultipartFile upload) {
        this.upload = upload;
    }

    public GeckoFile toFile() {
        try {
            String originalFilename = upload.getOriginalFilename();
            return new GeckoFile(originalFilename, upload.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("Create GeckoFile failed", e);
        }
    }
}