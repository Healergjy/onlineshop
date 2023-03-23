package com.gjy.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BusinessLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object business = request.getSession().getAttribute("business");
        if (business != null) {
            return true;
        } else {
            response.sendRedirect("/business/toLogin");
            return false;
        }
    }
}
