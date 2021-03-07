package com.example.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//zookeeper:服务注册与发现

@Component  //使用了Dubbo后尽量不要用service注解
@Service  //可以被扫描到，在项目中一启动就会自动注册到注册中心
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "《java》";
    }
}
