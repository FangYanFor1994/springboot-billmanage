package com.fy.billmanagement.mapper;

import com.fy.billmanagement.entities.User;

import java.util.List;

public interface UserMapper {

    public List<User> getUsers(User user);

    public void addUser(User user);

    public User getUserById(Integer id);

    public void updateUserById(User user);

    public void deleteUserById(Integer id);

    //校验登录用户
    public User checkUserByUsername(String username);
}
