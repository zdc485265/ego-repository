package cn.gzsxt.sso.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.manager.pojo.User;
import cn.gzsxt.sso.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object check(@PathVariable("param")String param,@PathVariable("type")Integer type,String callback){
		EgoResult result = userService.check(param, type);
		if(null==callback||"".equals(callback)){
			return result;
		}
		String json = JsonUtils.objectToJson(result);
		String jsData=callback+"("+json+")";
		return jsData;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Object redister(User user){
		EgoResult result = userService.register(user);
		return result;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Object Login(String username,String password,HttpServletResponse response){
		EgoResult result = userService.login(username, password, response);
		return result;
	}
	
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object checkLogin(@PathVariable("token")String token,String callback){
		EgoResult result = userService.checkLogin(token);
		if(null==callback||"".equals(callback)){
			return result;
		}
		String jsonData=JsonUtils.objectToJson(result);
		String jsData=callback+"("+jsonData+")";
		return jsData;
	}
}
