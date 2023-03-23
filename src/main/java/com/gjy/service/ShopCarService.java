package com.gjy.service;


import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;


public interface ShopCarService {
    public int addShopCar(int pid,int userId);
    public PageResult findAll(QueryPageBean queryPageBean,int id);
    public void deleteById(Integer id);
}
