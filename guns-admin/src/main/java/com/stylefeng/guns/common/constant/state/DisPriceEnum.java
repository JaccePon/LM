package com.stylefeng.guns.common.constant.state;

/**
 * @描述:折扣的枚举
 * @创建人: JaccePon
 * @创建日期: 2018年04月25日 5:53 PM
 */
public enum DisPriceEnum {


    JINJI(44, "金级"), YINJI(52, "银级"),YIJI(60, "一级"),ERJI(68, "二级"),SANJI(78, "三级"),SIJI(78, "四级");

    int disCount;
    String role;

    DisPriceEnum(int disCount, String role) {
        this.disCount = disCount;
        this.role = role;
    }

    public int getDisCount() {
        return disCount;
    }

    public void setDisCount(int disCount) {
        this.disCount = disCount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
