package com.example.frame.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis plus 配置
 *
 * @author bobo
 * @date 2021/04/19
 */
@Configuration
@MapperScan("com.example.system.mapper")
public class MybatisPlusConfig {
    /**
     * 旧版 3.0.5版本之前
     * 分页插件的拦截器
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
