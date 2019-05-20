package cn.gzsxt.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.common.pojo.SearchResult;
import cn.gzsxt.portal.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String query(String q,@RequestParam(defaultValue="1")Integer page,ModelMap map){
		SearchResult result = searchService.query(q, page);
		map.put("query", q);
		map.put("totalPages", result.getTotalPages());
		map.put("itemList", result.getItemList());
		map.put("page", page);
		return "search";
	}

}
