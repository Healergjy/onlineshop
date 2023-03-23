package com.gjy.controller.business;

import com.gjy.pojo.Business;
import com.gjy.pojo.User;
import com.gjy.pojo.UserOrder;
import com.gjy.service.BusinessService;
import com.gjy.service.ProductService;
import com.gjy.service.UserOrderService;
import com.gjy.service.UserService;
import com.gjy.utils.MessageConstant;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;
import com.gjy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/login")
    public String login(Business business, HttpSession session) {
        Business business1 = businessService.findBusiness(business);
        if (business1 != null) {
            session.setAttribute("business", business1);
            return "redirect:/business/main";
        } else {
            session.setAttribute("msg", "用户名或密码错误");
        }
        return "business/login";
    }

    @RequestMapping("/register")
    public String register(Business business, Model model) {
        int i = businessService.addBusiness(business);
        if (i > 0) {
            model.addAttribute("msg", "注册成功");
        } else {
            model.addAttribute("msg", "注册失败");
        }
        return "business/login";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "business/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("business");
        return "redirect:/business/toLogin";
    }

    @RequestMapping("/findBusinessById")
    @ResponseBody
    public Result findBusinessById(HttpServletRequest request) {
        Business business = (Business) request.getSession().getAttribute("business");
        int id = business.getId();
        try {
            businessService.findBusinessById(id);
            return new Result(true, "查询成功", business);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询失败", null);
        }
    }

    @RequestMapping("/editPersonInfo")
    @ResponseBody
    public Result editPersonInfo(@RequestBody Business business, HttpServletRequest request) {
        try {
            Business business1 = (Business) request.getSession().getAttribute("business");
            business.setId(business1.getId());
            businessService.editPersonInfo(business);
            return new Result(true, MessageConstant.EDITPERSONINFO_SUCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDITPERSONINFO_FAIL);
        }
    }

    @RequestMapping("/findAllOrder")
    @ResponseBody
    public PageResult findAllOrder(@RequestBody QueryPageBean queryPageBean, HttpServletRequest request) {
        Business business = (Business) request.getSession().getAttribute("business");
        return userOrderService.findAllByBusiness(queryPageBean, business.getId());
    }

    @RequestMapping("/findUserById/{userId}")
    @ResponseBody
    public Result findUserById(@PathVariable("userId") int userId) {
        try {
            User user = userService.findUserById(userId);
            return new Result(true, null, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }

    @RequestMapping("/sendProduct/{id}")
    @ResponseBody
    public Result sendProduct(@PathVariable("id") Integer id) {
        try {
            userOrderService.editUserOrderStatus(MessageConstant.ORDER_STATUS_WAITRECEIVE, id);
            //发货成功，减库存  加销量
            UserOrder order = userOrderService.findById(id);
            productService.editNumberAndSale(order.getPimg());
            return new Result(true,MessageConstant.SENDPRODUCT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SENDPRODUCT_FAIL);
        }
    }

}
