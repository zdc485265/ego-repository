package cn.gzsxt.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gzsxt.common.pojo.SearchItem;
import cn.gzsxt.common.pojo.SearchResult;
import cn.gzsxt.search.dao.SearchDao;

@Component
public class SearchDaoImpl implements SearchDao{
	
	@Autowired
	private HttpSolrServer httpSolrServer;

	@Override
	public SearchResult query(SolrQuery query) {
		SearchResult result =new SearchResult();
		List<SearchItem> items = new ArrayList<>();
		try {
			QueryResponse response = httpSolrServer.query(query);
			int status = response.getStatus();
			if(0==status){
				SolrDocumentList results = response.getResults();
				long numFound = results.getNumFound();
				System.out.println("满足条件的有:"+numFound+"条数据");
				SearchItem item=null;
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				for (SolrDocument s : results) {
					item=new SearchItem();
					item.setId(Long.valueOf((String)s.get("id")));
					Boolean flag=true;
					if(null!=highlighting&&highlighting.size()>0){
						Map<String, List<String>> map = highlighting.get(s.get("id"));
						if(null!=map&&map.size()>0){
							List<String> names = map.get("item_title");
							if(null!=names && names.size()>0){
								item.setTitle(names.get(0));
								flag = false;
							}
						}
					}
					if(flag){
						item.setTitle((String) s.get("item_title"));
					}
					item.setCategory_name((String) s.get("item_category_name"));
					item.setImage((String) s.get("item_image"));
					item.setPrice((Long) s.get("item_price"));
					item.setSell_point((String) s.get("item_sell_point"));
					items.add(item);

				}
				result.setItemList(items);
				result.setRecordCount(numFound);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
