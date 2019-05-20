package cn.gzsxt.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.service.ItemService;

@Controller
@RequestMapping(value="/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/{itemId}")
	public String getById(@PathVariable("itemId") Long itemId,ModelMap map){
		Item item = itemService.getById(itemId);
		if(null!=item){
			map.addAttribute("item", item);
			return "item";
		}
		map.addAttribute("message", "服务器繁忙");
		return "error/exception";
	}
}
