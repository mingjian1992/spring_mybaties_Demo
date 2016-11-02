package com.andaily.zhishifenzi.domain.player;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.commons.Photo;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

import java.util.Date;

/**
 * 队员
 *
 * @author Shengzhao Li
 */
public class Player extends AbstractDomain {

    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);
    //姓名
    private String fullName;
    //球衣号码
    private String number;
    //头像
    private Photo headPhoto;

    //球场位置
    private PlayerPosition position;
    //队长??
    private boolean captain;

    //加入球队日期
    private Date entryDate;
    //生日
    private Date birthday;

    private String phone;
    private String email;

    //个人描述
    private String description;

    public Player() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            playerRepository.savePlayer(this);
        } else {
            playerRepository.updatePlayer(this);
        }
    }

    public PlayerPosition position() {
        return position;
    }

    public Player position(PlayerPosition position) {
        this.position = position;
        return this;
    }

    public String fullName() {
        return fullName;
    }

    public Player fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public boolean captain() {
        return captain;
    }

    public Player captain(boolean captain) {
        this.captain = captain;
        return this;
    }


    public String phone() {
        return phone;
    }

    public String email() {
        return email;
    }

    public Date entryDate() {
        return entryDate;
    }

    public Player entryDate(Date entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public Player email(String email) {
        this.email = email;
        return this;
    }

    public Player phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date birthday() {
        return birthday;
    }

    public Player birthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "fullName='" + fullName + '\'' +
                ", position=" + position +
                ", captain=" + captain +
                '}';
    }

    public Player archiveMe() {
        this.archived(true);
        this.saveOrUpdate();
        //archive playerUser
        this.playerRepository.archivePlayerUser(this, true);
        LogHandler.createLog("Archive Player [" + this + "], archive PlayerUser too.", LogType.PLAYER);
        return this;
    }

    public String number() {
        return number;
    }

    public Player number(String number) {
        this.number = number;
        return this;
    }

    public Photo headPhoto() {
        return headPhoto;
    }

    public Player headPhoto(Photo headPhoto) {
        this.headPhoto = headPhoto;
        return this;
    }

    public String description() {
        return description;
    }

    public Player description(String description) {
        this.description = description;
        return this;
    }
}