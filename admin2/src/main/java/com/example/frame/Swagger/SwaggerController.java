package com.example.frame.Swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接口文档
 *
 * @author bobo
 * @date 2021/04/30
 */
@Controller
@RequestMapping("/swagger")
public class SwaggerController {

    @GetMapping("init")
    public String init(){
        return "redirect:/swagger-ui.html";
    }
}
