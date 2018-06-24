package com.taotao.controller;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class DeleteTest {
    @Test
    public void delete(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
        TbContentCategoryMapper contentCategoryMapper = ac.getBean(TbContentCategoryMapper.class);
        TbContentCategoryExample example=new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo((long) 86);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        for(TbContentCategory contentCategory:list){
            System.out.println(contentCategory.getParentId());
        }
    }
}
