package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo
 * @Date: 2017/11/30
 * @Time: 11:35
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        if (httpServletRequest.getRequestURI().equals("/user/login") ||httpServletRequest.getRequestURI().equals("/user/login2") || httpServletRequest.getRequestURI().equals("/login") || !httpServletRequest.getRequestURI().contains("/user")) {
//            return true;
//        }
//        //验证session是否存在
//        Object object = httpServletRequest.getSession().getAttribute("_session_user");
//        if (object==null){
//            httpServletResponse.sendRedirect("/login");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}