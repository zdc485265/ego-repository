package cn.gzsxt.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gzsxt.dubbo.pojo.User;
import cn.gzsxt.dubbo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public List<User> getAllUsers() {
		List<User> users=new ArrayList<>();
		User user=null;
		for(int i =0;i<5;i++){
			user = new User();
			user.setId(i);
			user.setName("win:gzsxt"+i);
			user.setPwd("win:pwd"+i);
			users.add(user);
		}
		return users;
	}

}
