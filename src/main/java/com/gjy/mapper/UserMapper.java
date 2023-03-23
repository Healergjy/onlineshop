package com.gjy.mapper;

import com.gjy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {
    List<User> queryAllUser();
    public User findUser(User user);
    public User findById(int id);
    public int addUser(User user);
    public User findUserById(int id);
    public void editUser(User user);
    public void editUserMoney(Map map);
}
