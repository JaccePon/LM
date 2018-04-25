package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JaccePon123
 * @since 2018-04-25
 */
@TableName("sys_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 对应的用户id
     */
    private Long userId;
    /**
     * 订单的衣服图片
     */
    private String pic;
    /**
     * 对应app的订单号
     */
    private String orderNum;
    /**
     * 衣服货号
     */
    private String code;
    /**
     * 原价
     */
    private Integer price;
    /**
     * 代理价
     */
    private Integer disPrice;
    /**
     * 关联的客户id
     */
    private Long customId;
    /**
     * 来源（1.特价 、2.微商城、3.普通）
     */
    private Integer source;
    /**
     * 收款情况（1.利润已收、2.利润未收、3.已收款、4.未收款、5.垫款）
     */
    private Integer gathering;
    /**
     * 状态（1.待货、2.已发货、3.断货、4.取消订单、5.成交、6.退货中、7.已退货、8.特殊处理、9.删除）
     */
    private Integer status;
    /**
     * 待货时间
     */
    private String waiting;
    /**
     * 快递费
     */
    private Integer post;
    /**
     * 上级意见
     */
    private String opinion;
    /**
     * 备注
     */
    private String remark;
    /**
     * 退款情况（1.无，2.已收退款，3.未收退款，4.退款完成）
     */
    private Integer refund;
    /**
     * 进度（0-100）
     */
    private Integer progress;
    /**
     * 提醒，用于后期扩展（微信提醒，短信提醒等）
     */
    private Integer remind;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDisPrice() {
        return disPrice;
    }

    public void setDisPrice(Integer disPrice) {
        this.disPrice = disPrice;
    }

    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getGathering() {
        return gathering;
    }

    public void setGathering(Integer gathering) {
        this.gathering = gathering;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWaiting() {
        return waiting;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRefund() {
        return refund;
    }

    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", userId=" + userId +
        ", pic=" + pic +
        ", orderNum=" + orderNum +
        ", code=" + code +
        ", price=" + price +
        ", disPrice=" + disPrice +
        ", customId=" + customId +
        ", source=" + source +
        ", gathering=" + gathering +
        ", status=" + status +
        ", waiting=" + waiting +
        ", post=" + post +
        ", opinion=" + opinion +
        ", remark=" + remark +
        ", refund=" + refund +
        ", progress=" + progress +
        ", remind=" + remind +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
