package com.stylefeng.guns.common.constant.enums;

/**
 * @描述:订单状态
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 10:32 AM
 */
public enum OrderStatusEnum {

    //1.待货、2.已发货、3.断货、4.取消订单、5.成交、6.退货中、7.已退货、8.特殊处理、9.删除
    DAIHUO(1, "待货"),YIFAHUO(2, "已发货"),DUANHUO(3, "断货"),QUXIAODINGDAN(4, "取消订单"),CHENGJIAO(5, "成交")
    ,TUIHUOZHONG(6, "退货中"),YITUIHUO(7, "已退货"),TESHUCHULI(8, "特殊处理"),SHANCHU(9, "删除")
    ;

    int code;
    String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        } else {
            for (OrderStatusEnum ms : OrderStatusEnum.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
