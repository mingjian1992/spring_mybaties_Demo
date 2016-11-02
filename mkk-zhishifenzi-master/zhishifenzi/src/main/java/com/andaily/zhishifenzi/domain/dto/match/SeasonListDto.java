package com.andaily.zhishifenzi.domain.dto.match;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import com.mysql.jdbc.StringUtils;

/**
 * 14-3-16 上午1:18
 *
 * @author Shengzhao Li
 */
public class SeasonListDto extends DefaultPaginated<SeasonDto> {

    private String seasonGuid;
    private SeasonDto formDto;

    public SeasonListDto() {
    }

    public boolean isAdd() {
        return StringUtils.isEmptyOrWhitespaceOnly(seasonGuid);
    }

    public String getSeasonGuid() {
        return seasonGuid;
    }

    public void setSeasonGuid(String seasonGuid) {
        this.seasonGuid = seasonGuid;
    }

    public SeasonDto getFormDto() {
        return formDto;
    }

    public void setFormDto(SeasonDto formDto) {
        this.formDto = formDto;
    }
}
