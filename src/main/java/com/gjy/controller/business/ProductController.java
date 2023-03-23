package com.gjy.controller.business;

import com.gjy.pojo.Business;
import com.gjy.pojo.Product;
import com.gjy.service.ProductService;
import com.gjy.utils.MessageConstant;
import com.gjy.utils.PageResult;
import com.gjy.utils.QueryPageBean;
import com.gjy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/business")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/addProduct")
    public Result addProduct(@RequestBody Product product, HttpServletRequest request) {
        Business business = (Business) request.getSession().getAttribute("business");
        product.setBusinessId(business.getId());
        try {
            productService.addProduct(product);
            return new Result(true, MessageConstant.ADDPRODUCT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADDPRODUCT_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean, HttpServletRequest request) {
        Business business = (Business) request.getSession().getAttribute("business");
        int businessId = business.getId();
        return productService.findPage(queryPageBean, businessId);
    }

    @RequestMapping("/findProductById")
    public Result findProductById(Integer id) {
        try {
            Product product = productService.findProductById(id);
            return new Result(true, MessageConstant.QUERY_SUCCESS, product);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_FAIL);
        }


    }

    @RequestMapping("/editProduct")
    public Result editProduct(@RequestBody Product product) {
        try {
            productService.editProduct(product);
            return new Result(true, MessageConstant.EDITPRODUCT_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDITPRODUCT_FAIL);
        }
    }

    @RequestMapping("/deleteProduct")
    public Result deleteProduct(Integer id) {
        try {
            productService.deleteProduct(id);
            return new Result(true, MessageConstant.DELETEPRODUCT_SUCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETEPRODUCT_FAIL);
        }

    }
}
