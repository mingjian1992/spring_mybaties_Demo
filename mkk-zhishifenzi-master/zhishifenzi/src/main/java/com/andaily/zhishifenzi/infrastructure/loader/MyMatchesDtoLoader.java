package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.dto.match.MyMatchesDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;

import java.util.List;
import java.util.Map;

/**
 * 14-4-19 下午5:15
 *
 * @author Shengzhao Li
 */
public class MyMatchesDtoLoader {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);

    private MyMatchesDto matchesDto;

    public MyMatchesDtoLoader(MyMatchesDto matchesDto) {
        this.matchesDto = matchesDto;
    }

    public MyMatchesDto load() {
        loadSeasons();

        final Map<String, Object> map = matchesDto.queryMap();
        return matchesDto.load(new PaginatedLoader<MatchDto>() {
            @Override
            public List<MatchDto> loadList() {
                List<Match> matches = matchRepository.findMyMatches(map);
                return MatchDto.toDtos(matches, true);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalMyMatches(map);
            }
        });
    }

    private void loadSeasons() {
        List<Season> seasonList = seasonRepository.findAllSeasons();
        matchesDto.setSeasons(SeasonDto.toDtos(seasonList));

    }
}
