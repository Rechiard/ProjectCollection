package sboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @Controller和@RestController的区别：
 * 当我们利用@RestController的时候，实际上是@RequestBody+@Controller,而@RequestBody的目的是将java返回的数据转化为json数据并且返回，所以不会跳转页面
 * 而我们使用@Controller的时候，我们是可以用String类型的方法return一个字符串实现跳转页面的，如果想要向页面返回数据但不是跳转页面的时候，就需要在方法上面加入@RequestBody
 */

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session)
    {
        //具体业务
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            //告诉用户，你登陆失败了！
            model.addAttribute("msg","用户名或者密码错误！");
            return "index";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        //移除session
        session.invalidate();
        return "redirect:/index.html";
    }
}
