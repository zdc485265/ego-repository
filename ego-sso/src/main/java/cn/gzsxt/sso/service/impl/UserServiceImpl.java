package cn.gzsxt.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.manager.mapper.UserMapper;
import cn.gzsxt.manager.pojo.User;
import cn.gzsxt.manager.pojo.UserExample;
import cn.gzsxt.manager.pojo.UserExample.Criteria;
import cn.gzsxt.sso.service.UserService;
import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService{
	
	@Value("${EGO_USER_TOKEN}")
	private String EGO_USER_TOKEN;
	
	@Value("${EGO_USER_KEY}")
	private String EGO_USER_KEY;
	
	@Value("${EGO_USER_EXPIRE_TIME}")
	private Integer EGO_USER_EXPIRE_TIME;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public EgoResult check(String param, Integer type) {
		UserExample example=new UserExample();
		Criteria criteria = example.createCriteria();
		if(1==type){
			criteria.andUsernameEqualTo(param);
		}else if (2==type) {
			criteria.andPhoneEqualTo(param);
		}else if (3==type) {
			criteria.andEmailEqualTo(param);
		}else {
			return EgoResult.build(400,"传参有误");
		}
		List<User> list = userMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			return EgoResult.ok(false);
		}
		return EgoResult.ok(true);
	}

	@Override
	public EgoResult register(User user) {
		
		try {
			user.setCreated(new Date());
			user.setUpdated(user.getCreated());
			int num = userMapper.insertSelective(user);
			
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(400,null);
		}
	}

	@Override
	public EgoResult login(String username, String password, HttpServletResponse response) {
		
		UserExample example=new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			String token = UUID.randomUUID().toString();
			Cookie cookie=new Cookie(EGO_USER_TOKEN, token);
			cookie.setPath("/");
			response.addCookie(cookie);
			User user = list.get(0);
			String objectToJson = JsonUtils.objectToJson(user);
			jedisCluster.set(EGO_USER_KEY+":"+token,objectToJson);
			jedisCluster.expire(EGO_USER_KEY+":"+token,EGO_USER_EXPIRE_TIME);
			return EgoResult.ok(token);
		}
		return EgoResult.build(400, "用户名或密码有误");
	}

	@Override
	public EgoResult checkLogin(String token) {
		String jsonData = jedisCluster.get(EGO_USER_KEY+":"+token);
		if(jsonData!=null&&!"".equals(jsonData)){
			User user = JsonUtils.jsonToPojo(jsonData, User.class);
			return EgoResult.ok(user);
		}
		return EgoResult.build(400, "当前没有登录");
	}

}
