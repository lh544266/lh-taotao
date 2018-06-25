package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
    //Content分页展示
    EUDataGridResult getContentList(int page,int rows);
    //新增内容
    TaotaoResult insertContent(TbContent content);
    //删除内容
    TaotaoResult deleteContent(long contentId);
    //修改内容
    TaotaoResult updateContent(TbContent content);
}
