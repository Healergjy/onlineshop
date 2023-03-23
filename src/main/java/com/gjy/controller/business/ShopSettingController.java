package com.gjy.controller.business;

import com.gjy.pojo.Business;
import com.gjy.pojo.ShopSetting;
import com.gjy.service.ShopSettingService;
import com.gjy.utils.MessageConstant;
import com.gjy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/business")
public class ShopSettingController {
    @Autowired
    private ShopSettingService shopSettingService;

    @RequestMapping("/findShopById")
    public Result findShopById(HttpServletRequest request) {
        Business business = (Business) request.getSession().getAttribute("business");
        int id = business.getId();
        try {
            ShopSetting shop = shopSettingService.findShopById(id);
            return new Result(true, null, shop);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }

    }

    @RequestMapping("/editShopSetting")
    public Result editShopSetting(@RequestBody ShopSetting shopSetting, HttpServletRequest request) {
        try {
            Business business = (Business) request.getSession().getAttribute("business");
            shopSetting.setBusinessId(business.getId());
            shopSettingService.editShopSetting(shopSetting);
            return new Result(true, MessageConstant.EDITSHOPSETTING_SUCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDITSHOPSETTING_FAIL);
        }
    }

}
