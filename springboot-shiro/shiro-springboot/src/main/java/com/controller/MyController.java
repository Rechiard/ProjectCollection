package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        /*
        当我们执行登录操作的时候（即调用login方法），会自动跳转到/config/UserRealm.java的认证方法中
        然后我们就可以对认证里的方法进行具体需求的操作了
         */
        try{
            subject.login(token);   //执行登录的方法，没有异常就OK了
            return "index";
        }catch(UnknownAccountException e){  //用户名不存在
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch(IncorrectCredentialsException e){  //用户名不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
