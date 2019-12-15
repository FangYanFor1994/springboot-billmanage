package com.fy.billmanagement.controller;

import com.fy.billmanagement.entities.User;
import com.fy.billmanagement.mapper.UserMapper;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: billmanagement-springboot->UserController
 * @description: 用户管理
 * @author: fangyan
 * @create: 2019-12-15 14:43
 **/
@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    /**
     * 查询列表
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/users")
    public String getUsers(Model model, User user){
        List<User> users = userMapper.getUsers(user);
        model.addAttribute("users", users);
        //回显
        model.addAttribute("username", user.getUsername());
        return "user/list";
    }

    /**
     * 进入添加页面
     * @return
     */
    @GetMapping("/user")
    public String toAddPage(){
        return "user/add";
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/user")
    public String addUser(User user, BindingResult bindingResult){
        userMapper.addUser(user);
        return "redirect:/users";
    }

    /**
     * 根据type的值选择进入view或update页面
     * @param id
     * @param model
     * @param type
     * @return
     */
    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id, Model model,
                       @RequestParam(name = "type", defaultValue = "view") String type){
        User user = userMapper.getUserById(id);
        model.addAttribute("user",user);
        return "user/"+type;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @GetMapping("/updateUser")
    public String updateUser(User user){
        userMapper.updateUserById(user);
        return "redirect:/users";
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userMapper.deleteUserById(id);
        return "redirect:/users";
    }

    /**
     * 进入密码修改页面
     * @return
     */
    @GetMapping("/user/pwd")
    public String toPwdPage(){
        return "main/password";
    }

    /**
     * 校验旧密码
     * @param oldPwd
     * @return
     */
    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPassword(@PathVariable("oldPwd")String oldPwd, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        if(user.getPassword().equals(oldPwd)){
            return true;
        }
        return false;
    }

    @PostMapping("/user/updatePwd")
    public String updatePwd(HttpSession session, String password){
        //获取session中的用户信息
        User user = (User) session.getAttribute("loginUser");
        //将新密码替换
        user.setPassword(password);
        //修改保存至数据库中
        userMapper.updateUserById(user);
        //将session中用户信息移除
        session.removeAttribute("loginUser");
        return "redirect:/index.html";
    }
}
