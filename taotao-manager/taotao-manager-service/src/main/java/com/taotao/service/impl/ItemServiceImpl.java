package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

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
