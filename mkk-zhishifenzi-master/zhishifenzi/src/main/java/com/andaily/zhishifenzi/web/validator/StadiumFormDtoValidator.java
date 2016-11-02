package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumFormDto;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.service.ClubService;
import com.andaily.zhishifenzi.service.StadiumService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 14-3-16 下午10:22
 *
 * @author Shengzhao Li
 */
@Component
public class StadiumFormDtoValidator implements Validator {

    @Autowired
    private StadiumService stadiumService;


    @Override
    public boolean supports(Class<?> aClass) {
        return StadiumFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact", null, "联系人不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", null, "球场地址不能为空");

        StadiumFormDto formDto = (StadiumFormDto) o;
        validateName(formDto, errors);
    }

    private void validateName(StadiumFormDto formDto, Errors errors) {
        String name = formDto.getName();
        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", null, "球场名称不能为空");
            return;
        }

        if (!name.equals(formDto.getExistName())) {
            Stadium stadium = stadiumService.loadStadiumByName(name);
            if (stadium != null) {
                errors.rejectValue("name", null, "该球场名称已经存在");
            }
        }
    }
}
