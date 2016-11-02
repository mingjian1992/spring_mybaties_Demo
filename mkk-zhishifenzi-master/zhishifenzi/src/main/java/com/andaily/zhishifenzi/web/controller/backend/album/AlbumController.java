package com.andaily.zhishifenzi.web.controller.backend.album;

import com.andaily.zhishifenzi.domain.dto.album.AlbumManageDto;
import com.andaily.zhishifenzi.domain.dto.album.FrontPhotosDto;
import com.andaily.zhishifenzi.domain.dto.album.PhotoUploadDto;
import com.andaily.zhishifenzi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 14-3-22 下午5:03
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/album/")
public class AlbumController {


    @Autowired
    private AlbumService albumService;


    @RequestMapping("archive/{guid}.zsfz")
    public String archive(@PathVariable("guid") String guid, Model model) {
        albumService.archiveAlbum(guid);
        model.addAttribute("alert", "archiveFinishInfo");
        return "redirect:../../album.zsfz";
    }

    @RequestMapping("manage/{guid}.zsfz")
    public String manage(@PathVariable("guid") String guid, Model model) {
        AlbumManageDto manageDto = albumService.loadAlbumManageDto(guid);
        model.addAttribute("manageDto", manageDto);
        return "backend/album/album_manage";
    }

    @RequestMapping(value = "manage/upload.zsfz", method = RequestMethod.POST)
    public void upload(PhotoUploadDto uploadDto, HttpServletResponse response) throws IOException {
        albumService.uploadPhotos(uploadDto);
        response.getWriter().print("");       //empty response
    }

    @RequestMapping("details/{guid}.zsfz")
    public String details(@PathVariable("guid") String guid, Model model) {
        AlbumManageDto manageDto = albumService.loadAlbumManageDto(guid);
        model.addAttribute("manageDto", manageDto);
        return "backend/album/album_details";
    }

    @RequestMapping("manage/remove/{guid}.zsfz")
    public String removePhoto(@PathVariable("guid") String guid, Model model) {
        String albumGuid = albumService.removePhoto(guid);
        model.addAttribute("alert", "removePhotoSuccess");
        return "redirect:../../manage/" + albumGuid + ".zsfz";
    }

    //首页照片管理页面
    @RequestMapping("front_photos.zsfz")
    public String frontPhotos(Model model) {
        FrontPhotosDto frontPhotosDto = albumService.loadFrontPhotosDto();
        model.addAttribute("frontPhotosDto", frontPhotosDto);
        return "backend/album/front_photos";
    }


}
