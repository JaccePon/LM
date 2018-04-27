package com.stylefeng.guns.common.constant.enums;

/**
 * @描述:收款情况
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 10:32 AM
 */
public enum GatheringEnum {

    //1.利润已收、2.利润未收、3.已收款、4.未收款、5.垫款
    LIRUNYISHOU(1, "利润已收"),LIRUNWEIHSOU(2, "<font style='color:#F56C6C;'>利润未收</font>"),YISHOUKUAN(3, "已收款"),WEISHOUKUAN(4, "<font style='color:#F56C6C;'>未收款</font>"),DIANKUAN(5, "<font style='color:#F56C6C;'>垫款</font>");

    int code;
    String message;

    GatheringEnum(int code, String message) {
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
            for (GatheringEnum ms : GatheringEnum.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
