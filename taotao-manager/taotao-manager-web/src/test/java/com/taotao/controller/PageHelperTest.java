package com.taotao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PageHelperTest {
    @Test
    public void pageHelpItem(){
        //获取spring容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
        //注入bean
        TbItemMapper tbItemMapper = ac.getBean(TbItemMapper.class);
        TbItemExample example=new TbItemExample();
        PageHelper.startPage(1,10);
        List<TbItem> list = tbItemMapper.selectByExample(example);
        //遍历list
        for(TbItem item:list){
            System.out.println(item.getTitle());
        }
        PageInfo info=new PageInfo(list);
        System.out.println(info.getTotal());
    }

    @Test
    public void pageHelpItemParam(){
        //获取spring容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
        //注入bean
        TbItemParamMapper itemParamMapper = ac.getBean(TbItemParamMapper.class);
        TbItemParamExample example=new TbItemParamExample();
        PageHelper.startPage(1,10);
        List<TbItemParam> list = itemParamMapper.selectByExample(example);
        //遍历list
        for(TbItemParam itemParam:list){
            System.out.println(itemParam.getItemCatId());
        }
        PageInfo info=new PageInfo(list);
        System.out.println(info.getTotal());
    }
}
