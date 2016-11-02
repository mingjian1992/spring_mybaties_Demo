package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.club.ClubRepository;
import com.andaily.zhishifenzi.domain.dto.club.ClubFormDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

/**
 * 14-3-16 下午10:33
 *
 * @author Shengzhao Li
 */
public class ClubFormDtoPersister {

    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);
    private ClubFormDto formDto;

    public ClubFormDtoPersister(ClubFormDto formDto) {
        this.formDto = formDto;
    }

    public void persist() {
        if (formDto.isNewly()) {
            saveClub();
        } else {
            updateClub();
        }
    }

    private void updateClub() {
        Club club = clubRepository.findByGuid(formDto.getGuid());
        club.name(formDto.getName())
                .contact(formDto.getContact())
                .remark(formDto.getRemark())
                .saveOrUpdate();
        LogHandler.createLog("Update club [" + club + "]", LogType.CLUB);
    }

    private void saveClub() {
        Club club = formDto.toDomain();
        club.saveOrUpdate();
        LogHandler.createLog("Create club [" + club + "]", LogType.CLUB);
    }
}
