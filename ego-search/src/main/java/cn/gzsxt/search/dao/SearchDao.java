package cn.gzsxt.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.gzsxt.common.pojo.SearchResult;

public interface SearchDao {

	public SearchResult query(SolrQuery query) ;
}
