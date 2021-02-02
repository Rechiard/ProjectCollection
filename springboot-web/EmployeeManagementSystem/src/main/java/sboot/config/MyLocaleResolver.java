package sboot.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//自定义的国际化的地区解析器
//需要放在@bean中才可以生效
public class MyLocaleResolver implements LocaleResolver {

    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获得请求中的语言参数
        String language = httpServletRequest.getParameter("l");

        Locale locale = Locale.getDefault();//如果没有就使用默认的

        //利用StringUtils工具类判断language是否为空
        //如果请求的连接携带了国际化的参数
        if(!StringUtils.isEmpty(language)){
            //分割字符串
            String[] s = language.split("_");
            //语言，国家
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
