package sboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//在templates目录下的所有页面，只能通过controller来跳转！
//这个需要模板引擎的支持！thymeleaf
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello(){
        return "hello！";
    }

}
