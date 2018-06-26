package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	//注册数据校验
	TaotaoResult getUser(String param, int type);
	//注册用户信息
	TaotaoResult createUser(TbUser user);
	//登陆
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	/*//通过token查询用户信息
	TaotaoResult getUserByToken(String token);*/
}
