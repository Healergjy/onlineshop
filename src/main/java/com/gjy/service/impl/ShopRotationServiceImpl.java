package com.gjy.service.impl;

import com.gjy.mapper.ShopRotationMapper;
import com.gjy.pojo.ShopRotation;
import com.gjy.service.ShopRotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopRotationServiceImpl implements ShopRotationService {
    @Autowired
    private ShopRotationMapper shopRotationMapper;

    @Override
    public void editRotationById(ShopRotation shopRotation) {
        shopRotationMapper.editRotationById(shopRotation);
    }

    @Override
    public ShopRotation findRotaionById(int id) {
        return shopRotationMapper.findRotaionById(id);
    }
}
