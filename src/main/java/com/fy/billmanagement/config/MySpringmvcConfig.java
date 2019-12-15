package com.fy.billmanagement.config;

import com.fy.billmanagement.component.MyErrorAttribute;
import com.fy.billmanagement.component.MyLocaleResolver;
import com.fy.billmanagement.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringmvcConfig {

    //让WebMvcConfigurer被spring容器管理,由于内部自定义了视图解析器,springboot默认的mvc配置类WebMvcAutoConfiguration会检测到自定义控制器并使用它
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //添加视图控制
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login.html");
                registry.addViewController("/index.html").setViewName("main/login.html");
                registry.addViewController("/main.html").setViewName("main/index.html");
            }
            //添加拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**")//拦截所有路径
                        .excludePathPatterns("/","/index.html","/login","/css/*","/img/*","/js/*","/public/*");//排除登录页面和静态资源
            }
        };
    }

    //需要替换mvc自动配置类中区域解析器,
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //错误页面参数设置
    @Bean
    public MyErrorAttribute errorAttributes(){
        return new MyErrorAttribute();
    }
}
