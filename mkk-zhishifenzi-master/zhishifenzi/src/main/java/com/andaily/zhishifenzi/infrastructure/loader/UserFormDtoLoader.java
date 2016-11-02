package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.user.UserFormDto;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.user.User;
import com.andaily.zhishifenzi.domain.user.UserRepository;

/**
 * @author Shengzhao Li
 */
public class UserFormDtoLoader {

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);
    private String guid;

    public UserFormDtoLoader(String guid) {
        this.guid = guid;
    }

    public UserFormDto load() {
        UserFormDto userFormDto = new UserFormDto(guid);

        if (!userFormDto.isNewly()) {
            User user = userRepository.findByGuid(guid);
            userFormDto = new UserFormDto(user);
        }
        return userFormDto;
    }

}