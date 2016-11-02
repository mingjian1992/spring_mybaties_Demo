package com.andaily.zhishifenzi.domain.club;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.domain.user.User;

/**
 * 球队(也就是每一个对手)
 *
 * @author Shengzhao Li
 */
public class Club extends AbstractDomain {

    private transient ClubRepository clubRepository = BeanProvider.getBean(ClubRepository.class);

    private User creator;
    //球队名称
    private String name;
    //联系人
    private String contact;
    //备注
    private String remark;

    public Club() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            clubRepository.saveClub(this);
        } else {
            clubRepository.updateClub(this);
        }
    }

    public User creator() {
        return creator;
    }

    public Club creator(User creator) {
        this.creator = creator;
        return this;
    }

    public String name() {
        return name;
    }

    public Club name(String name) {
        this.name = name;
        return this;
    }

    public String contact() {
        return contact;
    }

    public Club contact(String contact) {
        this.contact = contact;
        return this;
    }

    public String remark() {
        return remark;
    }

    public Club remark(String remark) {
        this.remark = remark;
        return this;
    }

    public Club archiveMe() {
        this.archived(true);
        this.saveOrUpdate();
        LogHandler.createLog("Archive Club [" + this + "]", LogType.CLUB);
        return this;
    }
}