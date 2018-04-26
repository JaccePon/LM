package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.common.persistence.model.Order;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.transfer.OrderDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JaccePon123
 * @since 2018-04-25
 */
public interface IOrderService extends IService<Order> {

    List<Map<String,Object>> selectListByCondition(OrderDto orderDto);
}
