package com.example.system.controller;

import com.example.frame.dto.layuimini.HomeInfo;
import com.example.frame.dto.layuimini.LogoInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bobo
 * @date 2021/04/16
 */
@Controller
public class IndexController {

    @GetMapping ("toWelcome")
    public String home(){
        return "system/welcome";
    }

    @PostMapping("initData")
    @ResponseBody
    public Map initData(){
        Map<String, Object> map = new HashMap<>(16);
        map.put("homeInfo", new HomeInfo().setTitle("首页").setHref("/welcome"));
        map.put("logoInfo", new LogoInfo().setTitle("Admin-Shiro").setImage("/static/layuimini/images/logo.png"));
        return map;
    }

    @GetMapping("/")
    public String index() {
        return "system/index";
    }

}
