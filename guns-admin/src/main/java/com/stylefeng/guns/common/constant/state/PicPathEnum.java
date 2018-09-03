package com.stylefeng.guns.common.constant.state;

import java.io.File;

/**
 * @描述:图片路径的中间部分
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 10:32 AM
 */
public enum PicPathEnum {

    USER(0, "user"+ File.separator),ORDER(1, "order"+ File.separator),BLOG(2,"blog"+ File.separator),TEMP(99, "temp"+ File.separator);

    int code;
    String path;

    PicPathEnum(int code, String path) {
        this.code = code;
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return TEMP.getPath();
        } else {
            for (PicPathEnum ms : PicPathEnum.values()) {
                if (ms.getCode() == value) {
                    return ms.getPath();
                }
            }
            return TEMP.getPath();
        }
    }
}
