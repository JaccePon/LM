package com.stylefeng.guns.modular.system.controller.blog;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @描述:博客控制层
 * @创建人: JaccePon
 * @创建日期: 2018年08月14日 4:30 PM
 */

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {

    private String PREFIX = "/blog/";

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "index.html";
    }
}
