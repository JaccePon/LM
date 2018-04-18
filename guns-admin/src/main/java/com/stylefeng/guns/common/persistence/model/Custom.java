package com.stylefeng.guns.common.persistence.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @描述:客户
 * @创建人: JaccePon
 * @创建日期: 2018年04月16日 11:43 AM
 */

public class Custom {
      /* `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
            `user_id` bigint(20) NOT NULL COMMENT '对应的用户id',
            `name` varchar(45) NOT NULL COMMENT '客户名称',
            `vxAccount` varchar(45) NOT NULL COMMENT '微信号',
            `gender` int(1) NOT NULL DEFAULT '0' COMMENT '性别，2 为 女 ，1 为 男',
            `phone` varchar(30) DEFAULT NULL COMMENT '联系电话',
            `address` varchar(500) DEFAULT NULL COMMENT '地址',
            `height` varchar(20) DEFAULT NULL COMMENT '身高，单位是cm',
            `weight` varchar(20) DEFAULT NULL COMMENT '体重，单位是kg',
            `size` varchar(20) DEFAULT NULL COMMENT '码数',
            `advertisement` int(10) NOT NULL COMMENT '对应的广告平台id',
            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
            `operate_by` varchar(20) NOT NULL COMMENT '操作人的名称',
            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
            `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态，0 为 正常 ，1 为删除。',*/

    private Integer id;
    private Integer userId;
    private String vxAccount;
    private String name;
    private Integer gender;
    private String phone;
    private String address;

    private String height;
    private String weight;
    private String size;
    private Integer advertisement;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String operateBy;
    private String remark;
    private Integer status;

    public String getVxAccount() {
        return vxAccount;
    }

    public void setVxAccount(String vxAccount) {
        this.vxAccount = vxAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Integer advertisement) {
        this.advertisement = advertisement;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
