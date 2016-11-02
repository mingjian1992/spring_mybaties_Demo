package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.album.AlbumFormDto;
import com.andaily.zhishifenzi.infrastructure.file.ImageUtils;
import com.andaily.zhishifenzi.service.AlbumService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 14-3-16 下午10:22
 *
 * @author Shengzhao Li
 */
@Component
public class AlbumFormDtoValidator implements Validator {

    @Autowired
    private AlbumService albumService;


    @Override
    public boolean supports(Class<?> aClass) {
        return AlbumFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AlbumFormDto formDto = (AlbumFormDto) o;
        validateName(formDto, errors);
        if (formDto.isNewly()) {
            validatePhotos(formDto, errors);
        }
    }

    private void validatePhotos(AlbumFormDto formDto, Errors errors) {
        List<MultipartFile> files = formDto.getFiles();
        if (files.isEmpty()) {
            errors.rejectValue("files", null, "请选择相册封面照片");
            return;
        }
        //只验证第一张图片
        MultipartFile file1 = files.get(0);
        if (file1 == null || file1.isEmpty()) {
            errors.rejectValue("files", null, "请选择相册封面照片");
        } else {
            if (!ImageUtils.isImageFilename(file1.getOriginalFilename())) {
                errors.rejectValue("files", null, "图片格式不正确, 允许的格式为: " + ImageUtils.IMAGE_EXTENSIONS);
            }
            if (!ImageUtils.isAvailableUploadImageSize(file1.getSize())) {
                errors.rejectValue("files", null, "图片最大不能超出1MB");
            }
        }

    }


    private void validateName(AlbumFormDto formDto, Errors errors) {
        String name = formDto.getName();
        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", null, "相册名称不能为空");
            return;
        }

        if (!name.equals(formDto.getExistName())) {
            boolean existAlbum = albumService.isExistAlbumByName(name);
            if (existAlbum) {
                errors.rejectValue("name", null, "相册名称已存在");
            }
        }
    }
}
