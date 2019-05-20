package cn.gzsxt.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.portal.service.ContentService;

@Controller
public class PageController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model){
		String adResult = contentService.getAdItemList();
		model.addAttribute("ads", adResult);
		return "index";
	}
}
