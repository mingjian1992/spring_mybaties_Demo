package com.andaily.zhishifenzi.domain.dto.club;

import com.andaily.zhishifenzi.domain.club.Club;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;

/**
 * 14-3-16 下午9:58
 *
 * @author Shengzhao Li
 */
public class ClubFormDto extends ClubDto {

    private String existName;

    public ClubFormDto() {
        super();
    }

    public ClubFormDto(Club club) {
        super(club);
        this.existName = club.name();
    }

    public ClubFormDto(String guid) {
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

    public Club toDomain() {
        return new Club().name(name).contact(contact)
                .remark(remark)
                .creator(SecurityUtils.currentUser());
    }
}
