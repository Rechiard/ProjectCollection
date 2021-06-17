package com.example.frame.config;

import com.example.frame.consts.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义的拦截器
 * 需要进行登录操作才能访问其他页面
 */
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        System.out.println("此路是我开，此树是我栽，要想从此过，留下买路财");
        System.out.println(request.getRequestURI());

        if(session.getAttribute(SessionConst.USER_INFO_SESSION) == null){
            response.sendRedirect(request.getContextPath()+"/toLogin");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
