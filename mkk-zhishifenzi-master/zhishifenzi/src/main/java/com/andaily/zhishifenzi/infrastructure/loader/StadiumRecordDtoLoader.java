package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.match.MatchDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumRecordDto;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;

import java.util.List;
import java.util.Map;

/**
 * 14-5-7 下午10:50
 *
 * @author Shengzhao Li
 */
public class StadiumRecordDtoLoader {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    private StadiumRecordDto recordDto;

    public StadiumRecordDtoLoader(StadiumRecordDto recordDto) {
        this.recordDto = recordDto;
    }

    public StadiumRecordDto load() {
        final Map<String, Object> map = recordDto.queryMap();
        recordDto.load(new PaginatedLoader<MatchDto>() {
            @Override
            public List<MatchDto> loadList() {
                List<Match> matches = matchRepository.findStadiumMatches(map);
                return MatchDto.toDtos(matches, true);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalStadiumMatches(map);
            }
        });

        Stadium stadium = stadiumRepository.findByGuid(recordDto.getGuid());
        return recordDto.setStadiumDto(new StadiumDto(stadium));
    }
}
