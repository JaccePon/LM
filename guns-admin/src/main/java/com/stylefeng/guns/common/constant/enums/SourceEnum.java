package com.stylefeng.guns.common.constant.enums;

/**
 * @描述:来源
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 10:32 AM
 */
public enum SourceEnum {

    //1.特价 、2.微商城、3.普通
    TEJIA(1, "特价"),WEISHANGCHENG(2, "微商城"),PUTONG(3, "普通");

    int code;
    String message;

    SourceEnum(int code, String message) {
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
            for (SourceEnum ms : SourceEnum.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
