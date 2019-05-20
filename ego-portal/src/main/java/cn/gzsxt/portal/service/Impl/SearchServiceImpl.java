package cn.gzsxt.portal.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.common.pojo.SearchResult;
import cn.gzsxt.common.utils.HttpClientUtils;
import cn.gzsxt.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	@Value("${SEARCH_ITEM_URL}")
	private String SEARCH_ITEM_URL;
	
	@Override
	public SearchResult query(String q, Integer page) {
		SearchResult result = null;
		Map<String,String> params = new HashMap<>();
		params.put("q", q);
		params.put("page", page+"");
		String jsonData = HttpClientUtils.doGet(SEARCH_BASE_URL+SEARCH_ITEM_URL, params);
		if(null!=jsonData){
			result = JsonUtils.jsonToPojo(jsonData, SearchResult.class);
		}
		return result;

	}

}
