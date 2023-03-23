package com.gjy.config;

import com.gjy.interceptor.BusinessLoginInterceptor;
import com.gjy.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OnlineShopMvcConfig implements WebMvcConfigurer {
    @Autowired
    private BusinessLoginInterceptor businessLoginInterceptor;
    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/business/main").setViewName("business/main");
        registry.addViewController("/business/add.html").setViewName("business/add");
        registry.addViewController("/business/productanalysis.html").setViewName("business/productanalysis");
        registry.addViewController("/business/login").setViewName("business/login");
        registry.addViewController("/business/list.html").setViewName("business/list");
        registry.addViewController("/business/order.html").setViewName("business/order");
        registry.addViewController("/business/myshop.html").setViewName("business/myshop");
        registry.addViewController("/business/return.html").setViewName("business/return");
        registry.addViewController("/business/shopdesign.html").setViewName("business/shopdesign");
        registry.addViewController("/business/person.html").setViewName("business/person");
        registry.addViewController("/business/shopsettings.html").setViewName("business/shopsettings");

        registry.addViewController("/user/main.html").setViewName("user/main");
        registry.addViewController("/user/index.html").setViewName("user/index");
        registry.addViewController("/user/clothes.html").setViewName("user/clothes");
        registry.addViewController("/user/login.html").setViewName("user/login");
        registry.addViewController("/user/shopcar.html").setViewName("user/shopcar");
        registry.addViewController("/user/order.html").setViewName("user/order");
        registry.addViewController("/user/goods.html").setViewName("user/goods");
        registry.addViewController("/user/book.html").setViewName("user/book");
        registry.addViewController("/user/daily.html").setViewName("user/daily");
        registry.addViewController("/user/phone.html").setViewName("user/phone");
        registry.addViewController("/user/shoebag.html").setViewName("user/shoebag");
        registry.addViewController("/user/sport.html").setViewName("user/sport");
        registry.addViewController("/user/person.html").setViewName("user/person");
        registry.addViewController("/user/productdetail.html").setViewName("user/productdetail");
    }

    //配置商家登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(businessLoginInterceptor).addPathPatterns("/business/**").excludePathPatterns("/business/toLogin", "/business/login", "/business/register", "/css/**", "/js/**", "/upload/**");
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login", "/user/login.html", "/user/register","/user/main.html","/css/**", "/js/**", "/upload/**");
    }




    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:E:\\JavaWeb\\onlineshop\\src\\main\\resources\\upload\\");
    }
}
