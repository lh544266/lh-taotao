package com.taotao.sso.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
/*	@Autowired
	private JedisClient JedisClient;
	//注入resources里的值
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;*/
	
	
	//校验数据库中是否有该数据，判断数据是否可用
	@Override
	public TaotaoResult getUser(String param, int type) {
		TbUserExample example=new TbUserExample();
		//创建查询条件
		Criteria criteria = example.createCriteria();
		//对数据进行校验：1、2、3分别代表username、phone、email
		if(1==type){
			criteria.andUsernameEqualTo(param);
		}else if(2==type){
			criteria.andPhoneEqualTo(param);
		}else if(3==type){
			criteria.andEmailEqualTo(param);
		}
		//执行查询条件
		List<TbUser> list = userMapper.selectByExample(example);
		if(list==null || list.size()==0){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}
	//注册用户到user表中
	@Override
	public TaotaoResult createUser(TbUser user) {
		//补全user
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//密码md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return TaotaoResult.ok();
	}
	//登陆
	@Override
	public TaotaoResult userLogin(String username, String password,
			HttpServletRequest request,HttpServletResponse response) {
		
		//根据username查询用户
		TbUserExample example=new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		//判断用户名
		if(null==list||list.size()==0){
			return TaotaoResult.build(400, "用户名错误");
		}
		TbUser user=list.get(0);
		//判断密码
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
			return TaotaoResult.build(400, "密码错误");
		}
		return TaotaoResult.ok();
	/*	//生成token
		String token=UUID.randomUUID().toString();
		//保存之前先清除密码
		user.setPassword(null);
		//将用户信息保存到redis
		JedisClient.set(REDIS_USER_SESSION_KEY+":"+token,JSONUtils.toJSONString(user));
		//设置redis的过期时间，传入token名称和过期时间
		JedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, SSO_SESSION_EXPIRE);
		 //添加cookie,不设置默认时间，浏览器关闭则cookie失效
		 CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		 //返回token
		return TaotaoResult.ok(token);*/
	
	}
	//根据token返回用户信息
	/*@Override
	public TaotaoResult getUserByToken(String token) {
			//获取redis中的token
		String json = JedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
		//判断是否为空
		if(StringUtils.isBlank(json)){
			return TaotaoResult.build(400, "用户登陆已过期，请重新登陆");
		}
		//如果不为空，更新过期时间
		JedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, SSO_SESSION_EXPIRE);
		//返回json转化为对象的用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}
 	*/
}
