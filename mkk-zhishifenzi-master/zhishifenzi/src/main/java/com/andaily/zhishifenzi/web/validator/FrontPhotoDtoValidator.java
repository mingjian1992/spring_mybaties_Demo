package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.album.FrontPhotoDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class FrontPhotoDtoValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return FrontPhotoDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FrontPhotoDto formDto = (FrontPhotoDto) target;
        validateURL(formDto, errors);
        validateDescription(formDto, errors);
    }

    private void validateDescription(FrontPhotoDto formDto, Errors errors) {
        final String description = formDto.getDescription();
        if (StringUtils.isEmpty(description) || description.length() > 50) {
            errors.rejectValue("description", null, "描述不空或长度超出50");
        }
    }

    private void validateURL(FrontPhotoDto formDto, Errors errors) {
        final String url = formDto.getUrl();
        if (StringUtils.isEmpty(url)) {
            errors.rejectValue("url", null, "URL不能为空");
        } else {
            if (!url.startsWith("http")
                    && (!url.endsWith("jpg")
                    || !url.endsWith("jpeg"))) {
                errors.rejectValue("url", null, "URL地址格式错误或不是图片(jpg,jpeg)");
            }
        }
    }
}