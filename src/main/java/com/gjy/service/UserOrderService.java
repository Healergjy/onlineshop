package com.gjy.service;

import com.gjy.pojo.UserOrder;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;

public interface UserOrderService {
    public int addOrder(int pid, int userId);
    public PageResult findAll(QueryPageBean queryPageBean, int id);
    public PageResult findAllByBusiness(QueryPageBean queryPageBean, int id);
    public void deleteById(int id);
    public UserOrder findById(int id);
    public void editUserOrderStatus(String status,int orderId);
}
