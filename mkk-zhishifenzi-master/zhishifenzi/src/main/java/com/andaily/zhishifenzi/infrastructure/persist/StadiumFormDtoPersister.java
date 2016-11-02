package com.andaily.zhishifenzi.infrastructure.persist;

import com.andaily.zhishifenzi.domain.dto.stadium.StadiumFormDto;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.domain.stadium.StadiumRepository;

/**
 * 14-3-18 下午10:16
 *
 * @author Shengzhao Li
 */
public class StadiumFormDtoPersister {

    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);
    private StadiumFormDto formDto;

    public StadiumFormDtoPersister(StadiumFormDto formDto) {
        this.formDto = formDto;
    }

    public void persist() {
        if (formDto.isNewly()) {
            createStadium();
        } else {
            updateStadium();
        }
    }

    private void updateStadium() {
        Stadium stadium = stadiumRepository.findByGuid(formDto.getGuid());
        stadium.contact(formDto.getContact())
                .remark(formDto.getRemark())
                .name(formDto.getName())
                .address(formDto.getAddress())
                .homeStadium(formDto.isHomeStadium())
                .saveOrUpdate();
        LogHandler.createLog("Update Stadium [" + stadium + "]", LogType.STADIUM);
    }

    private void createStadium() {
        Stadium stadium = formDto.toDomain();
        stadium.saveOrUpdate();
        LogHandler.createLog("Create Stadium [" + stadium + "]", LogType.STADIUM);
    }
}
