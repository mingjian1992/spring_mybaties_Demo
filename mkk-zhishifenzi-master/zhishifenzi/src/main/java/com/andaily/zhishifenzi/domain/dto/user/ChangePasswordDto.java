package com.andaily.zhishifenzi.domain.dto.user;

/**
 * @author Shengzhao Li
 */
public class ChangePasswordDto extends UserDto {

    private String oldPassword;

    private String newPassword;
    private String confirmPassword;


    public ChangePasswordDto() {
    }

    public ChangePasswordDto(String username) {
        this.username = username;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}