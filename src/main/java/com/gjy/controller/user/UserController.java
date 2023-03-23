package com.gjy.controller.user;

import com.gjy.pojo.Product;
import com.gjy.pojo.User;
import com.gjy.pojo.UserOrder;
import com.gjy.service.*;
import com.gjy.utils.MessageConstant;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;
import com.gjy.utils.Result;
import com.sun.org.apache.regexp.internal.RE;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private BusinessService businessService;

    @RequestMapping("/login")
    public String login(User user, HttpSession session) {
        User user1 = userService.findUser(user);
        if (user1 != null) {
            session.setAttribute("user", user1);
            return "redirect:/user/main.html";
        }
        session.setAttribute("msg", "用户名或密码错误");
        return "/user/login";
    }

    @RequestMapping("/register")
    public String register(User user, Model model) {
        int i = userService.addUser(user);
        if (i > 0) {
            model.addAttribute("msg", "注册成功");
        } else {
            model.addAttribute("msg", "注册失败");
        }
        return "user/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("msg");
        return "redirect:/user/login.html";
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return productService.findAll(queryPageBean);
    }

    @RequestMapping("/findProductByType")
    @ResponseBody
    public PageResult findProductByType(@RequestBody QueryPageBean queryPageBean) {
        String type = queryPageBean.getQueryString();
        return productService.findByType(queryPageBean, type);
    }

    @RequestMapping("/productdetail/{id}")
    @ResponseBody
    public Result findProductById(@PathVariable("id") Integer id) {
        try {
            Product product = productService.findById(id);
            return new Result(true, null, product);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }

    @RequestMapping("/addShopCar/{id}")
    @ResponseBody
    public Result addShopCar(@PathVariable("id") Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        int i = shopCarService.addShopCar(id, userId);
        if (i > 0) {
            return new Result(true, MessageConstant.ADDSHOPCAR_SUCESSS);
        } else {
            return new Result(false, MessageConstant.ADDSHOPCAR_FAIL);
        }
    }

    @RequestMapping("/findShopCar")
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();
        return shopCarService.findAll(queryPageBean, id);
    }

    @RequestMapping("/deleteShopCar/{id}")
    @ResponseBody
    public Result deleteShopCar(@PathVariable("id") Integer id) {
        try {
            shopCarService.deleteById(id);
            return new Result(true, MessageConstant.DELETESHOPCAR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETESHOPCAR_FAIL);
        }

    }

    @RequestMapping("/findUserById")
    @ResponseBody
    public Result findUserById(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            User user1 = userService.findUserById(user.getId());
            return new Result(true, null, user1);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }

    @RequestMapping("/editPersonInfo")
    @ResponseBody
    public Result editPersonInfo(@RequestBody User user, HttpServletRequest request) {
        try {
            User user1 = (User) request.getSession().getAttribute("user");
            user.setId(user1.getId());
            userService.editUser(user);
            return new Result(true, MessageConstant.EDITPERSONINFO_SUCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDITPERSONINFO_FAIL);
        }
    }

    @RequestMapping("/buyProduct/{id}")
    @ResponseBody
    public Result buyProduct(@PathVariable("id") Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int i = userOrderService.addOrder(id, user.getId());
        if (i > 0) {
            return new Result(true, MessageConstant.ORDER_SUCCESS);
        } else {
            return new Result(false, MessageConstant.ORDER_FAIL);
        }
    }

    @RequestMapping("/findAllOrder")
    @ResponseBody
    public PageResult findAllOrder(@RequestBody QueryPageBean queryPageBean, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return userOrderService.findAll(queryPageBean, user.getId());
    }

    @RequestMapping("/deleteOrder/{id}")
    @ResponseBody
    public Result deleteOrder(@PathVariable("id") Integer id) {
        try {
            userOrderService.deleteById(id);
            return new Result(true, MessageConstant.DELETEOEDER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETEOEDER_FAIL);
        }
    }

    @RequestMapping("/findOrderById/{id}")
    @ResponseBody
    public Result findOrderById(@PathVariable("id") Integer id) {
        try {
            UserOrder userOrder = userOrderService.findById(id);
            return new Result(true, MessageConstant.DELETEOEDER_SUCCESS, userOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETEOEDER_FAIL);
        }
    }

    @RequestMapping("/findUser")
    @ResponseBody
    public Result findUser(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            User user1 = userService.findById(user.getId());
            return new Result(true, null, user1);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }

    @RequestMapping("/pay/{id}")
    @ResponseBody
    public Result userPay(@PathVariable("id") Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        UserOrder userOrder = userOrderService.findById(id);
        double money = user.getMoney();
        if (money>userOrder.getPrice()) {
            if (userOrder.getStatus().equals("未支付")) {
                businessService.editBusinessMoney(userOrder.getBusinessId(), userOrder.getPrice());
                userService.editUserMoney(userOrder.getUserId(), userOrder.getPrice());
                userOrderService.editUserOrderStatus(MessageConstant.ORDER_STATUS_WAITPRODUCT, userOrder.getId());
                return new Result(true, MessageConstant.PAY_SUCCESS);
            }else {
                return new Result(false,MessageConstant.PAY_ERROR_TIP);
            }
        }else {
            return new Result(false,MessageConstant.PAY_FAIL);
        }
    }

    @RequestMapping("/receiveProduct/{id}")
    @ResponseBody
    public Result receiveProduct(@PathVariable("id") Integer id) {
        UserOrder order = userOrderService.findById(id);
        if (order.getStatus().equals("待收货")) {
            userOrderService.editUserOrderStatus(MessageConstant.ORDER_STATUS_SUCCESS, order.getId());
            return new Result(true, MessageConstant.RECEIVE_SUCESS);
        } else {
            return new Result(false, MessageConstant.RECEIVE_FAIL);
        }
    }

}
