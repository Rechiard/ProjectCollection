package com.example.system.controller;

import com.example.frame.consts.SessionConst;
import com.example.frame.dto.ResponseResult;
import com.example.frame.shiro.util.ShiroUtils;
import com.example.frame.util.CaptchaUtils;
import com.example.system.service.UserInfoService;
import com.example.system.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @author bobo
 * @date 2021/04/11
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("login")
    @ResponseBody
    public ResponseResult login(@RequestParam("username")String userName,
                                @RequestParam("password")String password,
                                @RequestParam("captcha")String captcha,
                                HttpServletRequest request){
        log.info("loginId：{}，password：{}", userName, password);
        HttpSession session = request.getSession();
        String captchaRoot = session.getAttribute(SessionConst.KEY_CAPTCHA).toString().toLowerCase();
        String captchaUser = captcha.toLowerCase();
        if(!captchaRoot.equals(captchaUser)){
            return ResponseResult.error("验证码错误,请重新输入");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            //登录认证
            subject.login(token);
            log.info("登录成功：{}", userName);
            //保存用户信息系
            UserInfoVo userInfoVo = userInfoService.getByName(userName);
            ShiroUtils.setSessionAttribute(SessionConst.USER_INFO_SESSION,userInfoVo);
            String loginSuccessUrl = "/";
            return ResponseResult.success("登录成功",loginSuccessUrl);
        }catch(UnknownAccountException e){
            return ResponseResult.error("用户不存在");
        }catch(IncorrectCredentialsException e){
            return ResponseResult.error( "密码错误！");
        }catch(LockedAccountException e){
            return ResponseResult.error("登录失败，该用户被冻结！");
        }

    }

    @GetMapping("toLogin")
    public String toLogin(){
        System.out.println("跳转登录页面");
        return "system/login";
    }

    @GetMapping("Captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        // 不缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try{
            HttpSession session = request.getSession();
            CaptchaUtils util = new CaptchaUtils();
            StringBuffer code = new StringBuffer();
            BufferedImage image = util.getRandomCodeImage(code);
            session.removeAttribute(SessionConst.KEY_CAPTCHA);
            session.setAttribute(SessionConst.KEY_CAPTCHA,code.toString());

            ImageIO.write(image,"JPEG",response.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @GetMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "system/login";
    }

}
