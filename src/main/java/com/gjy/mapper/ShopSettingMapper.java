package com.gjy.mapper;

import com.gjy.pojo.ShopSetting;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopSettingMapper {
    public ShopSetting findShopById(Integer id);
    public void editShopSetting(ShopSetting shopSetting);
}
