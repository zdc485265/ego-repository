package cn.gzsxt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.common.pojo.Menu;
import cn.gzsxt.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
	@Qualifier(value="itemCatServiceImpl")
	private ItemCatService itemCatService;
	
	@RequestMapping(value="/item/all",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public Object initMenu(String callback){
		Menu menu = itemCatService.getMenu();
		String menuJson=JsonUtils.objectToJson(menu);
		String result=callback+"("+menuJson+")";
		return result;
	}
}
