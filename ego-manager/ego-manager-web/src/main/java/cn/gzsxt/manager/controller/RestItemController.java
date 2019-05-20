package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.service.RestItemService;

@Controller
@RequestMapping(value="/rest/item")
public class RestItemController {
	
	@Autowired
	@Qualifier(value="restItemServiceImpl")
	private RestItemService restItemService;

	@RequestMapping(value="/reshelf")
	@ResponseBody
	public Object reshelfItem(String ids){
		EgoResult result =restItemService.reshelfItemByIds(ids);
		return result;
	}
	@RequestMapping(value="/instock")
	@ResponseBody
	public Object instockItem(String ids){
		EgoResult result =restItemService.instockItemByIds(ids);
		return result;
	}
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteItem(String ids){
		EgoResult result =restItemService.deleteItemByIds(ids);
		return result;
	}
}
