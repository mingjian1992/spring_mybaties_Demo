package com.andaily.zhishifenzi.domain.stadium;

import com.andaily.zhishifenzi.domain.shared.Repository;

import java.util.List;
import java.util.Map;

/**
 * 14-3-8 下午10:18
 *
 * @author Shengzhao Li
 */
public interface StadiumRepository extends Repository {

    Stadium findByGuid(String guid);

    void saveStadium(Stadium stadium);

    void updateStadium(Stadium stadium);

    List<Stadium> findListStadiums(Map<String, Object> map);

    int totalListStadiums(Map<String, Object> map);

    Stadium findByName(String name);

    List<Stadium> findAllStadiums();
}
