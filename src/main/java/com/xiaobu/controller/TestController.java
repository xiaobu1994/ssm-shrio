package com.xiaobu.controller;

import com.xiaobu.base.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/12/20 11:14
 * @description V1.0  验证 RedirectAttributes addAttribute和addFlashAttribute的区别
 *  addFlashAttribute 请求的重定向生效之前被临时存储（通常是在session)中，并且在重定向之后被立即移除.
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @GetMapping("/test4")
    public String test4(RedirectAttributes redirectAttributes) {
       int a=1/0;
        return "123";
    }

    @GetMapping("/test")
    public ModelAndView test(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name","admin");
        redirectAttributes.addFlashAttribute("message", "测试");
        //直接
        return ResultUtil.redirect("index");
    }

    @GetMapping("/index")
    @ResponseBody
    public ModelAndView index(ModelMap modelMap, HttpServletRequest request){
        System.out.println("request.getParameter(\"name\") = " + request.getParameter("name"));
        System.out.println("request.getParameter(\"message\") = " + request.getParameter("message"));
        System.out.println("message = " + modelMap.get("message"));
        System.out.println("name = " + modelMap.get("name"));
        System.out.println();
        return ResultUtil.redirect("last");
    }


    @GetMapping("/last")
    public String last(ModelMap modelMap, HttpServletRequest request){
        System.out.println("request.getParameter(\"name\") = " + request.getParameter("name"));
        System.out.println("request.getParameter(\"message\") = " + request.getParameter("message"));
        System.out.println("message = " + modelMap.get("message"));
        System.out.println("name = " + modelMap.get("name"));
        return "index";
    }

}
