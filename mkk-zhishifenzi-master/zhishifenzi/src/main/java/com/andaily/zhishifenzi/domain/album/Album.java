package com.andaily.zhishifenzi.domain.album;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.match.Match;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 14-3-8 下午5:42
 * 相册
 *
 * @author Shengzhao Li
 */
public class Album extends AbstractDomain {

    private transient AlbumRepository albumRepository = BeanProvider.getBean(AlbumRepository.class);

    private User creator;
    //相册名称
    private String name;
    //描述
    private String description;
    //相册对应的比赛,可选
    private Match match;
    //照片
    private List<Photo> photos = new ArrayList<>();

    public Album() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            albumRepository.saveAlbum(this);
        } else {
            albumRepository.updateAlbum(this);
        }
    }

    public String name() {
        return name;
    }

    public Album name(String name) {
        this.name = name;
        return this;
    }

    public String description() {
        return description;
    }

    public Album description(String description) {
        this.description = description;
        return this;
    }

    public Match match() {
        return match;
    }

    public Album match(Match match) {
        this.match = match;
        return this;
    }

    public List<Photo> photos() {
        return photos;
    }


    public Album creator(User creator) {
        this.creator = creator;
        return this;
    }

    public User creator() {
        return creator;
    }

    public Album archiveMe() {
        this.archived(true);
        this.saveOrUpdate();
        //delete photos
        for (Photo photo : photos) {
            photo.delete();
        }
        LogHandler.createLog("Archive Album [" + this + "], delete the album photos too.", LogType.ALBUM);
        return this;
    }
}
