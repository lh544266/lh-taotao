package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
   //分页展示
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(int page,int rows){
        EUDataGridResult result = contentService.getContentList(page, rows);
        return result;
    }
    //添加内容
    @RequestMapping(value = "/content/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertContent(TbContent content){
        TaotaoResult result = contentService.insertContent(content);
        return result;
    }
    //删除内容
    @RequestMapping("/content/delete")
    @ResponseBody
    public TaotaoResult deleteContent(@RequestParam(value = "ids",defaultValue = "0") long contentId){
        TaotaoResult result = contentService.deleteContent(contentId);
        return result;
    }
    //修改内容
    @RequestMapping(value = "/content/edit",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateContent(TbContent content){
        TaotaoResult taotaoResult = contentService.updateContent(content);
        return taotaoResult;
    }
}
