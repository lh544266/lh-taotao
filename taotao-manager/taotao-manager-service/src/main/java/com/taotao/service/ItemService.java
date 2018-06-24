package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;


public interface ItemService {
    //根据id查询Item
    TbItem getItem(long id);
    //新增商品
    TaotaoResult insertItem(TbItem item);
   /* //删除单个商品商品
    TaotaoResult deleteItem(long id);*/
   //批量删除商品
   TaotaoResult deleteItem(long[] id);
  /* //修改商品
    TaotaoResult updateItem(TbItem item);*/
    //ItemList分页
    EUDataGridResult getItemList(int page, int rows);
}
