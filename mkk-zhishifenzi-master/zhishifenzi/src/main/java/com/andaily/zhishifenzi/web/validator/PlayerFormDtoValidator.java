package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumFormDto;
import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.service.PlayerService;
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
public class PlayerFormDtoValidator implements Validator {

    @Autowired
    private PlayerService playerService;


    @Override
    public boolean supports(Class<?> aClass) {
        return PlayerFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", null, "请选择场上位置");

        PlayerFormDto formDto = (PlayerFormDto) o;
        validateFullName(formDto, errors);
    }

    private void validateFullName(PlayerFormDto formDto, Errors errors) {
        String fullName = formDto.getFullName();
        if (StringUtils.isEmpty(fullName)) {
            errors.rejectValue("fullName", null, "球员名字不能为空");
            return;
        }

        if (!fullName.equals(formDto.getExistName())) {
            Player player = playerService.loadPlayerByFullName(fullName);
            if (player != null) {
                errors.rejectValue("fullName", null, "该球员名字已经存在");
            }
        }
    }
}
