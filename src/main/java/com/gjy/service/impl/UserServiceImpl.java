package com.gjy.service.impl;

import com.gjy.mapper.UserMapper;
import com.gjy.pojo.User;
import com.gjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public void editUser(User user) {
        user.setUpdateTime(new Date());
        userMapper.editUser(user);
    }

    @Override
    public void editUserMoney(int userId, double price) {
        Map map=new HashMap();
        map.put("userId",userId);
        map.put("price",price);
        userMapper.editUserMoney(map);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User findUser(User user) {
        return userMapper.findUser(user);
    }
}
