package com.stylefeng.guns.common.constant.enums;

/**
 * @描述:退款情况
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 10:32 AM
 */
public enum RefundEnum {

    //1.无，2.已收退款，3.未收退款，4.退款完成
    WU(1, "无"),YISHOUTUIKUAN(2, "<font style='color:#E6A23C;'>已收退款</font>"),WEISHOUTUIKUAN(3, "<font style='color:#F56C6C;'>未收退款</font>"),TUIKUANWANCHENG(4, "退款完成");

    int code;
    String message;

    RefundEnum(int code, String message) {
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
            for (RefundEnum ms : RefundEnum.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
