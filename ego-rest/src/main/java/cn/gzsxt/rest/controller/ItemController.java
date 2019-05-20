package cn.gzsxt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.rest.service.ItemService;

@Controller
@RequestMapping(value="/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/{itemId}")
	@ResponseBody
	public Object getById(@PathVariable("itemId") Long itemId){
		Item item = itemService.getById(itemId);
		return item;
	} 
}
