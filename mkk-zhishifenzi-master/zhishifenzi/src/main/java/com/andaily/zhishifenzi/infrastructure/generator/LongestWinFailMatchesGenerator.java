package com.andaily.zhishifenzi.infrastructure.generator;

import com.andaily.zhishifenzi.domain.commons.StatisticsRepository;
import com.andaily.zhishifenzi.domain.dto.statistics.MatchScoreDiffDto;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.DateUtils;

import java.util.List;

/**
 * 14-9-25 下午9:28
 * <p/>
 * 生成 最长胜利与最长失败的数据
 *
 * @author Shengzhao Li
 */
public class LongestWinFailMatchesGenerator {

    private transient StatisticsRepository statisticsRepository = BeanProvider.getBean(StatisticsRepository.class);

    //最长连胜
    private int longestWinMatches;
    //最长连胜 开始日期
    private String longestWinMatchesStart;
    //最长连胜 开始的比赛guid
    private String longestWinStartMatchGuid;
    //最长连胜 结束日期
    private String longestWinMatchesEnd;
    //最长连胜 结束的比赛guid
    private String longestWinEndMatchGuid;

    //最长连败
    private int longestFailMatches;
    //最长连败 开始日期
    private String longestFailMatchesStart;
    //最长连败 开始的比赛guid
    private String longestFailStartMatchGuid;
    //最长连败 结束日期
    private String longestFailMatchesEnd;
    //最长连败 结束的比赛guid
    private String longestFailEndMatchGuid;


    public LongestWinFailMatchesGenerator() {
    }

    public LongestWinFailMatchesGenerator generate() {
        //定义临时变量,用于比较
        int longestWinMatches = 0;
        String longestWinMatchesStart = null;
        String longestWinStartMatchGuid = null;
        String longestWinMatchesEnd = null;
        String longestWinEndMatchGuid = null;

        int longestFailMatches = 0;
        String longestFailMatchesStart = null;
        String longestFailStartMatchGuid = null;
        String longestFailMatchesEnd = null;
        String longestFailEndMatchGuid = null;

        List<MatchScoreDiffDto> diffDtos = statisticsRepository.findAllMatchScoresDiff();
        for (MatchScoreDiffDto diffDto : diffDtos) {
            int diffScores = diffDto.getDiffScores();
            if (diffScores > 0) {
                //胜
                longestWinMatches++;
                if (longestWinMatchesStart == null) {
                    longestWinStartMatchGuid = longestWinEndMatchGuid = diffDto.getMatchGuid();
                    longestWinMatchesStart = longestWinMatchesEnd = DateUtils.toDateText(diffDto.getMatchTime());
                } else {
                    longestWinEndMatchGuid = diffDto.getMatchGuid();
                    longestWinMatchesEnd = DateUtils.toDateText(diffDto.getMatchTime());
                }
                //check fail
                if (this.longestFailMatches < longestFailMatches) {
                    this.longestFailMatches = longestFailMatches;
                    this.longestFailMatchesStart = longestFailMatchesStart;
                    this.longestFailMatchesEnd = longestFailMatchesEnd;
                    this.longestFailStartMatchGuid = longestFailStartMatchGuid;
                    this.longestFailEndMatchGuid = longestFailEndMatchGuid;
                }
                //reset
                longestFailMatches = 0;
                longestFailMatchesStart = null;
            } else if (diffScores < 0) {
                //负
                longestFailMatches++;
                if (longestFailMatchesStart == null) {
                    longestFailStartMatchGuid = longestFailEndMatchGuid = diffDto.getMatchGuid();
                    longestFailMatchesStart = longestFailMatchesEnd = DateUtils.toDateText(diffDto.getMatchTime());
                } else {
                    longestFailEndMatchGuid = diffDto.getMatchGuid();
                    longestFailMatchesEnd = DateUtils.toDateText(diffDto.getMatchTime());
                }
                //check win
                if (this.longestWinMatches < longestWinMatches) {
                    this.longestWinMatches = longestWinMatches;
                    this.longestWinMatchesStart = longestWinMatchesStart;
                    this.longestWinMatchesEnd = longestWinMatchesEnd;
                    this.longestWinStartMatchGuid = longestWinStartMatchGuid;
                    this.longestWinEndMatchGuid = longestWinEndMatchGuid;
                }
                //reset
                longestWinMatches = 0;
                longestWinMatchesStart = null;
            } else {
                //平
                if (this.longestWinMatches < longestWinMatches) {
                    this.longestWinMatches = longestWinMatches;
                    this.longestWinMatchesStart = longestWinMatchesStart;
                    this.longestWinMatchesEnd = longestWinMatchesEnd;
                    this.longestWinStartMatchGuid = longestWinStartMatchGuid;
                    this.longestWinEndMatchGuid = longestWinEndMatchGuid;
                }
                //reset
                longestWinMatches = 0;
                longestWinMatchesStart = null;

                if (this.longestFailMatches < longestFailMatches) {
                    this.longestFailMatches = longestFailMatches;
                    this.longestFailMatchesStart = longestFailMatchesStart;
                    this.longestFailMatchesEnd = longestFailMatchesEnd;
                    this.longestFailStartMatchGuid = longestFailStartMatchGuid;
                    this.longestFailEndMatchGuid = longestFailEndMatchGuid;
                }
                //reset
                longestFailMatches = 0;
                longestFailMatchesStart = null;

            }
        }
        //last check
        if (this.longestWinMatches < longestWinMatches) {
            this.longestWinMatches = longestWinMatches;
            this.longestWinMatchesStart = longestWinMatchesStart;
            this.longestWinMatchesEnd = longestWinMatchesEnd;
            this.longestWinStartMatchGuid = longestWinStartMatchGuid;
            this.longestWinEndMatchGuid = longestWinEndMatchGuid;
        }

        if (this.longestFailMatches < longestFailMatches) {
            this.longestFailMatches = longestFailMatches;
            this.longestFailMatchesStart = longestFailMatchesStart;
            this.longestFailMatchesEnd = longestFailMatchesEnd;
            this.longestFailStartMatchGuid = longestFailStartMatchGuid;
            this.longestFailEndMatchGuid = longestFailEndMatchGuid;
        }

        return this;
    }

    //按赛季生成
    public LongestWinFailMatchesGenerator generateBySeason(Season season) {
        throw new UnsupportedOperationException("not yet implements");
    }


    public int longestWinMatches() {
        return longestWinMatches;
    }

    public String longestWinMatchesStart() {
        return longestWinMatchesStart;
    }

    public String longestWinStartMatchGuid() {
        return longestWinStartMatchGuid;
    }

    public String longestWinMatchesEnd() {
        return longestWinMatchesEnd;
    }

    public String longestWinEndMatchGuid() {
        return longestWinEndMatchGuid;
    }

    public int longestFailMatches() {
        return longestFailMatches;
    }

    public String longestFailMatchesStart() {
        return longestFailMatchesStart;
    }

    public String longestFailStartMatchGuid() {
        return longestFailStartMatchGuid;
    }

    public String longestFailMatchesEnd() {
        return longestFailMatchesEnd;
    }

    public String longestFailEndMatchGuid() {
        return longestFailEndMatchGuid;
    }
}
