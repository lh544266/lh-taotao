package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EUTreeNode> getContentCategory(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> result = contentCategoryService.getContentCategory(parentId);
        return  result;
    }
}
