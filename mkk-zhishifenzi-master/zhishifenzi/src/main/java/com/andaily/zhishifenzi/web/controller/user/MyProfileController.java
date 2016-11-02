package com.andaily.zhishifenzi.web.controller.user;


import com.andaily.zhishifenzi.domain.dto.user.MyProfileDto;
import com.andaily.zhishifenzi.service.UserService;
import com.andaily.zhishifenzi.web.validator.MyProfileDtoValidator;
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
@RequestMapping("/b/my_profile.zsfz")
public class MyProfileController {


    @Autowired
    private UserService userService;
    @Autowired
    private MyProfileDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showProfile(Model model) {
        MyProfileDto formDto = userService.loadMyProfileDto();
        model.addAttribute("formDto", formDto);
        return "my_profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String update(@ModelAttribute("formDto") MyProfileDto formDto, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "my_profile";
        }
        userService.updateMyProfile(formDto);
        return "redirect:my_profile.zsfz?alert=updateSuccess";
    }

}