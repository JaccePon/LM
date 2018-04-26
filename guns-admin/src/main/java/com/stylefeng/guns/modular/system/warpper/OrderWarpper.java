package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.enums.GatheringEnum;
import com.stylefeng.guns.common.constant.enums.OrderStatusEnum;
import com.stylefeng.guns.common.constant.enums.RefundEnum;
import com.stylefeng.guns.common.constant.enums.SourceEnum;
import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @描述:
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 1:58 PM
 */

public class OrderWarpper  extends BaseControllerWarpper {

    public OrderWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("pic", ConstantFactory.me().getPicShow( map.get("pic")+""));
        map.put("price", ConstantFactory.me().getPriceShow( (Integer)map.get("price")));
        map.put("disPrice", ConstantFactory.me().getPriceShow( (Integer)map.get("disPrice")));
        map.put("source", SourceEnum.valueOf((Integer) map.get("source")));
        map.put("gathering", GatheringEnum.valueOf((Integer) map.get("gathering")));
        map.put("status", OrderStatusEnum.valueOf((Integer) map.get("status")));
        map.put("refund", RefundEnum.valueOf((Integer) map.get("refund")));
        map.put("remark", ConstantFactory.me().getCustomMsgTooLong( map.get("remark")+""));
        map.put("createTime", ConstantFactory.me().getDateString( (Date)map.get("createTime")));
        map.put("progress", map.get("progress")+" %");

    }
}
