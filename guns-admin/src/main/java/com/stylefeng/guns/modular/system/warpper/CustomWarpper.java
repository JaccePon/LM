package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 客户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class CustomWarpper extends BaseControllerWarpper {

    public CustomWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("gender")));
        map.put("address", ConstantFactory.me().getCustomMsgTooLong( map.get("address")+""));
        map.put("remark", ConstantFactory.me().getCustomMsgTooLong( map.get("remark")+""));
    }

}
