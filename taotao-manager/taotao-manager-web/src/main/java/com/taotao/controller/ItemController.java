package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.service.ItemService;
import org.aspectj.internal.lang.annotation.ajcDeclareEoW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(int page,int rows){
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
}
