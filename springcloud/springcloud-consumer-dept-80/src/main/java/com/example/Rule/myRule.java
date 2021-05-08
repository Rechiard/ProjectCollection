package com.example.Rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义的Ribbon类
 * 用于自定义负载均衡的方式以及一些其他操作
 */
@Configuration
public class myRule {

    @Bean
    public IRule Rule(){
        return new myRandomRule();  //默认是轮询，现在是使用自定义的负载均衡的方式，用于测试我们自定义调用服务的方式，测试15次之后就会报500错误
    }

}
