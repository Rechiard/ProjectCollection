package sboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//自定义的web视图解析器
@Configuration
public class MyMvcConfig implements WebMvcConfigurer{

    //使用这个可以在我们跳转页面的时候让我们的网站的url显示的干净利落
    //例如我们登陆之后访问的是主页面，一般情况url会自动在后面添加   主路径/设置的RequestMapper的路径/参数数值
    //如果我们在自定义的视图中添加了我们的跳转页面，就不会出现上述情况
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //主界面的视图添加，但是也是因为这样，所以我们必须设置拦截器，不然我们直接访问的话就可以不需要登录访问到后端
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //自定义的国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/css/**","/js/**","/img/**");

    }
}
