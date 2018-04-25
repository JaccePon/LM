package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.Order;
import com.stylefeng.guns.common.persistence.dao.OrderMapper;
import com.stylefeng.guns.modular.system.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
