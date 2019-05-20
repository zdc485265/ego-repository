package cn.gzsxt.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping(value="/")
	public String showIndex(){
		
		return "index";
	}
	/**
	 * 请求路径格式：  http://localhost:8080/item-list
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable("page")String page){
		
		System.out.println("跳转到"+page+".jsp页面");
		
		return page;
	}

}
