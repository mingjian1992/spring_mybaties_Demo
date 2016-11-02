package com.andaily.zhishifenzi.web.controller.backend.album;

import com.andaily.zhishifenzi.domain.dto.album.FrontPhotoDto;
import com.andaily.zhishifenzi.service.CommonsService;
import com.andaily.zhishifenzi.web.validator.FrontPhotoDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/album/front_form/{guid}.zsfz")
public class FrontPhotoFormController {


    @Autowired
    private CommonsService commonsService;
    @Autowired
    private FrontPhotoDtoValidator validator;


    @RequestMapping(method = RequestMethod.GET)
    public String showForm(@PathVariable("guid") String guid, Model model) {
        FrontPhotoDto formDto = commonsService.loadFrontPhotoDto(guid);
        model.addAttribute("formDto", formDto);
        return "backend/album/front_photo_form";
    }

    //submit
    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("formDto") FrontPhotoDto formDto, Model model, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "backend/album/front_photo_form";
        }
        commonsService.updateFrontPhotoDto(formDto);
        model.addAttribute("alert", "updateSuccess");
        return "redirect:../front_photos.zsfz";
    }


}