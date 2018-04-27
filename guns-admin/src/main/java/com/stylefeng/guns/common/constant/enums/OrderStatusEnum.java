package com.stylefeng.guns.common.constant.enums;

/**
 * @描述:订单状态
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 10:32 AM
 */
public enum OrderStatusEnum {

    //1.待货、2.已发货、3.断货、4.取消订单、5.成交、6.退货中、7.已退货、8.特殊处理、9.删除
    DAIHUO(1, "<font style='color:#E6A23C;'>待货</font>"),YIFAHUO(2, "<font style='color:#409EFF;'>已发货</font>"),DUANHUO(3, "<font style='color:#409EFF;'>断货</font>"),QUXIAODINGDAN(4, "<font style='color:#409EFF;'>取消订单</font>"),CHENGJIAO(5, "<font style='color:#67C23A;'>成交</font>")
    ,TUIHUOZHONG(6, "<font style='color:#E6A23C;'>退货中</font>"),YITUIHUO(7, "<font style='color:#909399;'>已退货</font>"),TESHUCHULI(8, "<font style='color:#F56C6C;'>特殊处理</font>"),SHANCHU(9, "删除")
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
