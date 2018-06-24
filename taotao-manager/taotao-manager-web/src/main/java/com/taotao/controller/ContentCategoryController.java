package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    //加载树列表
    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCategory(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> result = contentCategoryService.getContentCategory(parentId);
        return  result;
    }
    //增加树节点
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult insertContentCategory(long parentId,String name){
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }
    //删除树节点
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContenCategory(@RequestParam(value = "id",defaultValue = "0") long parentId){
        TaotaoResult result = contentCategoryService.deleteContentCategory(parentId);
        return result;
    }
    //修改节点
    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id,String name){
        TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
        return result;
    }
}
