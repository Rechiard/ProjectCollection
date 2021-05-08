package com.example.springcloud.controller;

import com.example.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    //理解：消费者不应该有service层
    //RestTemplate。。。供我们直接调用就可以了，需要注册到Spring中
    //(url , 实体 : Map , class<T> responseType)
    @Autowired
    private RestTemplate restTemplate;  //提供多种便捷访问远程http服务的方法，简单的restful服务模板~


    //private static final String REST_URL_PREFIX = "http://localhost:8001";

    //用了Ribbon：我们这里的地址，是一个变量
    //Ribbon 和 Eureka 整合以后，客户端可以直接调用，不用关系IP地址和端口号~
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";   //通过服务名来访问（"springcloud-provider-dept"是Springcloud-provider-dept-8001项目的服务名，可以在其yaml配置中查看）



    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/add",boolean.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }



}
