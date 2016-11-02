package com.andaily.zhishifenzi.web.controller.backend.album;

import com.andaily.zhishifenzi.domain.dto.album.AlbumFormDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerFormDto;
import com.andaily.zhishifenzi.service.AlbumService;
import com.andaily.zhishifenzi.web.validator.AlbumFormDtoValidator;
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
 * 14-3-22 下午5:03
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/album/form/{guid}.zsfz")
public class AlbumFormController {


    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumFormDtoValidator validator;


    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("guid") String guid, Model model) {
        AlbumFormDto formDto = albumService.loadAlbumFormDto(guid);
        model.addAttribute("formDto", formDto);
        return "backend/album/album_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") AlbumFormDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/album/album_form";
        }
        albumService.persistAlbumFormDto(formDto);
        model.addAttribute("alert", "persistSuccess");
        return "redirect:../../album.zsfz";
    }


}
