package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

public interface ContentCategoryService {
    //加载ContentCategory的树列表
    List<EUTreeNode> getContentCategory(long parentId);
}
