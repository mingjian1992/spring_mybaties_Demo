package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.user.UserFormDto;
import com.andaily.zhishifenzi.domain.user.UserRole;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;
import com.andaily.zhishifenzi.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class UserFormDtoValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", null, "姓名不能为空");

        UserFormDto userFormDto = (UserFormDto) target;
        validateUsername(userFormDto, errors);

        if (userFormDto.isNewly()) {
            validatePassword(userFormDto, errors);
        }

        validateUserRole(errors, userFormDto);
    }


    private void validateUserRole(Errors errors, UserFormDto userFormDto) {
        final UserRole userRole = userFormDto.getUserRole();
        if (userRole == null) {
            errors.rejectValue("userRole", null, "用户类型不能为空");
        }
    }

    private void validatePassword(UserFormDto userFormDto, Errors errors) {
        String password = userFormDto.getPassword();
        if (password.length() < 6) {
            errors.rejectValue("password", null, "用户密码至少6位");
            return;
        }
        if (!password.equals(userFormDto.getRePassword())) {
            errors.rejectValue("rePassword", null, "两次输入的密码不相同");
        }

    }


    private void validateUsername(UserFormDto userFormDto, Errors errors) {
        String username = userFormDto.getUsername();
        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("username", null, "用户名不能为空");
            return;
        }
        if (!username.equals(userFormDto.getExistUsername())) {
            boolean exist = userService.loadExistUsername(username);
            if (exist) {
                errors.rejectValue("username", null, "该用户名已经存在");
            }
        }
    }
}