package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.Order;
import com.stylefeng.guns.common.persistence.dao.OrderMapper;
import com.stylefeng.guns.modular.system.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.transfer.OrderDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JaccePon123
 * @since 2018-04-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Map<String, Object>> selectListByCondition(OrderDto orderDto) {
        List<Map<String, Object>> orders = orderMapper.selectListByCondition(orderDto);
        return orders;
    }
}
