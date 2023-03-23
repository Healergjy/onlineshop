package com.gjy.service;

import com.github.pagehelper.Page;
import com.gjy.pojo.Product;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;

public interface ProductService {
    public void addProduct(Product product);
    public void deleteProduct(Integer id);
    public Product findById(Integer id);
    public PageResult findPage(QueryPageBean queryPageBean,int BusinessId);
    public PageResult findAll(QueryPageBean queryPageBean);
    public PageResult findByType(QueryPageBean queryPageBean,String type);
    public Product findProductById(Integer id);
    public void editProduct(Product product);
    public void editNumberAndSale(String pimg);
}
