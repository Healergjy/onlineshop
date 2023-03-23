package com.gjy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gjy.mapper.ProductMapper;
import com.gjy.pojo.Product;
import com.gjy.service.ProductService;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findProductById(Integer id) {
       return productMapper.findProductById(id);
    }

    @Override
    public void editNumberAndSale(String pimg) {
        productMapper.editNumberAndSale(pimg);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean,int businessId) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        Map map= new HashMap();
        map.put("businessId",businessId);
        map.put("value",queryString);
        PageHelper.startPage(currentPage,pageSize);
        Page<Product> page = productMapper.findByCondition(map);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    public PageResult findByType(QueryPageBean queryPageBean,String type) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        Page<Product> page = productMapper.findByType(type);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult findAll(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        Map map= new HashMap();
        map.put("value",queryString);
        PageHelper.startPage(currentPage,pageSize);
        Page<Product> page = productMapper.findAll(map);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }

    @Override
    public void editProduct(Product product) {
        product.setUpdateTime(new Date());
        productMapper.editProduct(product);
    }

    @Override
    public void addProduct(Product product) {
      productMapper.addProduct(product);
    }
}
