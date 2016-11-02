package com.andaily.zhishifenzi.domain.dto.stadium;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.stadium.Stadium;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-17 下午9:52
 *
 * @author Shengzhao Li
 */
public class StadiumDto extends AbstractDto {

    //球场名称
    protected String name;
    //地址
    protected String address;
    //是否为主场
    protected boolean homeStadium;
    //联系人信息
    protected String contact;
    //备注
    protected String remark;

    public StadiumDto() {
    }

    public StadiumDto(Stadium stadium) {
        super(stadium.guid());
        this.name = stadium.name();
        this.address = stadium.address();

        this.homeStadium = stadium.homeStadium();
        this.contact = stadium.contact();
        this.remark = stadium.remark();
    }

    public String getName() {
        return name;
    }

    public String getNameIncludeHome() {
        if (homeStadium) {
            return name + " (主)";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHomeStadium() {
        return homeStadium;
    }

    public void setHomeStadium(boolean homeStadium) {
        this.homeStadium = homeStadium;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRemark() {
        return remark;
    }

    public String getShortRemark() {
        if (remark != null && remark.length() > 20) {
            return remark.substring(0, 20) + "...";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static List<StadiumDto> toDtos(List<Stadium> stadiums) {
        List<StadiumDto> dtos = new ArrayList<>(stadiums.size());
        for (Stadium stadium : stadiums) {
            dtos.add(new StadiumDto(stadium));
        }
        return dtos;
    }
}
