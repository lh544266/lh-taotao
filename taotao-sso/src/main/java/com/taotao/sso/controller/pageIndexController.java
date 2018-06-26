package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class pageIndexController {
    //展示登录界面
    @RequestMapping("/user/login")
    public String pageLogin(){
        return "login";
    }
    //展示注册界面
    @RequestMapping("/page/register")
    public String pageRegister(){
        return "register";
    }
}
