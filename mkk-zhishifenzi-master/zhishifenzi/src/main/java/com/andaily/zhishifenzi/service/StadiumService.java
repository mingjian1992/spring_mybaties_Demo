package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.stadium.StadiumFormDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumListDto;
import com.andaily.zhishifenzi.domain.dto.stadium.StadiumRecordDto;
import com.andaily.zhishifenzi.domain.stadium.Stadium;

/**
 * 14-3-17 下午10:10
 *
 * @author Shengzhao Li
 */
public interface StadiumService {

    StadiumListDto loadStadiumListDto(StadiumListDto listDto);

    StadiumFormDto loadStadiumFormDto(String guid);

    Stadium loadStadiumByName(String name);

    void persistStadiumFormDto(StadiumFormDto formDto);

    void archiveStadium(String guid);

    StadiumRecordDto loadStadiumRecordDto(StadiumRecordDto recordDto);
}
