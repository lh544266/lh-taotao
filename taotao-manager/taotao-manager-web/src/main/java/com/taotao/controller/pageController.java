package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageController {
    //设置首页
    @RequestMapping("/")
    public String pageIndex(){
        return "index";
    }
    //展示其他页面
    @RequestMapping("/{page}")
    public String pageOthers(@PathVariable String page){
        return page;
    }
}
