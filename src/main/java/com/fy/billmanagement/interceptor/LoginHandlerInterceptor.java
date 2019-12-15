package com.fy.billmanagement.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(request.getSession().getId());
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null){//没有登录,返回登录页面
            request.setAttribute("msg","没有权限访问,请先登录");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
        //否则,已登录,放行
        return true;
    }
}
