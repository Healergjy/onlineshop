package com.gjy.mapper;

import com.github.pagehelper.Page;
import com.gjy.pojo.UserOrder;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserOrderMapper {
    public int addOrder(Map map);
    public Page<UserOrder> findAll(int id);
    public Page<UserOrder> findAllByBusiness(int id);
    public void deleteById(int id);
    public UserOrder findById(int id);
    public void editUserOrderStatus(Map map);
}
