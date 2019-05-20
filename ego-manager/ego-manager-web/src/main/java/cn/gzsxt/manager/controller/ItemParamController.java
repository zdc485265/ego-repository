package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.service.ItemParamService;

@Controller
@RequestMapping(value="/item/param")
public class ItemParamController {

	@Autowired
	@Qualifier(value="itemParamServiceImpl")
	private ItemParamService itemParamService;
	
	@RequestMapping(value="/query/itemcatid/{cid}")
	@ResponseBody
	public Object queryCatalogById(@PathVariable long cid){
		EgoResult result = itemParamService.queryCatalogById(cid);
		return result;
	}
	
	@RequestMapping(value="/save/{cid}")
	@ResponseBody
	public Object saveItemParam(@PathVariable Long cid, String paramData) {
		EgoResult result = itemParamService.saveItemParam(cid, paramData);
		return result;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(Integer page, Integer rows) {
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	 }
}
