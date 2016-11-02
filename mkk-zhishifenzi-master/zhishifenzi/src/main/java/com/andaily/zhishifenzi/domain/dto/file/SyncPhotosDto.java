package com.andaily.zhishifenzi.domain.dto.file;

import com.andaily.zhishifenzi.domain.dto.album.PhotoDto;
import com.andaily.zhishifenzi.domain.shared.paginated.DefaultPaginated;

import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class SyncPhotosDto extends DefaultPaginated<PhotoDto> {

    private boolean displayType;

    public SyncPhotosDto() {
    }

    @Override
    public Map<String, Object> queryMap() {
        final Map<String, Object> map = super.queryMap();
        map.put("displayType", displayType ? "ok" : null);
        return map;
    }

    public boolean getDisplayType() {
        return displayType;
    }

    public void setDisplayType(boolean displayType) {
        this.displayType = displayType;
    }
}