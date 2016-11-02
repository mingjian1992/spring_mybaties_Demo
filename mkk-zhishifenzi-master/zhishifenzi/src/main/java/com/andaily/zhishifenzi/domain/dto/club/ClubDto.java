package com.andaily.zhishifenzi.domain.dto.club;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.dto.AbstractDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-16 下午9:14
 *
 * @author Shengzhao Li
 */
public class ClubDto extends AbstractDto {

    protected String name;
    protected String contact;
    protected String remark;

    public ClubDto() {
    }

    public ClubDto(Club club) {
        super(club.guid());
        this.name = club.name();
        this.contact = club.contact();
        this.remark = club.remark();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (remark != null && remark.length() > 30) {
            return remark.substring(0, 30) + "...";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static List<ClubDto> toDtos(List<Club> clubs) {
        List<ClubDto> dtos = new ArrayList<>(clubs.size());
        for (Club club : clubs) {
            dtos.add(new ClubDto(club));
        }
        return dtos;
    }
}
