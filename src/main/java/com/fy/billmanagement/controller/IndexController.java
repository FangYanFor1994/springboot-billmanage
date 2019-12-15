package com.fy.billmanagement.controller;

import com.fy.billmanagement.entities.User;
import com.fy.billmanagement.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {
    //获取日志实体类
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserMapper userMapper;
    /**
     * 登录系统
     * @param session
     * @param username
     * @param password
     * @param map
     * @return
     */
    @PostMapping("/login")
    public String login(HttpSession session,String username, String password, Map<String,Object> map){
        if(!StringUtils.isEmpty(username)){
            User user = userMapper.checkUserByUsername(username);
            if(user != null && user.getPassword().equals(password)){
                //登录成功把用户名放到session中
                session.setAttribute("loginUser",user);
                return "redirect:/main.html";
            }
        }
        map.put("msg","用户名或密码错误");
        return "/main/login";
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");//移除登录用户信息
        session.invalidate();//注销session
        return "redirect:/index.html";//重定向到登录页面
    }

}
