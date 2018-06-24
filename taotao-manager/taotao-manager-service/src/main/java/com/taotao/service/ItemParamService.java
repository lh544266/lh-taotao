package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;

public interface ItemParamService {
    //ItemParam表分页
    EUDataGridResult getItemParam(int page,int rows);
}
