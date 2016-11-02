package com.andaily.zhishifenzi.domain.dto.stadium;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 14-3-17 下午10:07
 *
 * @author Shengzhao Li
 */
public class StadiumListDto extends DefaultPaginated<StadiumDto> {

    private String name;

    public StadiumListDto() {
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
