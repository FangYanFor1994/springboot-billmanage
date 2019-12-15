package com.fy.billmanagement.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        ///获取请求头中的l参数值
        String l = httpServletRequest.getParameter("l");
        //获取浏览器上的区域信息
        Locale locale = httpServletRequest.getLocale();
//获取当前操作系统 默认的区域信息
//    Locale locale = Locale.getDefault();

//参数有区域信息，则用参数里的区域信息
        if (!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
//参数：语言代码，国家代码
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
