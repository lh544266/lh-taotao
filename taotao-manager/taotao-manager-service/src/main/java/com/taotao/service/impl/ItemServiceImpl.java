package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    //根据id查询商品
    @Override
    public TbItem getItem(long id) {
        TbItem item = itemMapper.selectByPrimaryKey(id);
        return item;
    }
    //增加商品的实现
    @Override
    public TaotaoResult insertItem(TbItem item) {
        //补全
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        return TaotaoResult.ok();
    }


    /*   //删除单个商品的实现
       @Override
       public TaotaoResult deleteItem(long id) {
           itemMapper.deleteByPrimaryKey(id);
           return TaotaoResult.ok();
       }*/
    //批量删除商品的实现
    @Override
    public TaotaoResult deleteItem(long[] id) {
        for(long l:id){
            itemMapper.deleteByPrimaryKey(l);
        }
        return TaotaoResult.ok();
    }
  /*  //修改商品的实现
    @Override
    public TaotaoResult updateItem(TbItem item) {
        itemMapper.updateByPrimaryKey(item);
        return TaotaoResult.ok();
    }*/

    //商品分页列表的实现
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example=new TbItemExample();
        //查询之前先分页
        PageHelper.startPage(page,rows);
        // 查询所有TbItem
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值
        EUDataGridResult result=new EUDataGridResult();
        //设置rows
        result.setRows(list);
        //使用pageInfo获取total
        PageInfo<TbItem> info=new PageInfo<>(list);
        result.setTotal(info.getTotal());

        return result;
    }
}
