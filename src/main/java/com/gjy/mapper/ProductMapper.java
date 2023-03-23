package com.gjy.mapper;

import com.github.pagehelper.Page;
import com.gjy.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ProductMapper {
    public void addProduct(Product product);
    public Page<Product> findByType(String type);
    public Page<Product> findByCondition(Map map);
    public Product findById(Integer id);
    public Page<Product> findAll(Map map);
    public Product findProductById(Integer id);
    public void editProduct(Product product);
    public void deleteProduct(Integer id);
    public void editNumberAndSale(String pimg);
}
