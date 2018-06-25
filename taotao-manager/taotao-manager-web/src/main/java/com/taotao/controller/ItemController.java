package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.aspectj.internal.lang.annotation.ajcDeclareEoW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    //根据id查询商品
    @RequestMapping("/item/{id}")
    @ResponseBody
    public TbItem getItem(@PathVariable long id){
        TbItem item = itemService.getItem(id);
        return item;
    }
    //保存新增item
    @RequestMapping(value = "/item/save",method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItem(TbItem item, String desc, String itemParam) throws Exception {
        TaotaoResult result = itemService.insertItem(item,desc,itemParam);
        return result;
    }
    //删除商品
    @RequestMapping("/item/delete")
    @ResponseBody
    public TaotaoResult deleteItem(@RequestParam(value = "ids",defaultValue = "0") long [] id){
        TaotaoResult result = itemService.deleteItem(id);
        return result;
    }
   /* //修改商品
    @RequestMapping("/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem item){
        TaotaoResult result = itemService.updateItem(item);
        return result;
    }*/
    //ItemLis分页
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(int page,int rows){
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
}
