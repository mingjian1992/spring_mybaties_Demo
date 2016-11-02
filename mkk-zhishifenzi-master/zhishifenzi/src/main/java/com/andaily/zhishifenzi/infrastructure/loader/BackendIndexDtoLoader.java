package com.andaily.zhishifenzi.infrastructure.loader;

import com.andaily.zhishifenzi.domain.dto.BackendIndexDto;
import com.andaily.zhishifenzi.domain.dto.IndexDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeDto;
import com.andaily.zhishifenzi.domain.match.MatchNotice;
import com.andaily.zhishifenzi.domain.match.MatchRepository;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import com.andaily.zhishifenzi.domain.user.User;

import java.util.List;

/**
 * 14-5-21 下午10:20
 *
 * @author Shengzhao Li
 */
public class BackendIndexDtoLoader {

    private transient MatchRepository matchRepository = BeanProvider.getBean(MatchRepository.class);

    public BackendIndexDtoLoader() {
    }

    public BackendIndexDto load() {
        User user = SecurityUtils.currentUser();
        BackendIndexDto backendIndexDto = new BackendIndexDto(user);

        loadMatchNotices(backendIndexDto);
        return backendIndexDto;
    }

    private void loadMatchNotices(BackendIndexDto indexDto) {
        List<MatchNotice> notices = matchRepository.findPendingNotices();
        indexDto.setNotices(MatchNoticeDto.toDtos(notices));
    }
}
