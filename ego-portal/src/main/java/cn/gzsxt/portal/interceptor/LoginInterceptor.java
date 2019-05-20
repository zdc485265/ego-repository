package cn.gzsxt.portal.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.utils.HttpClientUtils;
import cn.gzsxt.manager.pojo.User;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Value("${EGO_USER_TOKEN}")
	private String EGO_USER_TOKEN;
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Cookie[] cookies = request.getCookies();
		boolean flag=true;
		User user =null;
		for (Cookie cookie : cookies) {
			if(EGO_USER_TOKEN.equals(cookie.getName())){
				String token = cookie.getValue();
				String doGet = HttpClientUtils.doGet(SSO_BASE_URL+"/token/"+token);
				if(null!=doGet&&!"".equals(doGet)){
					EgoResult result = EgoResult.formatToPojo(doGet, User.class);
					Integer status = result.getStatus();
					if(200==status){
						user =(User) result.getData();
						flag=false;
						break;
					}
				}
			}
		}
		if(flag){
			response.sendRedirect(SSO_BASE_URL+"/showLogin");
		}
		request.setAttribute("loginUser", user);
		return true;
	}

}
