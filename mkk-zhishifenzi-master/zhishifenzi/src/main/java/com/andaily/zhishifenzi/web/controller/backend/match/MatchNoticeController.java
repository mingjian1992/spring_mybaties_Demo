package com.andaily.zhishifenzi.web.controller.backend.match;

import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeFormDto;
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
@RequestMapping("/b/match_notice/")
public class MatchNoticeController {

    @Autowired
    private MatchService matchService;


    @RequestMapping("publish/{guid}.zsfz")
    public String publish(@PathVariable("guid") String guid, Model model) {
        matchService.publishMatchNotice(guid);
        model.addAttribute("alert", "publishMatchNoticeInfo");
        return "redirect:../../match_notice.zsfz";
    }

    @RequestMapping("cancel/{guid}.zsfz")
    public String cancelPublish(@PathVariable("guid") String guid, Model model) {
        matchService.cancelPublishMatchNotice(guid);
        model.addAttribute("alert", "cancelMatchNoticeInfo");
        return "redirect:../../match_notice.zsfz";
    }

    @RequestMapping("archive/{guid}.zsfz")
    public String archive(@PathVariable("guid") String guid, Model model) {
        matchService.archiveMatchNotice(guid);
        model.addAttribute("alert", "archiveMatchNoticeSuccess");
        return "redirect:../../match_notice.zsfz";
    }


}
