package com.gjy.mapper;

import com.github.pagehelper.Page;
import com.gjy.pojo.Product;
import com.gjy.pojo.ShopCar;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ShopCarMapper {
    public int addShopCar(Map map);
    public Page<ShopCar> findAll(Integer id);
    public void deleteById(Integer id);
}
