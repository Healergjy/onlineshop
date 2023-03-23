package com.gjy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gjy.mapper.ProductMapper;
import com.gjy.mapper.UserOrderMapper;
import com.gjy.pojo.Product;
import com.gjy.pojo.UserOrder;
import com.gjy.service.UserOrderService;
import com.gjy.utils.MessageConstant;
import com.gjy.utils.OrderNumber;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public UserOrder findById(int id) {
        return userOrderMapper.findById(id);
    }

    @Override
    public void editUserOrderStatus(String status,int orderId) {
        Map map=new HashMap();
        map.put("status",status);
        map.put("orderId",orderId);
        userOrderMapper.editUserOrderStatus(map);
    }

    @Override
    public int addOrder(int pid, int userId) {
        Product product = productMapper.findProductById(pid);
        Map map = new HashMap();
        map.put("orderId", OrderNumber.getOrderNum());
        map.put("status", MessageConstant.ORDER_STATUS_NOPAY);
        map.put("userId",userId);
        map.put("businessId",product.getBusinessId());
        map.put("price",product.getPprice());
        map.put("ptitle",product.getPtitle());
        map.put("pimg",product.getPimg());
        return userOrderMapper.addOrder(map);
    }
    @Override
    public PageResult findAll(QueryPageBean queryPageBean, int id) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        Page<UserOrder> page = userOrderMapper.findAll(id);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult findAllByBusiness(QueryPageBean queryPageBean, int id) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        Page<UserOrder> page = userOrderMapper.findAllByBusiness(id);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(int id) {
        userOrderMapper.deleteById(id);
    }
}
