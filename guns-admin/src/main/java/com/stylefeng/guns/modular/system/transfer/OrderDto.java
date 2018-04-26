package com.stylefeng.guns.modular.system.transfer;

import com.stylefeng.guns.common.persistence.model.Order;

/**
 * @描述:Order的扩展
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 11:27 AM
 */

public class OrderDto  extends Order{

    private String beginTime;
    private String endTime;
    private String customPhone;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }
}
