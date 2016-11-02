package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.service.ClubService;
import com.andaily.zhishifenzi.web.validator.ClubFormDtoValidator;
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
@RequestMapping("/b/club/form/{guid}.zsfz")
public class ClubFormController {

    @Autowired
    private ClubService clubService;
    @Autowired
    private ClubFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("guid") String guid, Model model) {
        ClubFormDto formDto = clubService.loadClubFormDto(guid);
        model.addAttribute("formDto", formDto);
        return "backend/club/club_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") ClubFormDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/club/club_form";
        }
        clubService.persistClubFormDto(formDto);
        model.addAttribute("alert", "persistSuccess");
        return "redirect:../../club.zsfz";
    }

}
