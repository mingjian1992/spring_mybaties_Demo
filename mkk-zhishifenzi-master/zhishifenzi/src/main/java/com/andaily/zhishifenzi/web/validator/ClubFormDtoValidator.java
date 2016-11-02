package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.service.ClubService;
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
public class ClubFormDtoValidator implements Validator {

    @Autowired
    private ClubService clubService;


    @Override
    public boolean supports(Class<?> aClass) {
        return ClubFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact", null, "联系人不能为空");

        ClubFormDto formDto = (ClubFormDto) o;
        validateName(formDto, errors);
    }

    private void validateName(ClubFormDto formDto, Errors errors) {
        String name = formDto.getName();
        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", null, "对手名称不能为空");
            return;
        }

        if (!name.equals(formDto.getExistName())) {
            Club club = clubService.loadClubByName(name);
            if (club != null) {
                errors.rejectValue("name", null, "该对手名称已经存在");
            }
        }
    }
}
