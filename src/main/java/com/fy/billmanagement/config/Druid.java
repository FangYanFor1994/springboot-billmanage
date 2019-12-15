package com.fy.billmanagement.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class Druid {

    /**
     * 将DruidDatasource连接池的属性注入
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid1(){
        return new DruidDataSource();
    }

    /**
     * 配置一个Druid后台监控
     * 1.配置一个管理后台的servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //注册一个管理后台的servlet   statViewServlet
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //初始化参数
        Map<String, String> initParams = new HashMap<>();
        initParams.put(StatViewServlet.PARAM_NAME_USERNAME, "root");
        initParams.put(StatViewServlet.PARAM_NAME_PASSWORD, "123");
        initParams.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        initParams.put(StatViewServlet.PARAM_NAME_DENY, "192.168.10.1");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 2.配置一个过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filter(){
        //注册过滤器WebSatFilter
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //拦截所有路径
        bean.setUrlPatterns(Arrays.asList("/*"));
        //排除路径
        Map<String, String> initparams = new HashMap<>();
        initparams.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
        bean.setInitParameters(initparams);
        return bean;
    }
}
