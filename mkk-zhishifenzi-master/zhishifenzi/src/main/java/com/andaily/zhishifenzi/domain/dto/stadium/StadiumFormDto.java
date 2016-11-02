package com.andaily.zhishifenzi.domain.dto.stadium;

import com.andaily.zhishifenzi.domain.stadium.Stadium;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;

/**
 * 14-3-17 下午10:57
 *
 * @author Shengzhao Li
 */
public class StadiumFormDto extends StadiumDto {


    private String existName;

    public StadiumFormDto() {
        super();
    }

    public StadiumFormDto(Stadium stadium) {
        super(stadium);
        this.existName = stadium.name();
    }

    public StadiumFormDto(String guid) {
        this.guid = guid;
    }

    public boolean isNewly() {
        return super.isNewly() || MatchUtils.isCreate(guid);
    }

    public String getExistName() {
        return existName;
    }

    public void setExistName(String existName) {
        this.existName = existName;
    }

    public Stadium toDomain() {
        return new Stadium().name(name)
                .remark(remark)
                .address(address)
                .contact(contact)
                .homeStadium(homeStadium);
    }
}
