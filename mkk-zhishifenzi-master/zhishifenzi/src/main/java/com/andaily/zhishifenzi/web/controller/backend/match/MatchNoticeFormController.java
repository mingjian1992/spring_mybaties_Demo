package com.andaily.zhishifenzi.web.controller.backend.match;

import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeFormDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.service.MatchService;
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
 * 14-3-21 下午10:42
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/match_notice/form/{guid}.zsfz")
public class MatchNoticeFormController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private MatchNoticeFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("guid") String guid, Model model) {
        MatchNoticeFormDto formDto = matchService.loadMatchNoticeFormDto(guid);
        model.addAttribute("formDto", formDto);
        model.addAttribute("startTime", DateUtils.toDateText(DateUtils.now()));
        return "backend/match/match_notice_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") MatchNoticeFormDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/match/match_notice_form";
        }
        matchService.persistMatchNoticeFormDto(formDto);
        model.addAttribute("alert", "persistSuccess");
        return "redirect:../../match_notice.zsfz";
    }


}
