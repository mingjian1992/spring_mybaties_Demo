package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchListDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;

import java.util.List;
import java.util.Map;

/**
 * 14-3-31 下午11:12
 *
 * @author Shengzhao Li
 */
public class MatchListDtoLoader {


    protected transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    protected transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);
    protected transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    protected transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    protected MatchListDto listDto;

    public MatchListDtoLoader() {
    }

    public MatchListDtoLoader(MatchListDto listDto) {
        this.listDto = listDto;
    }

    public MatchListDto load() {
        loadSeasons();
        loadOpponents();
        loadStadiums();

        final Map<String, Object> map = listDto.queryMap();
        return listDto.load(new PaginatedLoader<MatchDto>() {
            @Override
            public List<MatchDto> loadList() {
                List<Match> matches = matchRepository.findListMatches(map);
                return MatchDto.toDtos(matches, true);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalListMatches(map);
            }
        });
    }

    private void loadStadiums() {
        List<Stadium> stadiums = stadiumRepository.findAllStadiums();
        listDto.setStadiums(StadiumDto.toDtos(stadiums));
    }

    private void loadOpponents() {
        List<Club> clubs = clubRepository.findAllClubs();
        listDto.setClubs(ClubDto.toDtos(clubs));
    }

    private void loadSeasons() {
        List<Season> seasonList = seasonRepository.findAllSeasons();
        listDto.setSeasons(SeasonDto.toDtos(seasonList));

    }
}
