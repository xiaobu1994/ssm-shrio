package com.xiaobu.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2019/2/21 11:56
 * @description V1.0 异常处理控制器
 */
@Controller
public class MyErrorController extends BasicErrorController {
    public MyErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }




    @RequestMapping(produces = "text/html", value = "/401")
    public ModelAndView errorHtml401(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg", "自定义错误信息");
        return new ModelAndView("error/401", model);
    }

    @RequestMapping(produces = "text/html", value = "/403")
    public ModelAndView errorHtml403(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg", "自定义错误信息");
        return new ModelAndView("error/403", model);
    }

    /**
     *     * 定义404的ModelAndView
     *     * @param request
     *     * @param response
     *     * @return
     */
    @RequestMapping(produces = "text/html", value = "/404")
    public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg", "自定义错误信息");
        return new ModelAndView("error/404", model);
    }

    @RequestMapping(produces = "text/html", value = "/405")
    public ModelAndView errorHtml405(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg", "自定义错误信息");
        return new ModelAndView("error/405", model);
    }



    @RequestMapping(produces = "text/html", value = "/500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg", "自定义错误信息");
        return new ModelAndView("error/500", model);
    }

    /**
     *     * 定义500的错误JSON信息
     *     * @param request
     *     * @return
     */
    @RequestMapping(value = "/500")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        HttpStatus status = getStatus(request);
        return new ResponseEntity<Map<String, Object>>(body, status);
    }


}
