package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.user.ChangePasswordDto;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.PasswordHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class ChangePasswordDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePasswordDto.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePasswordDto changePasswordDto = (ChangePasswordDto) target;
        validateOldPassword(changePasswordDto, errors);
        validatePassword(changePasswordDto, errors);
    }

    private void validateOldPassword(ChangePasswordDto changePasswordDto, Errors errors) {
        String oldPassword = changePasswordDto.getOldPassword();
        if (StringUtils.isEmpty(oldPassword)) {
            errors.rejectValue("oldPassword", null, "旧密码不能为空");
            return;
        }

        String password = SecurityUtils.currentUser().password();
        String tempPassword = PasswordHandler.encryptPassword(oldPassword);
        if (!tempPassword.equals(password)) {
            errors.rejectValue("oldPassword", null, "旧密码不正确");
        }

    }

    private void validatePassword(ChangePasswordDto changePasswordDto, Errors errors) {
        String password = changePasswordDto.getNewPassword();
        if (password.length() < 6) {
            errors.rejectValue("newPassword", null, "新密码至少6位");
            return;
        }
        if (!password.equals(changePasswordDto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", null, "两次输入的密码不相同");
        }

    }

}