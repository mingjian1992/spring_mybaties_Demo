package com.andaily.zhishifenzi.web.controller.backend.system;

import com.andaily.zhishifenzi.domain.dto.GlobalSettingDto;
import com.andaily.zhishifenzi.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/system/global_setting.zsfz")
public class SystemGlobalSettingController {


    @Autowired
    private CommonsService commonsService;


    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Model model) {
        GlobalSettingDto formDto = commonsService.loadGlobalSettingDto();
        model.addAttribute("formDto", formDto);
        return "system/global_setting";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") GlobalSettingDto formDto, Model model) {
        commonsService.updateGlobalSetting(formDto);
        model.addAttribute("alert", "updateSuccess");
        return "redirect:global_setting.zsfz";
    }


}