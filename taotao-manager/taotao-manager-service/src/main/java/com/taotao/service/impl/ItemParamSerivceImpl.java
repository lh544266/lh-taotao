package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamSerivceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;
    @Override
    public EUDataGridResult getItemParam(int page, int rows) {
        TbItemParamExample example=new TbItemParamExample();
        //查询之前先进行分页
        PageHelper.startPage(page,rows);
        //查询itemParam表中所有数据
        List<TbItemParam> list = itemParamMapper.selectByExample(example);
       //创建EUDataGridResult，获取结果
        EUDataGridResult result=new EUDataGridResult();
        //获取分页列表
        result.setRows(list);
        //获取表中的总条数
        PageInfo<TbItemParam> info=new PageInfo<>(list);
        result.setTotal(info.getTotal());
        return result;
    }
}
