package com.example.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {


    /*
        IRule
        AvailabilityFilteringRule: 会先过滤掉：访问很慢或者跳闸的服务~，剩下的进行轮询
        RoundRobinRule 轮询
        RandomRule 随机
        RetryRule 会先按照轮询获取服务，如果服务获取失败，则会在指定时间内进行重试

     */

    //配置负载均衡实现RestTemplate
    @LoadBalanced   //Ribbon的实现
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
