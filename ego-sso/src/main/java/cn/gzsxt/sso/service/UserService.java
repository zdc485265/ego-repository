package cn.gzsxt.sso.service;

import javax.servlet.http.HttpServletResponse;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.User;

public interface UserService {
	public EgoResult check(String param, Integer type);
	public EgoResult register(User user);
	public EgoResult login(String username, String password,HttpServletResponse response);
	public EgoResult checkLogin(String token);
}
