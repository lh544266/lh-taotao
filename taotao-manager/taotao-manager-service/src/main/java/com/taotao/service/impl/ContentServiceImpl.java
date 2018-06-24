package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    //content分页的实现
    @Override
    public EUDataGridResult getContentList(int page,int rows) {
        TbContentExample example=new TbContentExample();
        //查询之前先进行分页
        PageHelper.startPage(page,rows);
        //查询 Tbcontent表
        List<TbContent> list = contentMapper.selectByExample(example);
        //创建EUDataGridResult结果集
        EUDataGridResult result=new EUDataGridResult();
        //设置rows
        result.setRows(list);
        //创建PageInfo，获取total
        PageInfo<TbContent> info=new PageInfo<>(list);
        result.setTotal(info.getTotal());
        return result;
    }
}
