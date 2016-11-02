package com.andaily.zhishifenzi.web.controller.backend.system;

import com.andaily.zhishifenzi.domain.dto.album.PhotoDto;
import com.andaily.zhishifenzi.domain.dto.file.SyncPhotosDto;
import com.andaily.zhishifenzi.service.GeckoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/system/")
public class SystemPhotoController {


    @Autowired
    private GeckoFileService geckoFileService;


    @RequestMapping("sync_photos.zsfz")
    public String syncPhotos(SyncPhotosDto syncPhotosDto, Model model) {
        geckoFileService.loadSyncPhotosDto(syncPhotosDto);
        model.addAttribute("syncPhotosDto", syncPhotosDto);
        return "system/sync_photos";
    }

    @RequestMapping(value = "transfer_photo/{guid}.zsfz", method = RequestMethod.GET)
    public String transferPhoto(@PathVariable("guid") String guid, Model model) {
        PhotoDto photoDto = geckoFileService.loadPhotoDtoByGuid(guid);
        model.addAttribute("photoDto", photoDto);
        return "system/transfer_photo";
    }

    @RequestMapping(value = "transfer_photo/{guid}.zsfz", method = RequestMethod.POST)
    public String submitTransferPhoto(@PathVariable("guid") String guid, boolean deleteLocalPhoto, Model model) {
        boolean result = geckoFileService.transferPhotoToCloud(guid, deleteLocalPhoto);
        model.addAttribute("alert", result ? "transferSuccess" : "transferFailed");
        return "redirect:../sync_photos.zsfz?displayType=true";
    }

}