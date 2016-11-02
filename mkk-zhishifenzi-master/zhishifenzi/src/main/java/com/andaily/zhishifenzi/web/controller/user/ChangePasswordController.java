package com.andaily.zhishifenzi.web.controller.user;

import com.andaily.zhishifenzi.domain.dto.user.ChangePasswordDto;
import com.andaily.zhishifenzi.service.UserService;
import com.andaily.zhishifenzi.web.validator.ChangePasswordDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/change_password.zsfz")
public class ChangePasswordController {


    @Autowired
    private UserService userService;
    @Autowired
    private ChangePasswordDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model) {
        ChangePasswordDto changePasswordDto = userService.loadChangePasswordDto();
        model.addAttribute("changePasswordDto", changePasswordDto);
        return "change_password";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("changePasswordDto") ChangePasswordDto changePasswordDto, BindingResult result) {
        validator.validate(changePasswordDto, result);
        if (result.hasErrors()) {
            return "change_password";
        }
        userService.changePassword(changePasswordDto);
        return "redirect:../signout?pass=true";
    }

}