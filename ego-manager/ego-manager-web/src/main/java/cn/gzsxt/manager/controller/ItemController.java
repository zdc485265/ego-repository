package cn.gzsxt.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.manager.service.ItemService;

@Controller
@RequestMapping(value="/item")
public class ItemController {

	@Autowired
	@Qualifier(value="itemServiceImpl")
	private ItemService itemService;
	
	@RequestMapping("/getById")
	@ResponseBody
	public Item getByItemId(Long itemId){
		Item item = itemService.getByItemId(itemId);
		return item;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	 }
	
	@RequestMapping("/cat/list")
	@ResponseBody
	public Object getTree(@RequestParam(defaultValue="0")Long id) {
		List<EUTreeNode> nodes = itemService.getTreeById(id);
		return nodes;
	 }
	@RequestMapping("/save")
	@ResponseBody
	public Object save(Item item,String desc,String itemParams){
		EgoResult result = itemService.save(item, desc,itemParams);
		
		return result;
	}

}
