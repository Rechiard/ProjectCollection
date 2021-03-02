package com.example.swagger.config;


import com.example.swagger.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2     //开启Swagger2,Swagger是一个方便我们在调用API接口以及和前端沟通的图形化工具
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }


    /*
    * 用以下方法接管了系统默认的Swagger的Docket的实例，使用了自定义的Swagger配置，运行后访问localhost:8080/swagger-ui.html网页可以看见效果
    * */

    //配置了Swagger的Docket的Baen实例

    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");

        //获取项目的环境：
        //通过 environment.acceptsProfiles判断是否处于自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("hello")
                .enable(flag)   //enable判断是否启动Swagger，如果为false，则Swagger不能在浏览器中使用
                .select()

                //RequestHandlerSelectors，配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any:扫描全部
                //none:不扫描
                //withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation:扫描方法上的注解

                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                //过滤路径
                //.paths(PathSelectors.ant("/**"))
                .build();   //工厂模式
    }

    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact DEFAULT_CONTACT = new Contact("龚剑波", "http://www.rechiard.site", "616316004@qq.com");

        return new ApiInfo(
                "龚剑波的SwaggerAPI文档",
                "这是我的第一个Swagger配置制作",
                "1.0",
                "http://www.rechiard.site",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
