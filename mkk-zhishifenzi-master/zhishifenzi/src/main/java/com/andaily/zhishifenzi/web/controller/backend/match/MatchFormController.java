package com.andaily.zhishifenzi.web.controller.backend.match;

import com.andaily.zhishifenzi.domain.dto.match.MatchFormDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeFormDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.service.MatchService;
import com.andaily.zhishifenzi.web.validator.MatchFormDtoValidator;
import com.andaily.zhishifenzi.web.validator.MatchNoticeFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 14-4-3 下午11:42
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/match/form/{guid}.zsfz")
public class MatchFormController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private MatchFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("guid") String guid, Model model) {
        MatchFormDto formDto = matchService.loadMatchFormDto(guid);
        model.addAttribute("formDto", formDto);
        return "backend/match/match_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") MatchFormDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/match/match_form";
        }
        matchService.persistMatchFormDto(formDto);
        model.addAttribute("alert", "persistSuccess");
        return "redirect:../../match.zsfz";
    }


}
