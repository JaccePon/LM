package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.annotion.BussinessLog;
import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.constant.dictmap.CustomDict;
import com.stylefeng.guns.common.constant.dictmap.UserDict;
import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.constant.state.CustomStatus;
import com.stylefeng.guns.common.constant.state.ManagerStatus;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.Custom;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.CustomDao;
import com.stylefeng.guns.modular.system.factory.UserFactory;
import com.stylefeng.guns.modular.system.transfer.CustomDto;
import com.stylefeng.guns.modular.system.transfer.UserDto;
import com.stylefeng.guns.modular.system.warpper.CustomWarpper;
import com.stylefeng.guns.modular.system.warpper.UserWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/cus")
public class CustomMgrController extends BaseController {

    private static String PREFIX = "/system/custom/";

    @Resource
    private GunsProperties gunsProperties;

    @Resource
    private CustomDao customDao;

    @Resource
    private UserMapper userMapper;

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "custom.html";
    }

    /**
    * @描述:
    * @创建人: JaccePon
    * @创建时间: 1/22/2018 2:46 PM
    * @参数:
    * @返回值:
    * @版本:  V 1.0
    */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {
        Integer userId = ShiroKit.getUser().getId();
        if (ShiroKit.isAdmin()) {
            userId=null;
        }

            List<Map<String, Object>> custom = customDao.selectCustoms( name,beginTime, endTime,userId);
            return new CustomWarpper(custom).warp();
    }


    /**
     * 跳转到查看客户列表的页面
     */
    @RequestMapping("/cus_add")
    public String addView() {
        return PREFIX + "custom_add.html";
    }


    @RequestMapping("/add")
    @BussinessLog(value = "添加客户", key = "custom", dict = CustomDict.class)
    @Permission
    @ResponseBody
    public Tip add(@Valid Custom custom, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        Integer userId = ShiroKit.getUser().getId();
        User user = userMapper.selectById(userId);

        // 判断该用户添加的这个客户是否重复
        List<Custom> theCustomDto = customDao.getByPhoneORVX(custom.getPhone(),custom.getVxAccount(),userId);
        if (theCustomDto != null && theCustomDto.size()>0) {
            throw new GunsException(BizExceptionEnum.CUSTOM_ALREADY_REG);
        }

        // 完善账号信息
        custom.setStatus(CustomStatus.OK.getCode());
        custom.setCreateTime(new Date());
        custom.setUpdateTime(new Date());
        custom.setUserId(userId);
        custom.setOperateBy(user.getName());

       Integer num= this.customDao.insert(custom);
        return SUCCESS_TIP;
    }


    /**
     * 跳转到编辑客户页面
     */
    @Permission
    @RequestMapping("/update/{customId}")
    public String userEdit(@PathVariable Integer customId, Model model) {
        if (ToolUtil.isEmpty(customId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Custom custom1 = new Custom();
        custom1.setId(customId);
        Custom custom = customDao.selectOne(custom1);

        model.addAttribute(custom);
        LogObjectHolder.me().set(custom);
        return PREFIX + "custom_edit.html";
    }


    /**
     * 修改客户
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改客户信息", key = "custom", dict = CustomDict.class)
    @ResponseBody
    public Tip edit(@Valid Custom custom, BindingResult result) throws NoPermissionException {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        Integer userId = ShiroKit.getUser().getId();
        User user = userMapper.selectById(userId);


        //判断是否存在

        List<Custom> theCustomDto = customDao.getByPhoneORVX(custom.getPhone(),custom.getVxAccount(),userId);
        if (theCustomDto != null && theCustomDto.size()>0) {
            if(theCustomDto.size()>1){
                throw new GunsException(BizExceptionEnum.CUSTOM_ALREADY_REG);
            }else{
               if( theCustomDto.get(0).getId()!=custom.getId()){
                   throw new GunsException(BizExceptionEnum.CUSTOM_ALREADY_REG);
               }
            }
        }

        //修改客户信息
        custom.setOperateBy(user.getName());
        custom.setUpdateTime(new Date());

        customDao.updateById(custom);
        return SUCCESS_TIP;

    }


    /**
     * 删除客户（逻辑删除）
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除客户", key = "custom", dict = CustomDict.class)
    @Permission
    @ResponseBody
    public Tip delete(@RequestParam Integer customId) {
        if (ToolUtil.isEmpty(customId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Custom custom=new Custom();
        custom.setStatus(CustomStatus.DELETED.getCode());
        custom.setId(customId);
        customDao.updateById(custom);

        return SUCCESS_TIP;
    }




    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {
        String pictureName = UUID.randomUUID().toString() + ".jpg";
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;
    }

    /**
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(Integer userId) {
        if (ShiroKit.isAdmin()) {
            return;
        }
        List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
        User user = this.userMapper.selectById(userId);
        Integer deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new GunsException(BizExceptionEnum.NO_PERMITION);
        }

    }
}
