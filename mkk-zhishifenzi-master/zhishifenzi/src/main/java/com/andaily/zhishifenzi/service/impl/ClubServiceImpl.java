package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.club.ClubDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubListDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubRecordDto;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.infrastructure.loader.ClubRecordDtoLoader;
import com.andaily.zhishifenzi.infrastructure.persist.ClubFormDtoPersister;
import com.andaily.zhishifenzi.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 14-3-16 下午9:18
 *
 * @author Shengzhao Li
 */
@Service("clubService")
public class ClubServiceImpl implements ClubService {


    @Autowired
    private ClubRepository clubRepository;

    @Override
    public ClubListDto loadClubListDto(ClubListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        return listDto.load(new PaginatedLoader<ClubDto>() {
            @Override
            public List<ClubDto> loadList() {
                List<Club> clubs = clubRepository.findListClubs(map);
                return ClubDto.toDtos(clubs);
            }

            @Override
            public int loadTotalSize() {
                return clubRepository.totalListClubs(map);
            }
        });
    }

    @Override
    public ClubFormDto loadClubFormDto(String guid) {
        ClubFormDto formDto = new ClubFormDto(guid);
        if (!formDto.isNewly()) {
            Club club = clubRepository.findByGuid(guid);
            formDto = new ClubFormDto(club);
        }
        return formDto;
    }

    @Override
    public Club loadClubByName(String name) {
        return clubRepository.findByName(name);
    }

    @Override
    public void persistClubFormDto(ClubFormDto formDto) {
        ClubFormDtoPersister persister = new ClubFormDtoPersister(formDto);
        persister.persist();
    }

    @Override
    public void archiveClub(String guid) {
        Club club = clubRepository.findByGuid(guid);
        club.archiveMe();
    }

    @Override
    public ClubRecordDto loadClubRecordDto(ClubRecordDto recordDto) {
        ClubRecordDtoLoader loader = new ClubRecordDtoLoader(recordDto);
        return loader.load();
    }
}
