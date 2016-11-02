package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.dto.stadium.StadiumDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumFormDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumListDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumRecordDto;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;
import com.andaily.zhishifenzi.infrastructure.loader.StadiumRecordDtoLoader;
import com.andaily.zhishifenzi.infrastructure.persist.StadiumFormDtoPersister;
import com.andaily.zhishifenzi.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 14-3-17 下午10:11
 *
 * @author Shengzhao Li
 */
@Service("stadiumService")
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Override
    public StadiumListDto loadStadiumListDto(StadiumListDto listDto) {
        final Map<String, Object> map = listDto.queryMap();
        return listDto.load(new PaginatedLoader<StadiumDto>() {
            @Override
            public List<StadiumDto> loadList() {
                List<Stadium> stadiums = stadiumRepository.findListStadiums(map);
                return StadiumDto.toDtos(stadiums);
            }

            @Override
            public int loadTotalSize() {
                return stadiumRepository.totalListStadiums(map);
            }
        });
    }

    @Override
    public StadiumFormDto loadStadiumFormDto(String guid) {
        StadiumFormDto formDto = new StadiumFormDto(guid);
        if (!formDto.isNewly()) {
            Stadium stadium = stadiumRepository.findByGuid(guid);
            formDto = new StadiumFormDto(stadium);
        }
        return formDto;
    }

    @Override
    public Stadium loadStadiumByName(String name) {
        return stadiumRepository.findByName(name);
    }

    @Override
    public void persistStadiumFormDto(StadiumFormDto formDto) {
        StadiumFormDtoPersister persister = new StadiumFormDtoPersister(formDto);
        persister.persist();
    }

    @Override
    public void archiveStadium(String guid) {
        Stadium stadium = stadiumRepository.findByGuid(guid);
        stadium.archiveMe();
    }

    @Override
    public StadiumRecordDto loadStadiumRecordDto(StadiumRecordDto recordDto) {
        StadiumRecordDtoLoader loader = new StadiumRecordDtoLoader(recordDto);
        return loader.load();
    }
}
