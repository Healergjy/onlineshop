package com.gjy.service.impl;

import com.gjy.mapper.BusinessMapper;
import com.gjy.pojo.Business;
import com.gjy.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public void editPersonInfo(Business business) {
        business.setUpdateTime(new Date());
        businessMapper.editPersonInfo(business);
    }

    @Override
    public Business findBusinessById(int id) {
        return businessMapper.findBusinessById(id);
    }

    @Override
    public int addBusiness(Business business) {
        int i = businessMapper.addBusiness(business);
        if(i>0){
            Business business1= businessMapper.findByBusiness(business.getUsername(),business.getPassword());
            if(business1!=null){
                businessMapper.addShopRotation(business1.getId());
                businessMapper.addShopSetting(business1.getId());
            }
        }
        return i;
    }

    @Override
    public void editBusinessMoney(int businessId, double price) {
        Map map=new HashMap();
        map.put("businessId",businessId);
        map.put("price",price);
        businessMapper.editBusinessMoney(map);
    }

    @Override
    public Business findBusiness(Business business) {
        return businessMapper.findBusiness(business);
    }
}
