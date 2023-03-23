package com.gjy.mapper;

import com.gjy.pojo.ShopRotation;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRotationMapper {
    public ShopRotation findRotaionById(int id);
    public void editRotationById(ShopRotation shopRotation);
}
