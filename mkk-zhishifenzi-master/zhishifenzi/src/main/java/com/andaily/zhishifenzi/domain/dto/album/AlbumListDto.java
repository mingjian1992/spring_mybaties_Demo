package com.andaily.zhishifenzi.domain.dto.album;

import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 14-3-22 下午3:38
 *
 * @author Shengzhao Li
 */
public class AlbumListDto extends DefaultPaginated<AlbumDto> {

    private String name;

    public AlbumListDto() {
        setPerPageSize(12);
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
