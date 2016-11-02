package com.andaily.zhishifenzi.web.controller;

import com.andaily.zhishifenzi.domain.dto.IndexDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchDetailsDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchPhotosDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.service.AlbumService;
import com.andaily.zhishifenzi.service.CommonsService;
import com.andaily.zhishifenzi.service.MatchService;
import com.andaily.zhishifenzi.service.PlayerService;
import com.andaily.zhishifenzi.web.utils.CookieUserAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shengzhao Li
 */
@Controller
public class IndexController {


    @Autowired
    private CommonsService commonsService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("index.zsfz")
    public String index(Model model) {
        IndexDto indexDto = commonsService.loadIndexDto();
        model.addAttribute("indexDto", indexDto);
        return "index";
    }


    @RequestMapping("go_login.zsfz")
    public String goLogin(HttpServletRequest request, Model model) {
        CookieUserAssistant cookieUserAssistant = new CookieUserAssistant(request).retrieve();
        model.addAttribute("username", cookieUserAssistant.getUsername());
        return "login";
    }

    @RequestMapping("clean_cookie.zsfz")
    public String cleanCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUserAssistant cookieUserAssistant = new CookieUserAssistant(request);
        cookieUserAssistant.clean(response);
        return "redirect:index.zsfz?alert=cleanCookieSuccess";
    }


    @RequestMapping("match_details/{guid}.zsfz")
    public String matchDetails(@PathVariable("guid") String guid, Model model) {
        MatchDetailsDto detailsDto = matchService.loadMatchDetailsDto(guid);
        model.addAttribute("detailsDto", detailsDto);
        return "match/match_details";
    }

    @RequestMapping("player_info/{guid}.zsfz")
    public String playerInfo(@PathVariable("guid") String guid, Model model) {
        PlayerDto playerDto = playerService.loadPlayerDto(guid);
        model.addAttribute("playerDto", playerDto);
        return "player_info";
    }

    //Load match photos
    @RequestMapping("match_photos/{guid}.zsfz")
    public String matchPhotos(@PathVariable("guid") String guid, Model model) {
        MatchPhotosDto photosDto = albumService.loadMatchPhotosDto(guid);
        model.addAttribute("photosDto", photosDto);
        return "match_photos";
    }


}