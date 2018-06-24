package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;

public interface ContentService {
    //Content分页
    EUDataGridResult getContentList(int page,int rows);
}
