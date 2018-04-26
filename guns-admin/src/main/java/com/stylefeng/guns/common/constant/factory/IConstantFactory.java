package com.stylefeng.guns.common.constant.factory;

import com.stylefeng.guns.common.persistence.model.Dict;

import java.util.Date;
import java.util.List;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);

    /*
    * 获取客户的状态
    * */
    String getCustomStatusName(Integer status);

    /*
    * 处理过长的信息
    * */
    String getCustomMsgTooLong(String msg);

    /**
    * @描述:获取图片
    * @创建人: JaccePon
    * @创建时间: 4/26/2018 2:03 PM
    * @参数:
    * @返回值:
    * @版本:  V 1.0
    */String getPicShow(String picPath);

    /**
    * @描述:获取展示的价格
    * @创建人: JaccePon
    * @创建时间: 4/26/2018 2:50 PM
    * @参数:
    * @返回值:
    * @版本:  V 1.0
    */String getPriceShow(Integer price);

    /**
    * @描述:修改时间，去了时分秒
    * @创建人: JaccePon
    * @创建时间: 4/26/2018 3:39 PM
    * @参数:
    * @返回值:
    * @版本:  V 1.0
    */String getDateString(Date createTime);
}
