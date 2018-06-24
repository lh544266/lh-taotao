package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    //加载ContentCategory的树列表的实现
    @Override
    public List<EUTreeNode> getContentCategory(long parentId) {
        TbContentCategoryExample example=new TbContentCategoryExample();
        //设置查询条件，根据parentId进行查询
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        //设置List<EUTreeNode>结果集
        List<EUTreeNode> resultList=new ArrayList<>();
        //遍历查询结果
        for(TbContentCategory contentCategory:list){
            //创建EUTreeNode
            EUTreeNode node=new EUTreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }
    //增加节点的实现
    @Override
    public TaotaoResult insertContentCategory(long parentId, String name) {
        //创建ContentCategory，pojo类，补全该类
        TbContentCategory contentCategory=new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //将数据插入到表中
        contentCategoryMapper.insert(contentCategory);
        //获取当前parentId的数据
        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断获取的parentId中isparent是否为true，如果不是，则修改为true
        if(!category.getIsParent()){
            category.setIsParent(true);
            //修改后，更新当前获取的parentId数据
            contentCategoryMapper.updateByPrimaryKey(category);
        }
        //返回状态
        return TaotaoResult.ok(contentCategory);
    }
    //删除节点的实现
    @Override
    public TaotaoResult deleteContentCategory(long parentId) {
       //根据id查询
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断查询出的是否为父节点，如果为父节点，则删除下所有子节点
        if(contentCategory.getIsParent()){
            //该父节点下所有子节点
            List<TbContentCategory> list = getContentCategoryList(parentId);
            //递归删除
            for(TbContentCategory content:list){
                deleteContentCategory(content.getId());
            }
        }
        //判断删除后的父类是否还有子节点，如果没有，将isparent设置为false
        if(getContentCategoryList(contentCategory.getParentId()).size()==1)
        {
            TbContentCategory parentCategory=contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
            parentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentCategory);
        }
        contentCategoryMapper.deleteByPrimaryKey(parentId);
        return  TaotaoResult.ok();
    }
    //修改节点的实现
    @Override
    public TaotaoResult updateContentCategory(long id,String name) {
        //根据id进行查询
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        return TaotaoResult.ok();
    }

    //根据parentId查询TbContentCategory，获取当前parentId下所有子节点
    private List<TbContentCategory> getContentCategoryList(long parentId){
        TbContentCategoryExample example=new TbContentCategoryExample();
        //根据parentId进行查询
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        return list;
    }
}
