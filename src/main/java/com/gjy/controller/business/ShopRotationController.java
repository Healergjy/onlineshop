package com.gjy.controller.business;

import com.gjy.pojo.Business;
import com.gjy.pojo.ShopRotation;
import com.gjy.service.ShopRotationService;
import com.gjy.utils.MessageConstant;
import com.gjy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/business")
public class ShopRotationController {
    @Autowired
    private ShopRotationService shopRotationService;

    @RequestMapping("/findRotationById")
    public Result findRotationById(HttpServletRequest request) {
        Business business = (Business) request.getSession().getAttribute("business");
        int id = business.getId();
        try {
            ShopRotation shopRotation = shopRotationService.findRotaionById(id);
            return new Result(true, null, shopRotation);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }

    @RequestMapping("/editRotationById")
    public Result editRotationById(@RequestBody ShopRotation shopRotation, HttpServletRequest request) {
        Business business = (Business) request.getSession().getAttribute("business");
        int id = business.getId();
        shopRotation.setBusinessId(id);
        try {
            shopRotationService.editRotationById(shopRotation);
            return new Result(true, MessageConstant.EDITSHOPROTAION_SUCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDITSHOPROTAION_FAIL);
        }
    }
}
