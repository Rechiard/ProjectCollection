package sboot.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展SpringMvc   dispatchservlet
//如果使用了自定义的Configuration，就不能添加@EnableWebMvc注解，因为这玩意就是导入了一个类：DelegatingWebMvcConfiguration：从容器中获取所有的WebMvcConfig
//在WebMvcAutoConfiguration类中标出了@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)，而DelegatingWebMvcConfiguration是继承WebMvcConfigurationSupport类的
//所以在使用WebMvcAutoConfiguration做出自定义的操作的时候，不能加入@EnableWebMvc，否则导致WebMvcAutoConfiguration类失效
//如果 想diy一些定制化的功能，只要写这个组件，然后将它交给springboot，springboot就会帮我们自动装配
@Configuration
public class MyMvcConfig implements WebMvcConfigurer{

    // public interface viewResolver 实现了视图解析器接口的类，我们就可以把它看做视图解析器

    @Bean
    public ViewResolver myResolver(){
        return new MyViewResolver();
    }

    /*
    * 如果我们要扩展SpringMvc，官方建议我们这样去做！
    * */

    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sboot").setViewName("test");
    }
}
