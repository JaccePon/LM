package com.stylefeng.guns.common.constant.dictmap;

import com.stylefeng.guns.common.constant.dictmap.base.AbstractDictMap;

/**
 * @描述:客户字典
 * @创建人: JaccePon
 * @创建日期: 2018年04月16日 10:20 AM
 */

public class CustomDict extends AbstractDictMap {


    @Override
    public void init() {

        put("userId","所属用户");
        put("name","客户名字");
        put("phone","电话");
        put("gender","性别");
        put("address","地址");
        put("height","身高");
        put("weight","体重");
        put("size","码数");
        put("advertisement","所属广告");
        put("operateBy","操作人");
        put("remark","备注");
        put("status","状态");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("userId","getUserName");
        putFieldWrapperMethodName("gender","getGenderName");
        putFieldWrapperMethodName("status","getStatusName");
    }
}
