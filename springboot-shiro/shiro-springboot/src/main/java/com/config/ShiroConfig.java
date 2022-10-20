package com.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


//我们创建三大组件的时候需要倒着创建
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean    第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //关联DefaultWebSecurityManager,设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器
        /**
         * 以下代码就是拦截器的实现，下列关键字就是一些拦截的字符，点击源码也是可以看见的
         * anon：无需认证就可以访问
         * authc：必须认证才能访问
         * user：必须拥有记住我功能才能访问
         * perms:拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        //filterMap.put("/user/add","authc");
        //filterMap.put("/user/update","authc");

        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    //DefaultWebSecurityManager     第二步
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联userRealm
        securityManager.setRealm(userRealm);

        return securityManager;
    }


    //创建realm对象，需要自定义   第一步
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


}
