package sboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Controller
public class IndexController {

    @RequestMapping("/test")
    public String Test(Model model){
        model.addAttribute("users", Arrays.asList("bobo","rourou"));
        model.addAttribute("msg","<h1>hello,springboot</h1>");
        return "test";
    }
}
