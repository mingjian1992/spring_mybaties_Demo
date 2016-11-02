package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.player.PlayerAccountDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.service.PlayerService;
import com.andaily.zhishifenzi.web.validator.PlayerAccountDtoValidator;
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
 * 14-4-15 下午10:00
 *
 * @author Shengzhao Li
 */
@Controller
//player guid
@RequestMapping("/b/player/account/{playerGuid}.zsfz")
public class PlayerAccountFormController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerAccountDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("playerGuid") String playerGuid, Model model) {
        PlayerAccountDto formDto = playerService.loadPlayerAccountDto(playerGuid);
        model.addAttribute("formDto", formDto);
        return "backend/player/player_account_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") PlayerAccountDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/player/player_account_form";
        }
        String guid = playerService.persistPlayerAccountDto(formDto);
        model.addAttribute("alert", "persistSuccess");
        return "redirect:" + guid + ".zsfz";
    }

}
