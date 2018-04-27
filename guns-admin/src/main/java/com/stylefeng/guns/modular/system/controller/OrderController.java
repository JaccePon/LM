package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.constant.state.PicPathEnum;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.CustomDao;
import com.stylefeng.guns.modular.system.transfer.OrderDto;
import com.stylefeng.guns.modular.system.warpper.CustomWarpper;
import com.stylefeng.guns.modular.system.warpper.OrderWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Order;
import com.stylefeng.guns.modular.system.service.IOrderService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-04-25 11:25:14
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private String PREFIX = "/system/order/";

    private  String DEFAULTPIC= PicPathEnum.ORDER.getPath()+"AAAAA-DEFAULT.jpg";

    @Autowired
    private IOrderService orderService;

    @Resource
    private CustomDao customDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/order_add")
    public String orderAdd() {
        return PREFIX + "order_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/order_update/{orderId}")
    public String orderUpdate(@PathVariable Integer orderId, Model model) {
        Order order = orderService.selectById(orderId);
        model.addAttribute("item",order);
        LogObjectHolder.me().set(order);
        return PREFIX + "order_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(OrderDto orderDto) {

        if (!ShiroKit.isAdmin()) {
            Integer userId = ShiroKit.getUser().getId();
            orderDto.setUserId(Long.parseLong(userId+""));
        }

        List<Map<String, Object>> orders = orderService.selectListByCondition(orderDto);

        return new OrderWarpper(orders).warp();
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Order order,String custom) {

        if(ToolUtil.isEmpty(custom)){
            throw new GunsException(BizExceptionEnum.ORDER_CUSTOM_NULL);
        }

        ShiroUser user = ShiroKit.getUser();
        if (ToolUtil.isEmpty(user)) {
            throw new GunsException(BizExceptionEnum.USER_NULL);
        }

        List<Map<String, Object>> list = customDao.selectCustoms( custom,null, null,user.getId());
        if(list==null){
            throw new GunsException(BizExceptionEnum.ORDER_CUSTOM_NOTEXIST);
        }else{
            if(list.size()==0){
                throw new GunsException(BizExceptionEnum.ORDER_CUSTOM_NOTEXIST);
            }else{
                if(list.size()>1){
                    throw new GunsException(BizExceptionEnum.ORDER_CUSTOM_MORE);
                }
            }
        }

        Map<String, Object> map = list.get(0);
        Long customid = (Long)map.get("id");

        Integer price = order.getPrice();

        if(ToolUtil.isEmpty(order.getPic())){
            order.setPic(DEFAULTPIC);
        }
        order.setCustomId(customid);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setPrice(price*100);
        order.setDisPrice(price*user.getDisCount());
        order.setUserId(Long.parseLong(""+user.getId()));
        order.setOpinion("-");

        orderService.insert(order);
        return super.SUCCESS_TIP;
    }

    /**
     * 单个修改，包括删除
     */
    @RequestMapping(value = "/updateOrder")
    @ResponseBody
    public Object delete( Order order) {
        orderService.updateById(order);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Order order,String custom) {
        orderService.updateById(order);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{orderId}")
    @ResponseBody
    public Object detail(@PathVariable("orderId") Integer orderId) {
        return orderService.selectById(orderId);
    }


    /**
     * 跳转到客户设置
     */
    @RequestMapping(value = "/order_custom")
    public String roleAssign() {
        ShiroUser user = ShiroKit.getUser();
        if (ToolUtil.isEmpty(user)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        return PREFIX + "/order_custom.html";
    }


}
