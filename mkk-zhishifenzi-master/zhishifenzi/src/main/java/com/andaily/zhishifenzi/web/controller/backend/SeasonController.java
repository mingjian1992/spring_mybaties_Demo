package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonListDto;
import com.andaily.zhishifenzi.service.SeasonService;
import com.andaily.zhishifenzi.web.validator.SeasonDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 14-3-16 上午2:15
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/")
public class SeasonController {


    @Autowired
    private SeasonService seasonService;
    @Autowired
    private SeasonDtoValidator validator;


    @RequestMapping(value = "season/archive/{guid}.zsfz")
    public String archive(@PathVariable("guid") String guid, Model model) {
        seasonService.archiveSeason(guid);
        model.addAttribute("alert", "archiveFinishInfo");
        return "redirect:../../season.zsfz";
    }

    @RequestMapping(value = "season/submit.zsfz", method = RequestMethod.POST)
    public String submit(@ModelAttribute("listDto") SeasonListDto listDto, Model model, BindingResult result) {
        validator.validate(listDto.getFormDto(), result);
        if (result.hasErrors()) {
            return "backend/b_season";
        }
        seasonService.persistSeasonDto(listDto.getFormDto());
        model.addAttribute("alert", "persistSuccess");
        return "redirect:../season.zsfz";
    }


}
