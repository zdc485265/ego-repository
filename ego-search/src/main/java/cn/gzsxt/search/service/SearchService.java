package cn.gzsxt.search.service;

import java.util.List;

import org.apache.solr.common.SolrInputDocument;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.SearchItem;
import cn.gzsxt.common.pojo.SearchResult;

public interface SearchService {

	public List<SearchItem> gatherData();
	public List<SolrInputDocument> getDocuments();
	public EgoResult  createIndex();
	public SearchResult query(String q, Integer page);
}
