package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    //加载ContentCategory的树列表
    List<EUTreeNode> getContentCategory(long parentId);
    //添加树节点
    TaotaoResult insertContentCategory(long parentId,String name);
    //删除节点
    TaotaoResult deleteContentCategory(long parentId);
    //修改节点
    TaotaoResult updateContentCategory(long id,String name);
}
