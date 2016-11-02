package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumFormDto;
import com.andaily.zhishifenzi.service.ClubService;
import com.andaily.zhishifenzi.service.StadiumService;
import com.andaily.zhishifenzi.web.validator.ClubFormDtoValidator;
import com.andaily.zhishifenzi.web.validator.StadiumFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 14-3-16 下午10:00
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/stadium/form/{guid}.zsfz")
public class StadiumFormController {

    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private StadiumFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("guid") String guid, Model model) {
        StadiumFormDto formDto = stadiumService.loadStadiumFormDto(guid);
        model.addAttribute("formDto", formDto);
        return "backend/stadium/stadium_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") StadiumFormDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/stadium/stadium_form";
        }
        stadiumService.persistStadiumFormDto(formDto);
        model.addAttribute("alert", "persistSuccess");
        return "redirect:../../stadium.zsfz";
    }

}
