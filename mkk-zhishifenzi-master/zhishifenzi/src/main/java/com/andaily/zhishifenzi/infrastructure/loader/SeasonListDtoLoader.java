package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.domain.dto.match.SeasonListDto;
import com.andaily.zhishifenzi.domain.match.Season;
import com.andaily.zhishifenzi.domain.match.SeasonRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;

import java.util.List;
import java.util.Map;

/**
 * 14-3-16 上午2:11
 *
 * @author Shengzhao Li
 */
public class SeasonListDtoLoader {

    private transient SeasonRepository seasonRepository = BeanProvider.getBean(SeasonRepository.class);
    private SeasonListDto listDto;

    public SeasonListDtoLoader(SeasonListDto listDto) {
        this.listDto = listDto;
    }

    public SeasonListDto load() {
        loadForm();

        final Map<String, Object> map = listDto.queryMap();
        return listDto.load(new PaginatedLoader<SeasonDto>() {
            @Override
            public List<SeasonDto> loadList() {
                List<Season> seasons = seasonRepository.findListSeasons(map);
                return SeasonDto.toDtos(seasons);
            }

            @Override
            public int loadTotalSize() {
                return seasonRepository.totalListSeasons(map);
            }
        });
    }

    private void loadForm() {
        if (listDto.isAdd()) {
            listDto.setFormDto(new SeasonDto());
        } else {
            Season season = seasonRepository.findByGuid(listDto.getSeasonGuid());
            listDto.setFormDto(new SeasonDto(season));
        }
    }
}
