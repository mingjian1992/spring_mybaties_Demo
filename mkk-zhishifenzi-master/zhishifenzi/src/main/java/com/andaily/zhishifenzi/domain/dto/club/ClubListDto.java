package com.andaily.zhishifenzi.domain.dto.club;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 14-3-16 下午9:17
 *
 * @author Shengzhao Li
 */
public class ClubListDto extends DefaultPaginated<ClubDto> {

    private String name;

    public ClubListDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("name", StringUtils.isEmpty(name) ? null : "%" + name + "%");
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
