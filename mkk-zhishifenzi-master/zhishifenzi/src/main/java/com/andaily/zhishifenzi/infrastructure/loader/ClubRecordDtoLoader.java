package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubRecordDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;

import java.util.List;
import java.util.Map;

/**
 * 14-5-1 下午12:24
 *
 * @author Shengzhao Li
 */
public class ClubRecordDtoLoader {

    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private ClubRecordDto recordDto;

    public ClubRecordDtoLoader(ClubRecordDto recordDto) {
        this.recordDto = recordDto;
    }

    public ClubRecordDto load() {
        loadClubDto();
        loadStatisticsData();

        final Map<String, Object> map = recordDto.queryMap();
        return recordDto.load(new PaginatedLoader<MatchDto>() {
            @Override
            public List<MatchDto> loadList() {
                List<Match> matches = matchRepository.findClubMatches(map);
                return MatchDto.toDtos(matches, true);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalClubMatches(map);
            }
        });
    }

    private void loadStatisticsData() {
        int win = 0;
        int eq = 0;
        int fail = 0;

        List<Integer> scoreRanges = matchRepository.findScoreRanges(recordDto.getGuid());
        for (Integer scoreRange : scoreRanges) {
            if (scoreRange > 0) {
                win++;
            } else if (scoreRange == 0) {
                eq++;
            } else {
                fail++;
            }
        }

        recordDto.setWinCount(win)
                .setEqCount(eq)
                .setFailCount(fail);
    }

    private void loadClubDto() {
        Club club = clubRepository.findByGuid(recordDto.getGuid());
        recordDto.setClubDto(new ClubDto(club));
    }
}
