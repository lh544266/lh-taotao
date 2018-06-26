package com.taotao.sso.controller;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//注册时对数据进行校验
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public Object getUser(@PathVariable String param,@PathVariable Integer type,String callback){
		TaotaoResult result=null;
		//对传入的参数进行校验
		if(StringUtils.isEmpty(param)){
			result=TaotaoResult.build(400, "校验内容不能为空");
		}
		if(type==null){
			result=TaotaoResult.build(400, "校验的类型不能为空");
		}
		if(type!=1&&type!=2&&type!=3){
			result=TaotaoResult.build(400, "校验的类型不正确");
		}
		//jsonp校验出错
		if(null!=result){
			if(null!=callback){
				MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}else{
				return result;
			}
		}
		try {
			//调用服务
			result = userService.getUser(param, type);
			
		} catch (Exception e) {
			e.printStackTrace();
			result=TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		if(null!=callback){
			MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}else{
			return result;
		}
	}
	//注册数据到user表中,post请求
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createUser(TbUser user){
		//如果插入成功返回taotaoresult，如果失败返回500
		try {
			TaotaoResult result = userService.createUser(user);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	//登陆
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username,String password,
			HttpServletRequest request,HttpServletResponse response){
	try {
		TaotaoResult result = userService.userLogin(username, password,request,response);
		return result;
		
	} catch (Exception e) {
		e.printStackTrace();
		return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
	}
	}
	/*//通过token查询用户信息
	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback){
		TaotaoResult result=null;
		//获取token查询用户信息
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//判断jsonp
		if(StringUtils.isBlank(callback)){
			return result;
		}else{
			MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}*/
}
