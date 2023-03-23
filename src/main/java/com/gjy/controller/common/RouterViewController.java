package com.gjy.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterViewController {
    @RequestMapping("/user/index")
    public String index(){
        return "/user/main.html";
    }
    @RequestMapping("/toBusiness")
    public String toBusiness(){
        return "redirect:business/main";
    }
}
