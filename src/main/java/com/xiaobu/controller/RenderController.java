package com.xiaobu.controller;


import com.xiaobu.base.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转类
 * @author xiaobu
 */
@Controller
public class RenderController {

   /**
    * @author xiaobu
    * @date 2018/12/18 10:48
    * @return org.springframework.web.servlet.ModelAndView
    * @descprition   表示index或者空字符串进入 index页面
    * @version 1.0
    */
    @GetMapping(value ={"/index","/"})
    public ModelAndView index() {
        return ResultUtil.view("index");
    }

    @GetMapping("/users")
    public ModelAndView zuser() {
        return ResultUtil.view("user/list");
    }

    @GetMapping("/resources")
    public ModelAndView resources() {
        return ResultUtil.view("resources/list");
    }

    @GetMapping("/roles")
    public ModelAndView roles() {
        return ResultUtil.view("role/list");
    }

}
