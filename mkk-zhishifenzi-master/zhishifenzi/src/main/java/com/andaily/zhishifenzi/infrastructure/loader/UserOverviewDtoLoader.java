package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.user.UserDto;
import com.andaily.zhishifenzi.domain.dto.user.UserOverviewDto;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.paginated.PaginatedLoader;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRepository;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class UserOverviewDtoLoader {

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);
    private UserOverviewDto userOverviewDto;

    public UserOverviewDtoLoader(UserOverviewDto userOverviewDto) {
        this.userOverviewDto = userOverviewDto;
    }

    public UserOverviewDto load() {

        final Map<String, Object> map = userOverviewDto.queryMap();
        return userOverviewDto.load(new PaginatedLoader<UserDto>() {
            @Override
            public List<UserDto> loadList() {
                List<User> users = userRepository.findOverviewUsers(map);
                return UserDto.toDtos(users);
            }

            @Override
            public int loadTotalSize() {
                return userRepository.totalOverviewUsers(map);
            }
        });
    }

}