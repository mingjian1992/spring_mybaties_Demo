package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.club.ClubRecordDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumFormDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumRecordDto;
import com.andaily.zhishifenzi.service.ClubService;
import com.andaily.zhishifenzi.service.StadiumService;
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
@RequestMapping("/b/stadium/")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;


    @RequestMapping("archive/{guid}.zsfz")
    public String archive(@PathVariable("guid") String guid, Model model) {
        stadiumService.archiveStadium(guid);
        model.addAttribute("alert", "archiveFinishInfo");
        return "redirect:../../stadium.zsfz";
    }


    @RequestMapping("record.zsfz")
    public String record(StadiumRecordDto recordDto, Model model) {
        recordDto = stadiumService.loadStadiumRecordDto(recordDto);
        model.addAttribute("recordDto", recordDto);
        return "backend/stadium/stadium_record";
    }

}
