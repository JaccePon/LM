package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.constant.state.CustomStatus;
import com.stylefeng.guns.common.constant.state.PicPathEnum;
import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.Custom;
import com.stylefeng.guns.common.persistence.model.Order;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.JasonParseLmUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.CustomDao;
import com.stylefeng.guns.modular.system.service.IOrderService;
import com.stylefeng.guns.modular.system.transfer.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @描述:主页的controller
 * @创建人: JaccePon
 * @创建日期: 2018年05月15日 6:20 PM
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    private  String DEFAULTPIC= PicPathEnum.ORDER.getPath()+"AAAAA-DEFAULT.jpg";

    @Resource
    private GunsProperties gunsProperties;

    @Resource
    private UserMapper userMapper;
    @Resource
    private CustomDao customDao;

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/addOrder.json")
    @ResponseBody
    public  HashMap<String, Object>  addOrder(String jsonStr){
        HashMap<String, Object> resultMap = new HashMap<>();
        ArrayList<HashMap<String,String>> errorList = new ArrayList<>();
        int successSize=0;
        int errorSize=0;
        ShiroUser cuser = ShiroKit.getUser();
        if(!cuser.getName().equals("管理员")){
            resultMap.put("code","-1");
            resultMap.put("message","非管理员操作！！！");
            return resultMap;
        }
        if(ToolUtil.isNotEmpty(jsonStr)){
            Map map = JasonParseLmUtil.stringToCollect(jsonStr);

            if(map!=null&&map.size()>0){

                Object aaData = map.get("aaData");

                if(ToolUtil.isNotEmpty(aaData)){

                    try {
                        List<HashMap> list = JasonParseLmUtil.toList(aaData.toString(), HashMap.class);

                        if(list!=null&&list.size()>0){
                            resultMap.put("size",list.size()+"");

                            for(int i=0;i<list.size();i++) {
                                HashMap mapVo = list.get(i);

                                //判断订单号是否存在，如果存在就不插入
                                String sequence = mapVo.get("sequence").toString();
                                OrderDto orderDto = new OrderDto();
                                orderDto.setOrderNum(sequence);
                                List<Map<String, Object>> orderList = orderService.selectListByCondition(orderDto);
                                if (orderList != null && orderList.size() > 0) {
                                    mapPut("该订单已存在，请确认信息", mapVo, errorList);
                                    errorSize=errorSize+1;
                                } else {

                                Object guideBy = mapVo.get("guideBy");//代理
                                if (ToolUtil.isNotEmpty(guideBy)) {
                                    Map guide = JasonParseLmUtil.stringToCollect(guideBy.toString());
                                    Object name = guide.get("name");
                                    if (ToolUtil.isNotEmpty(name)) {
                                        String nameStr = name.toString();
                                        String[] split = nameStr.split("-");
                                        if (split.length == 2) {
                                            String str = split[1];
                                            if (str.equals("庞燕虹")) {
                                                str = "庞丹红";
                                            }

                                            //查询用户
                                            HashMap<String, Object> con = new HashMap<>();
                                            con.put("name", str);
                                            List<User> users = userMapper.selectByMap(con);
                                            if (users != null && users.size() == 1) {

                                                User user = users.get(0);
                                                Integer userId = user.getId();
                                                String vxAccount = "默认-" + DateUtil.getAllTime() + "-" + i;

                                                Object customer = mapVo.get("customer");
                                                if (ToolUtil.isNotEmpty(customer)) {
                                                    try {
                                                        Map custom = JasonParseLmUtil.stringToCollect(customer.toString());
                                                        String mobile = custom.get("mobile").toString();
                                                        //客户信息
                                                        List<Custom> theCustomDto = customDao.getByPhoneORVX(mobile, vxAccount, userId);

                                                        Order order = new Order();
                                                        if (theCustomDto != null && theCustomDto.size() > 0) {
                                                            Custom custom1 = theCustomDto.get(0);
                                                            order.setCustomId(Long.parseLong(custom1.getId() + ""));
                                                        } else {
                                                            Custom newCustom = new Custom();
                                                            newCustom.setStatus(CustomStatus.OK.getCode());
                                                            newCustom.setCreateTime(new Date());
                                                            newCustom.setUpdateTime(new Date());
                                                            newCustom.setUserId(userId);
                                                            newCustom.setOperateBy(cuser.getName());
                                                            newCustom.setName(custom.get("name").toString());
                                                            newCustom.setPhone(mobile);
                                                            Object address = mapVo.get("address");
                                                            Map addressMap = JasonParseLmUtil.stringToCollect(address.toString());
                                                            newCustom.setAddress(addressMap.get("districtAddress").toString());
                                                            newCustom.setSize(mapVo.get("size").toString());
                                                            newCustom.setAdvertisement(0);
                                                            newCustom.setVxAccount(vxAccount);
                                                            newCustom.setGender(2);

                                                            newCustom.setHeight("-");
                                                            newCustom.setWeight("-");
                                                            newCustom.setRemark("-");
                                                            customDao.insertAndReturnId(newCustom);
                                                            order.setCustomId(Long.parseLong(newCustom.getId() + ""));
                                                        }

                                                        //订单
                                                        order.setUserId(Long.parseLong(userId + ""));
                                                        //http://www.weixingshishang.cn/image/commodity/9ef5f47d30b842fe975f166fb8404670.jpg
                                                        String imageUrl = mapVo.get("imageUrl").toString();
                                                        String s = imgDownLoad("http://www.weixingshishang.cn" + imageUrl);
                                                        order.setPic(s);
                                                        order.setOrderNum(sequence);
                                                        Object commodity = mapVo.get("commodity");
                                                        Map commoditymap = JasonParseLmUtil.stringToCollect(commodity.toString());
                                                        order.setCode(commoditymap.get("code").toString().split(" ")[0]);
                                                        Object discountedPrice = mapVo.get("discountedPrice");
                                                        double price = Double.parseDouble(discountedPrice.toString());
                                                        order.setPrice((new Double(price*100)).intValue());

                                                        Object agentPriceLv3 = mapVo.get("agentPriceLv3");
                                                        Object agentPriceLv2 = mapVo.get("agentPriceLv2");
                                                        Object agentPriceLv1 = mapVo.get("agentPriceLv1");
                                                        int discount=0;
                                                        if(str.equals("庞丹红")||str.equals("燕虹")){
                                                            discount=60;
                                                        }else{
                                                        if(new Double(agentPriceLv3.toString()).intValue()==0){
                                                            if(new Double(agentPriceLv2.toString()).intValue()==0){
                                                                if(new Double(agentPriceLv1.toString()).intValue()==0){
                                                                    discount=52;
                                                                }else{
                                                                    discount=60;
                                                                }
                                                            }else{
                                                                discount=68;
                                                            }
                                                        }else{
                                                            discount=78;
                                                        }
                                                        }
                                                        order.setDisPrice((new Double(price*discount)).intValue());
                                                        String orderSource = mapVo.get("orderSource").toString();
                                                        if(orderSource.equals("0")){
                                                            order.setSource(3);
                                                            order.setGathering(3);
                                                        }else{
                                                            if(orderSource.equals("1")){
                                                                order.setSource(2);
                                                                order.setGathering(2);
                                                            }
                                                        }
                                                        order.setWaiting("-");
                                                        order.setOpinion("-");
                                                        order.setRemark("-");
                                                        order.setStatus(1);
                                                        order.setCreateTime(new Date());
                                                        order.setUpdateTime(new Date());
                                                        orderService.insert(order);
                                                        successSize=successSize+1;
                                                    } catch (Exception e) {
                                                        mapPut("新增订单出现异常", mapVo, errorList);
                                                        errorSize=errorSize+1;
                                                    }

                                                } else {
                                                    mapPut("客户信息为空", mapVo, errorList);
                                                    errorSize=errorSize+1;
                                                }

                                            } else {
                                                mapPut("代理名称查询结果不是唯一", mapVo, errorList);
                                                errorSize=errorSize+1;
                                            }
                                        } else {
                                            mapPut("代理名称有误", mapVo, errorList);
                                            errorSize=errorSize+1;
                                        }
                                    } else {
                                        mapPut("代理信息为空", mapVo, errorList);
                                        errorSize=errorSize+1;
                                    }
                                } else {
                                    mapPut("代理信息为空", mapVo, errorList);
                                    errorSize=errorSize+1;
                                }
                            }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }else{
                    resultMap.put("code","-1");
                    resultMap.put("message","订单信息为空");
                }
            }else{
                resultMap.put("code","-1");
                resultMap.put("message","数据集为空");
            }

        }else{
            resultMap.put("code","-1");
            resultMap.put("message","字符串为空");
        }

        resultMap.put("successSize",successSize+"");
        resultMap.put("errorSize",errorSize+"");
        if(errorList.size()>0){
            resultMap.put("code","0");
            resultMap.put("resultList",errorList);
            resultMap.put("message","有错误的信息");
        }else{
            resultMap.put("code","1");
            resultMap.put("message","成功插入 "+successSize+ " 条数据");
        }

        return resultMap;
    }


    public  void mapPut(String reason,Map mapVo,List errorList){
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("reason",reason);
        errorMap.put("data",JasonParseLmUtil.toJSONString(mapVo));
        errorList.add(errorMap);
    }

    public  String imgDownLoad(String imageUrl){
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // 创建文件

            String fileSavePath = gunsProperties.getFileUploadPath();
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
            String pictureName=fileSavePath+"order"+fileName;

            FileOutputStream out = new FileOutputStream(pictureName);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            is.close();
            out.close();

        return "order"+fileName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULTPIC;
    }

}
