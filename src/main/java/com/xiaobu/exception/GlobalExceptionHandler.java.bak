package com.xiaobu.exception;

import com.xiaobu.base.entity.vo.ResponseVO;
import com.xiaobu.base.enums.ResponseStatus;
import com.xiaobu.base.util.CommonConst;
import com.xiaobu.base.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/28 9:16
 * @description V1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView handleExption(HttpServletRequest request, Throwable e){
        log.info(request.getRequestURI() + " 方法异常:" + e.toString());
        System.out.println("e = " + e);
        if(e instanceof UnauthorizedException){
            return ResultUtil.view("error/403");
        }
        return ResultUtil.view("error/500");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO handle(HttpServletRequest request,Throwable e) {
        log.info(request.getRequestURI() + " 方法异常:" + e.toString());
        if (e instanceof CustomException) {
            return ResultUtil.error(e.getMessage());
        }
        System.out.println("e = " + e);
        if (e instanceof UndeclaredThrowableException) {
            e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
        }
        ResponseStatus responseStatus = ResponseStatus.getResponseStatus(e.getMessage());
        if (responseStatus != null) {
            log.error(responseStatus.getMessage());
            return ResultUtil.error(responseStatus.getCode(), responseStatus.getMessage());
        }
        e.printStackTrace(); // 打印异常栈
        return ResultUtil.error(CommonConst.DEFAULT_ERROR_CODE, e.getMessage());
    }
}
