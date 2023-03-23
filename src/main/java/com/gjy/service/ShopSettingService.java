package com.gjy.service;

import com.gjy.pojo.ShopSetting;

public interface ShopSettingService {
    public ShopSetting findShopById(Integer id);
    public void editShopSetting(ShopSetting shopSetting);
}
