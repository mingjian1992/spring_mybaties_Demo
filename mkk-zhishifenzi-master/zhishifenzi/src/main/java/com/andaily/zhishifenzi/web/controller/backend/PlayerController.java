package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.match.PlayerDataDto;
import com.andaily.zhishifenzi.domain.user.UserStatus;
import com.andaily.zhishifenzi.service.PlayerService;
import com.andaily.zhishifenzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 14-3-21 下午9:01
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/player/")
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @RequestMapping("archive/{guid}.zsfz")
    public String archive(@PathVariable("guid") String guid, Model model) {
        playerService.archivePlayer(guid);
        model.addAttribute("alert", "archiveFinishInfo");
        return "redirect:../../player.zsfz";
    }

    @RequestMapping("account/disable/{guid}.zsfz")
    public String disablePlayerUser(@PathVariable("guid") String guid, Model model) {
        String playerGuid = playerService.changePlayerUserStatus(guid, UserStatus.DISABLED);
        model.addAttribute("alert", "disableFinishInfo");
        return "redirect:../" + playerGuid + ".zsfz";
    }

    @RequestMapping("account/enable/{guid}.zsfz")
    public String enablePlayerUser(@PathVariable("guid") String guid, Model model) {
        String playerGuid = playerService.changePlayerUserStatus(guid, UserStatus.ENABLED);
        model.addAttribute("alert", "enableAccountSuccess");
        return "redirect:../" + playerGuid + ".zsfz";
    }


    //reset password
    @RequestMapping("account/reset_password/{guid}.zsfz")
    public String reset(@PathVariable("guid") String guid, Model model) {
        String newPass = playerService.resetPlayerUserPassword(guid);
        model.addAttribute("newPass", newPass);
        return "backend/player/reset_password_ok";
    }


    //data
    @RequestMapping("data_{guid}.zsfz")
    public String data(@PathVariable("guid") String guid, Model model) {
        PlayerDataDto dataDto = playerService.loadPlayerDataDto(guid);
        model.addAttribute("dataDto", dataDto);
        return "backend/player/player_data";
    }
}
