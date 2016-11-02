package com.andaily.zhishifenzi.web.controller.backend.system;

import com.andaily.zhishifenzi.domain.dto.log.SystemLogsDto;
import com.andaily.zhishifenzi.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shengzhao Li
 */
@Controller()
@RequestMapping("/b/system/")
public class SystemLogController {

    @Autowired
    private CommonsService commonsService;


    @RequestMapping("logs.zsfz")
    public String systemOverview(SystemLogsDto systemLogsDto, Model model) {
        systemLogsDto = commonsService.loadSystemLogsDto(systemLogsDto);
        model.addAttribute("overviewDto", systemLogsDto);
        return "system/system_logs";
    }
}