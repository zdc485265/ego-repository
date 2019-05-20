package cn.gzsxt.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/showRegister")
	public String showRegister(){
		return "register";
	}
	
	@RequestMapping("/showLogin")
	public String showLogin(){
		return "login";
	}
}
