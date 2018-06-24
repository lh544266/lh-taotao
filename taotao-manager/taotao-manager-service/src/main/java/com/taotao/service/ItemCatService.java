package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

public interface ItemCatService {
    //ItemCat加载树
    List<EUTreeNode> getItemCat(long parentId);
}
