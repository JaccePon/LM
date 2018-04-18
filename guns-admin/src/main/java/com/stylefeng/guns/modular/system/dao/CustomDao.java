package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.Custom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户的dao
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface CustomDao extends BaseMapper<Custom> {


    /**
     * 根据条件查询用户列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectCustoms( @Param("name") String name,@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("userId") Integer userId);

    /**
    * @描述:根据用户id和电话号码查询客户
    * @创建人: JaccePon
    * @创建时间: 4/16/2018 11:53 AM
    * @参数:
    * @返回值:
    * @版本:  V 1.0
    */
    List<Custom> getByPhoneORVX(@Param("phone")String phone,@Param("vxAccount")String vxAccount, @Param("userId")Integer userId);

}
