package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
