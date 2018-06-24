package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping("/item/param/list")
    //加载当前页面使用ResponseBody
    @ResponseBody
    public EUDataGridResult getItemPram(int page,int rows){
        EUDataGridResult result = itemParamService.getItemParam(page,rows);
        return result;
    }
}
