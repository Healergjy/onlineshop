package com.gjy.service;

import com.gjy.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAllUser();
    public User findUser(User user);
    public int addUser(User user);
    public User findUserById(int id);
    public void editUser(User user);
    public User findById(int id);
    public void editUserMoney(int userId,double price);
}
