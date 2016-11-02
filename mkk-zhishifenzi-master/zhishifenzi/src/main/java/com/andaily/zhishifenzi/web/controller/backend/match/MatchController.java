package com.andaily.zhishifenzi.web.controller.backend.match;

import com.andaily.zhishifenzi.domain.dto.match.MatchDetailsDto;
import com.andaily.zhishifenzi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/match/")
public class MatchController {

    @Autowired
    private MatchService matchService;


    @RequestMapping("archive/{guid}.zsfz")
    public String archive(@PathVariable("guid") String guid, Model model) {
        matchService.archiveMatch(guid);
        model.addAttribute("alert", "archiveMatchInfo");
        return "redirect:../../match.zsfz";
    }

    @RequestMapping("details/{guid}.zsfz")
    public String details(@PathVariable("guid") String guid, Model model) {
        MatchDetailsDto detailsDto = matchService.loadMatchDetailsDto(guid);
        model.addAttribute("detailsDto", detailsDto);
        return "backend/match/match_details";
    }


}
