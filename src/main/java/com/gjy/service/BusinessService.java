package com.gjy.service;

import com.gjy.pojo.Business;


public interface BusinessService {
    public Business findBusiness(Business business);
    public Business findBusinessById(int id);
    public int addBusiness(Business business);
    public void editPersonInfo(Business business);
    public void editBusinessMoney(int businessId,double price);
}
