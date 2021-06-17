package com.example.frame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");

        //获取项目的环境：
        //通过 environment.acceptsProfiles判断是否处于自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("接口文档")
                .enable(flag)   //enable判断是否启动Swagger，如果为false，则Swagger不能在浏览器中使用
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tjnu"))
                .build();   //工厂模式
    }

    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact DEFAULT_CONTACT = new Contact("龚剑波", "http://www.rechiard.site", "616316004@qq.com");

        return new ApiInfo(
                "xx企业管理系统的SwaggerAPI文档",
                "龚剑波制作的接口文档，负责交付前端的接口文档页面",
                "1.0",
                "http://www.rechiard.site",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
