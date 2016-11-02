package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeListDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.match.MatchNotice;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;

import java.util.List;
import java.util.Map;

/**
 * 14-3-21 下午9:35
 *
 * @author Shengzhao Li
 */
public class MatchNoticeListDtoLoader {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);
    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    private MatchNoticeListDto listDto;

    public MatchNoticeListDtoLoader(MatchNoticeListDto listDto) {
        this.listDto = listDto;
    }

    public MatchNoticeListDto load() {
        loadStadiums();
        loadOpponents();

        final Map<String, Object> map = listDto.queryMap();
        return listDto.load(new PaginatedLoader<MatchNoticeDto>() {
            @Override
            public List<MatchNoticeDto> loadList() {
                List<MatchNotice> notices = matchRepository.findListMatchNotices(map);
                return MatchNoticeDto.toDtos(notices);
            }

            @Override
            public int loadTotalSize() {
                return matchRepository.totalListMatchNotices(map);
            }
        });

    }

    private void loadOpponents() {
        List<Club> clubs = clubRepository.findAllClubs();
        listDto.setClubs(ClubDto.toDtos(clubs));
    }

    private void loadStadiums() {
        List<Stadium> stadiums = stadiumRepository.findAllStadiums();
        listDto.setStadiums(StadiumDto.toDtos(stadiums));
    }
}
