package com.andaily.zhishifenzi.domain.club;

import com.andaily.zhishifenzi.domain.shared.Repository;

import java.util.List;
import java.util.Map;

/**
 * 14-3-8 下午9:44
 *
 * @author Shengzhao Li
 */
public interface ClubRepository extends Repository {

    Club findByGuid(String guid);

    void saveClub(Club club);

    void updateClub(Club club);

    List<Club> findListClubs(Map<String, Object> map);

    int totalListClubs(Map<String, Object> map);

    Club findByName(String name);

    List<Club> findAllClubs();
}
