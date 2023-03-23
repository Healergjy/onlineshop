package com.gjy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gjy.mapper.ProductMapper;
import com.gjy.mapper.ShopCarMapper;
import com.gjy.pojo.Product;
import com.gjy.pojo.ShopCar;
import com.gjy.service.ShopCarService;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ShopCarServiceImpl implements ShopCarService {
    @Autowired
    private ShopCarMapper shopCarMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void deleteById(Integer id) {
     shopCarMapper.deleteById(id);
    }

    @Override
    public PageResult findAll(QueryPageBean queryPageBean, int id) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        Page<ShopCar> page = shopCarMapper.findAll(id);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public int addShopCar(int pid, int userId) {
        Product product = productMapper.findById(pid);
        Map map= new HashMap();
        map.put("userId",userId);
        map.put("ptitle",product.getPtitle());
        map.put("pimg",product.getPimg());
        map.put("businessId",product.getBusinessId());
        map.put("pprice",product.getPprice());
        return shopCarMapper.addShopCar(map);
    }
}
