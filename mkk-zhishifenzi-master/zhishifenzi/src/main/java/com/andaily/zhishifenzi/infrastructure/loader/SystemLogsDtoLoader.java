package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.log.LogDto;
import com.andaily.zhishifenzi.domain.dto.log.SystemLogsDto;
import com.andaily.zhishifenzi.domain.dto.user.UserDto;
import com.andaily.zhishifenzi.domain.log.Log;
import com.andaily.zhishifenzi.domain.log.LogRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRepository;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class SystemLogsDtoLoader {

    private transient LogRepository logRepository = BeanProvider.getBean(LogRepository.class);
    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private SystemLogsDto systemLogsDto;

    public SystemLogsDtoLoader(SystemLogsDto systemLogsDto) {
        this.systemLogsDto = systemLogsDto;
    }

    public SystemLogsDto load() {
        loadUsers();

        final Map<String, Object> map = systemLogsDto.queryMap();
        return systemLogsDto.load(new PaginatedLoader<LogDto>() {
            @Override
            public List<LogDto> loadList() {
                List<Log> logs = logRepository.findSystemLogs(map);
                return LogDto.toDtos(logs);
            }

            @Override
            public int loadTotalSize() {
                return logRepository.totalSystemLogs(map);
            }
        });
    }

    private void loadUsers() {
        final List<User> users = userRepository.findSimpleUsers();
        systemLogsDto.setUsers(UserDto.toDtos(users));
    }
}