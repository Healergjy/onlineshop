package com.gjy.service.impl;

import com.gjy.mapper.ShopSettingMapper;
import com.gjy.pojo.ShopSetting;
import com.gjy.service.ShopSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopSettingServiceImpl implements ShopSettingService {
    @Autowired
    private ShopSettingMapper shopSettingMapper;

    @Override
    public void editShopSetting(ShopSetting shopSetting) {
        shopSettingMapper.editShopSetting(shopSetting);
    }

    @Override
    public ShopSetting findShopById(Integer id) {
        return shopSettingMapper.findShopById(id);
    }
}
