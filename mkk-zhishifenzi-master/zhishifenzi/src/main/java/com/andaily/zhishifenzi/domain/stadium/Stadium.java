package com.andaily.zhishifenzi.domain.stadium;

import com.andaily.zhishifenzi.domain.AbstractDomain;
import com.andaily.zhishifenzi.domain.log.LogHandler;
import com.andaily.zhishifenzi.domain.log.LogType;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;

/**
 * 比赛球场
 *
 * @author Shengzhao Li
 */
public class Stadium extends AbstractDomain {

    private transient StadiumRepository stadiumRepository = BeanProvider.getBean(StadiumRepository.class);

    //球场名称
    private String name;
    //地址
    private String address;
    //是否为主场
    private boolean homeStadium;
    //联系人信息
    private String contact;
    //备注
    private String remark;

    public Stadium() {
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            stadiumRepository.saveStadium(this);
        } else {
            stadiumRepository.updateStadium(this);
        }
    }

    public String name() {
        return name;
    }

    public Stadium name(String name) {
        this.name = name;
        return this;
    }

    public String address() {
        return address;
    }

    public Stadium address(String address) {
        this.address = address;
        return this;
    }

    public boolean homeStadium() {
        return homeStadium;
    }

    public Stadium homeStadium(boolean homeStadium) {
        this.homeStadium = homeStadium;
        return this;
    }

    public String contact() {
        return contact;
    }

    public Stadium contact(String contact) {
        this.contact = contact;
        return this;
    }

    public String remark() {
        return remark;
    }

    public Stadium remark(String remark) {
        this.remark = remark;
        return this;
    }

    public Stadium archiveMe() {
        this.archived(true);
        this.saveOrUpdate();
        LogHandler.createLog("Archive Stadium [" + this + "]", LogType.STADIUM);
        return this;
    }
}