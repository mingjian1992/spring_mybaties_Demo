package com.andaily.zhishifenzi.domain.log;


import com.andaily.zhishifenzi.domain.shared.Repository;

import java.util.List;
import java.util.Map;


/**
 * @author Shengzhao Li
 */

public interface LogRepository extends Repository {

    Log findByGuid(String guid);

    void saveLog(Log log);

    void updateLog(Log log);

    List<Log> findSystemLogs(Map<String, Object> map);

    int totalSystemLogs(Map<String, Object> map);

}