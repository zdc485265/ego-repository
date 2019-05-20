package cn.gzsxt.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.SearchResult;
import cn.gzsxt.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	@Qualifier(value="searchServiceImpl")
	private SearchService searchService;
	
	
	
	@RequestMapping(value="/createIndex")
	@ResponseBody
	public Object createIndex(){
		EgoResult result = searchService.createIndex();
		return result;
	}
	
	@RequestMapping(value="/query")
	@ResponseBody
	public Object query(String q,Integer page){
		SearchResult result = searchService.query(q, page);
		return result;
	}

}
