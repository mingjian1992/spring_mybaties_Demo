package com.andaily.zhishifenzi.web.validator;


import com.andaily.zhishifenzi.domain.dto.user.MyProfileDto;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class MyProfileDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MyProfileDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", null, "姓名不能为空");

        MyProfileDto profileDto = (MyProfileDto) target;
        validateEmail(profileDto, errors);

    }

    private void validateEmail(MyProfileDto profileDto, Errors errors) {
        String email = profileDto.getEmail();
        if (!MatchUtils.isEmail(email)) {
            errors.rejectValue("email", null, "邮件地址格式错误");
        }
    }

}