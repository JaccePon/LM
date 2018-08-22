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
    @RequestMapping("/to_add_blog")

   /**
   * @描述:跳转到写blog的页面
   * @创建人: JaccePon
   * @创建时间: 8/21/2018 6:54 PM
   * @参数: []
   * @返回值: java.lang.String
   * @版本:  V 1.0
   */ public String to_add_blog() {
        return PREFIX + "add_blog.html";
    }
}
