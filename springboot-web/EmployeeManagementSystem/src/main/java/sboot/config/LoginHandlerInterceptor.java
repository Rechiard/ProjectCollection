package sboot.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器，用于登录，防止别人直接访问登录后的主页面
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //return true就是放行，return false就是不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录成功之后，应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");

        //如果没有用户session则返回主页面
        if(loginUser == null){
            request.setAttribute("msg","没有权限，请先登录");
            //返回到主页面
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
