package com.andaily.zhishifenzi.infrastructure.mybatis;

import com.andaily.zhishifenzi.domain.log.Log;
import com.andaily.zhishifenzi.domain.log.LogRepository;
import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRepository;
import com.andaily.zhishifenzi.domain.user.UserRole;
import com.andaily.zhishifenzi.infrastructure.AbstractRepositoryTest;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * 14-3-25 下午9:30
 *
 * @author Shengzhao Li
 */
public class LogRepositoryMyBatisTest extends AbstractRepositoryTest {


    @Autowired
    private LogRepository logRepository;


    @Autowired
    private UserRepository userRepository;


    @Test
    public void findByGuid() {
        String guid = GuidGenerator.generate();
        Log log = logRepository.findByGuid(guid);
        assertNull(log);

        User user = new User("user", "123").userRole(UserRole.CAPTAIN);
        userRepository.saveUser(user);
        user = userRepository.findByGuid(user.guid());

        Log log1 = new Log(user, DateUtils.now(), "test");
        logRepository.saveLog(log1);

        log = logRepository.findByGuid(log1.guid());
        assertNotNull(log);
        assertNotNull(log.content());
        assertNotNull(log.who());
        assertNotNull(log.time());

    }

    @Test
    public void updateLog() {
        User user = new User("user", "123").userRole(UserRole.CAPTAIN);
        userRepository.saveUser(user);
        user = userRepository.findByGuid(user.guid());

        Log log1 = new Log(user, DateUtils.now(), "test");
        logRepository.saveLog(log1);

        Log log = logRepository.findByGuid(log1.guid());
        assertNotNull(log);

        log.content("okok");
        logRepository.updateLog(log);
        log = logRepository.findByGuid(log.guid());
        assertNotNull(log);
        assertEquals(log.content(), "okok");

    }

}
