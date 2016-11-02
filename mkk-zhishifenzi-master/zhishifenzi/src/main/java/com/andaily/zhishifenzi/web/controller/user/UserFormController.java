package com.andaily.zhishifenzi.web.controller.user;


import com.andaily.zhishifenzi.domain.dto.user.UserFormDto;
import com.andaily.zhishifenzi.service.UserService;
import com.andaily.zhishifenzi.web.validator.UserFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/user/form/{guid}.wdcy")
public class UserFormController {

    @Value("#{properties['default.password']}")
    private String defaultPassword;

    @Autowired
    private UserService userService;
    @Autowired
    private UserFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, Model model) {
        UserFormDto userFormDto = userService.loadUserFormDto(guid);
        model.addAttribute("userFormDto", userFormDto);
        model.addAttribute("defaultPassword", defaultPassword);
        return "user/user_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("userFormDto") UserFormDto userFormDto, BindingResult result) {
        validator.validate(userFormDto, result);
        if (result.hasErrors()) {
            return "user/user_form";
        }
        userService.persistUserFormDto(userFormDto);
        return "redirect:../overview.wdcy?alert=persistSuccess";
    }


}