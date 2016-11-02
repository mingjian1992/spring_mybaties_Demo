package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubRecordDto;
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
@RequestMapping("/b/club/")
public class ClubController {

    @Autowired
    private ClubService clubService;


    @RequestMapping("archive/{guid}.zsfz")
    public String archive(@PathVariable("guid") String guid, Model model) {
        clubService.archiveClub(guid);
        model.addAttribute("alert", "archiveFinishInfo");
        return "redirect:../../club.zsfz";
    }

    @RequestMapping("record.zsfz")
    public String record(ClubRecordDto recordDto, Model model) {
        recordDto = clubService.loadClubRecordDto(recordDto);
        model.addAttribute("recordDto", recordDto);
        return "backend/club/club_record";
    }


}
