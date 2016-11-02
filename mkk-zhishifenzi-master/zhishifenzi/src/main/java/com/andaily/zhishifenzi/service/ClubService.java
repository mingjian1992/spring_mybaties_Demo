package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubListDto;
import com.andaily.zhishifenzi.domain.dto.club.ClubRecordDto;

/**
 * 14-3-16 下午9:18
 *
 * @author Shengzhao Li
 */
public interface ClubService {

    ClubListDto loadClubListDto(ClubListDto listDto);

    ClubFormDto loadClubFormDto(String guid);

    Club loadClubByName(String name);

    void persistClubFormDto(ClubFormDto formDto);

    void archiveClub(String guid);

    ClubRecordDto loadClubRecordDto(ClubRecordDto recordDto);
}
