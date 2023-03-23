package com.gjy.mapper;

import com.gjy.pojo.Business;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface BusinessMapper {
    public Business findBusiness(Business business);
    public Business findBusinessById(int id);
    public int addBusiness(Business business);
    public Business findByBusiness(String username,String password);
    public int addShopSetting(int id);
    public int addShopRotation(int id);
    public void editPersonInfo(Business business);
    public void editBusinessMoney(Map map);
}
