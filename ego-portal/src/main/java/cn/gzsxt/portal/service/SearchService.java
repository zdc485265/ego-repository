package cn.gzsxt.portal.service;

import cn.gzsxt.common.pojo.SearchResult;

public interface SearchService {
	
	public SearchResult query(String q, Integer page);
}
