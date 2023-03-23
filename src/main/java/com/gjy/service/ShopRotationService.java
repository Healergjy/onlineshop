package com.gjy.service;

import com.gjy.pojo.ShopRotation;

public interface ShopRotationService {
    public ShopRotation findRotaionById(int id);
    public void editRotationById(ShopRotation shopRotation);
}
