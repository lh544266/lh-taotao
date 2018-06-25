package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    //根据id查询商品
    @Override
    public TbItem getItem(long id) {
        TbItem item = itemMapper.selectByPrimaryKey(id);
        return item;
    }

    //增加商品的实现
    @Override
    public TaotaoResult insertItem(TbItem item, String desc, String itemParam) throws Exception {
        //item补全
        //生成商品ID
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除',
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库
        itemMapper.insert(item);
        //添加商品描述信息
        TaotaoResult result = insertItemDesc(itemId, desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        return TaotaoResult.ok();
    }
    //添加商品描述
    private TaotaoResult insertItemDesc(Long itemId, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }
     //添加规格参数
    private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();
    }

    /*   //删除单个商品的实现
       @Override
       public TaotaoResult deleteItem(long id) {
           itemMapper.deleteByPrimaryKey(id);
           return TaotaoResult.ok();
       }*/
    //批量删除商品的实现
    @Override
    public TaotaoResult deleteItem(long[] id) {
        for(long l:id){
            itemMapper.deleteByPrimaryKey(l);
        }
        return TaotaoResult.ok();
    }
  /*  //修改商品的实现
    @Override
    public TaotaoResult updateItem(TbItem item) {
        itemMapper.updateByPrimaryKey(item);
        return TaotaoResult.ok();
    }*/

    //商品分页列表的实现
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example=new TbItemExample();
        //查询之前先分页
        PageHelper.startPage(page,rows);
        // 查询所有TbItem
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值
        EUDataGridResult result=new EUDataGridResult();
        //设置rows
        result.setRows(list);
        //使用pageInfo获取total
        PageInfo<TbItem> info=new PageInfo<>(list);
        result.setTotal(info.getTotal());

        return result;
    }
}
