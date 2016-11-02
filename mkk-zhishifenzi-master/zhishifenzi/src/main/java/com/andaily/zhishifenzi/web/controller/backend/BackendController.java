package com.andaily.zhishifenzi.web.controller.backend;

import com.andaily.zhishifenzi.domain.dto.BackendIndexDto;
import com.andaily.zhishifenzi.domain.dto.album.AlbumListDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubListDto;
import com.andaily.zhishifenzi.domain.dto.file.HeadImageUploadDto;
import com.andaily.zhishifenzi.domain.dto.match.*;
import com.andaily.zhishifenzi.domain.dto.player.PlayerListDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumListDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.service.*;
import com.andaily.zhishifenzi.web.utils.WebUtils;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * 14-3-12 下午10:55
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/b/")
public class BackendController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private UserService userService;


    @RequestMapping("index.zsfz")
    public String index(Model model) {
        BackendIndexDto indexDto = userService.loadBackendIndexDto();
        model.addAttribute("indexDto", indexDto);
        return "backend/b_index";
    }


    @RequestMapping("my_data.zsfz")
    public String myData(Model model) {
        MyDataDto dataDto = matchService.loadMyDataDto();
        model.addAttribute("dataDto", dataDto);
        return "backend/my_data";
    }

    @RequestMapping("my_match.zsfz")
    public String myMatch(MyMatchesDto matchesDto, Model model) {
        matchesDto = matchService.loadMyMatchesDto(matchesDto);
        model.addAttribute("matchesDto", matchesDto);
        return "backend/my_match";
    }

    @RequestMapping("match.zsfz")
    public String match(MatchListDto listDto, Model model) {
        listDto = matchService.loadMatchListDto(listDto);
        model.addAttribute("listDto", listDto);
        model.addAttribute("today", DateUtils.toDateText(DateUtils.now()));
        return "backend/b_match";
    }

    @RequestMapping("club.zsfz")
    public String club(ClubListDto listDto, Model model) {
        listDto = clubService.loadClubListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "backend/b_club";
    }

    @RequestMapping("album.zsfz")
    public String album(AlbumListDto listDto, Model model) {
        listDto = albumService.loadAlbumListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "backend/b_album";
    }

    @RequestMapping("player.zsfz")
    public String player(PlayerListDto listDto, Model model) {
        listDto = playerService.loadPlayerListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "backend/b_player";
    }

    @RequestMapping("stadium.zsfz")
    public String stadium(StadiumListDto listDto, Model model) {
        listDto = stadiumService.loadStadiumListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "backend/b_stadium";
    }

    @RequestMapping("season.zsfz")
    public String season(SeasonListDto listDto, Model model) {
        listDto = seasonService.loadSeasonListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "backend/b_season";
    }

    @RequestMapping("match_notice.zsfz")
    public String matchNotice(MatchNoticeListDto listDto, Model model) {
        listDto = matchService.loadMatchNoticeListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "backend/b_match_notice";
    }


    @RequestMapping(value = "upload_head_image.zsfz", method = RequestMethod.POST)
    public void upload(HttpServletResponse response, HeadImageUploadDto uploadDto) {
        String fileGuid = userService.saveHeadImageUploadDto(uploadDto);
        WebUtils.writeJson(response, JSONObject.fromObject("{fileGuid:'" + fileGuid + "'}"));
    }

    @RequestMapping(value = "submit_head_image.zsfz", method = RequestMethod.POST)
    public String submitHeadImage(HeadImageUploadDto uploadDto, Model model) {
        userService.persistCropHeadImage(uploadDto);
        return "redirect:index.zsfz";
    }

}
