package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.manager.service.ContentService;

@Controller
@RequestMapping(value="/content")
public class ContentController {

	@Autowired
	@Qualifier(value="contentServiceImpl")
	private ContentService contentService;
	
	@RequestMapping(value="/query/list")
	@ResponseBody
	public Object getByCatIdAndPage(Long categoryId,Integer page,Integer rows){
		EUDataGridResult result = contentService.getByCatIdAndPage(categoryId, page, rows);
		return result;
	}
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(Content content){
		EgoResult result=contentService.saveByContent(content);
		return result;
	}
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit(Content content){
		EgoResult result=contentService.editByContent(content);
		return result;
	}
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(Long[] ids){
		EgoResult result=contentService.deleteByContent(ids);
		return result;
	}
	
}
