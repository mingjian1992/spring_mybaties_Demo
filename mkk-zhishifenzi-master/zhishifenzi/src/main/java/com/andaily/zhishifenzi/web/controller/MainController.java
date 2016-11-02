package com.andaily.zhishifenzi.web.controller;

import com.andaily.zhishifenzi.domain.dto.album.AlbumMainDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchMainDto;
import com.andaily.zhishifenzi.domain.dto.statistics.MainDataDto;
import com.andaily.zhishifenzi.domain.dto.statistics.ZsfzRecordDto;
import com.andaily.zhishifenzi.domain.dto.statistics.ZsfzRecordMatchListDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.service.AlbumService;
import com.andaily.zhishifenzi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 14-3-11 下午10:17
 *
 * @author Shengzhao Li
 */
@Controller
public class MainController {


    @Autowired
    private AlbumService albumService;
    @Autowired
    private MatchService matchService;


    @RequestMapping("match.zsfz")
    public String match(MatchMainDto mainDto, Model model) {
        mainDto = matchService.loadMatchMainDto(mainDto);
        model.addAttribute("mainDto", mainDto);
        model.addAttribute("today", DateUtils.toDateText(DateUtils.now()));
        return "match/match_main";
    }

    @RequestMapping("data.zsfz")
    public String data(MainDataDto dataDto, Model model) {
        dataDto = matchService.loadMainDataDto(dataDto);
        model.addAttribute("dataDto", dataDto);
        model.addAttribute("today", DateUtils.toDateText(DateUtils.now()));
        return "data/data_main";
    }

    @RequestMapping("album.zsfz")
    public String album(AlbumMainDto mainDto, Model model) {
        mainDto = albumService.loadAlbumMainDto(mainDto);
        model.addAttribute("mainDto", mainDto);
        return "album/album_main";
    }

    //记录
    @RequestMapping("record.zsfz")
    public String record(Model model) {
        ZsfzRecordDto recordDto = matchService.loadZSFZRecordDto();
        model.addAttribute("recordDto", recordDto);
        return "record/record_main";
    }


    //记录- 最佳射手 进球记录
    @RequestMapping("record_player_goal.zsfz")
    public String recordPlayerGoal(ZsfzRecordMatchListDto listDto, Model model) {
        matchService.loadZsfzRecordMatchGoalListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "record/record_player_goal";
    }

    //记录- 助攻记录
    @RequestMapping("record_player_assist.zsfz")
    public String recordPlayerAssist(ZsfzRecordMatchListDto listDto, Model model) {
        matchService.loadZsfzRecordMatchAssistListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "record/record_player_assist";
    }

    //记录- 参赛最多的队员
    @RequestMapping("record_player_join.zsfz")
    public String recordPlayerJoin(ZsfzRecordMatchListDto listDto, Model model) {
        matchService.loadZsfzRecordMatchJoinListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "record/record_player_join";
    }

    //记录- 最长连胜,最长连败
    @RequestMapping("record_period_match.zsfz")
    public String recordPeriodMatch(ZsfzRecordMatchListDto listDto, Model model) {
        matchService.loadZsfzRecordMatchPeriodListDto(listDto);
        model.addAttribute("listDto", listDto);
        return "record/record_period_match";
    }

    //关于
    @RequestMapping("about.zsfz")
    public String about() {
        return "about/about_main";
    }


}
