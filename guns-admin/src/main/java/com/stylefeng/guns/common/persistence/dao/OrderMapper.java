package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.transfer.OrderDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JaccePon123
 * @since 2018-04-25
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Map<String,Object>> selectListByCondition(OrderDto orderDto);
}
