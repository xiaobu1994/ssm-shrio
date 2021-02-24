package com.xiaobu.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2019/1/14 14:12
 * @description V1.0
 */
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {

  /*  private List<Integer> errorCodeList = Arrays.asList(401,403,404,405, 500, 501);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/error/" + response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }*/

}
