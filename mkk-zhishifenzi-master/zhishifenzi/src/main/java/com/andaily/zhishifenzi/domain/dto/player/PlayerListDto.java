package com.andaily.zhishifenzi.domain.dto.player;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.domain.shared.grouper.GroupResult;
import com.andaily.zhishifenzi.domain.shared.grouper.impl.PlayerListDtoGrouper;
import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 14-3-19 下午9:19
 *
 * @author Shengzhao Li
 */
public class PlayerListDto extends DefaultPaginated<PlayerDto> {

    private String name;

    public PlayerListDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("name", StringUtils.isEmpty(name) ? null : "%" + name + "%");
        return map;
    }

    public void afterLoad() {
        this.totalSize = list.size();
    }

    public List<GroupResult<String, PlayerDto>> getGroupResults() {
        PlayerListDtoGrouper grouper = new PlayerListDtoGrouper(this.list);
        return grouper.group().getGroupResults();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
