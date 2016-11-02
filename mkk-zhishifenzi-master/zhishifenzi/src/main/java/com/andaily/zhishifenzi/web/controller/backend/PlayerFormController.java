package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.service.PlayerService;
import com.andaily.zhishifenzi.web.validator.PlayerFormDtoValidator;
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
@RequestMapping("/b/player/form/{guid}.zsfz")
public class PlayerFormController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("guid") String guid, Model model) {
        PlayerFormDto formDto = playerService.loadPlayerFormDto(guid);
        model.addAttribute("formDto", formDto);
        return "backend/player/player_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") PlayerFormDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/player/player_form";
        }
        playerService.persistPlayerFormDto(formDto);
        model.addAttribute("alert", "persistSuccess");
        return "redirect:../../player.zsfz";
    }

}
