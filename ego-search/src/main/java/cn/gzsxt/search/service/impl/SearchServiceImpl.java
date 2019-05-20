package cn.gzsxt.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.SearchItem;
import cn.gzsxt.common.pojo.SearchResult;
import cn.gzsxt.search.dao.SearchDao;
import cn.gzsxt.search.mapper.SearchMapper;
import cn.gzsxt.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired 
	private HttpSolrServer solrServer;
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public List<SearchItem> gatherData() {
		
		List<SearchItem> list = searchMapper.gatherData();
		return list;
	}

	@Override
	public List<SolrInputDocument> getDocuments() {
		List<SolrInputDocument> solrs=new ArrayList();
		List<SearchItem> list = gatherData();
		if(null!=list){
			SolrInputDocument doc =null;
			for (SearchItem item : list) {
				doc=new SolrInputDocument();
				doc.addField("id", item.getId());
				doc.addField("item_title", item.getTitle());
				doc.addField("item_sell_point", item.getSell_point());
				doc.addField("item_price", item.getPrice());
				doc.addField("item_image", item.getImage());
				doc.addField("item_category_name", item.getCategory_name());
				
				solrs.add(doc);
			}
		}
		return solrs;
	}

	@Override
	public EgoResult createIndex() {
		try {
			solrServer.add(getDocuments());
			solrServer.commit();
			return EgoResult.ok();

		} catch (SolrServerException | IOException e) {
			
			e.printStackTrace();
			return EgoResult.build(400, e.getMessage());
		}
	}

	@Override
	public SearchResult query(String q, Integer page) {
		SolrQuery query=new SolrQuery();
		if(null!=q&&!"".equals(q)){
			query.set("q","item_title:"+q);
			
			query.setHighlight(true);
			query.setHighlightSimplePre("<font style='color:red'>");
			query.setHighlightSimplePost("</font>");
			query.addHighlightField("item_title");
		}else{
			query.set("q", "*:*");
		}
		
		if(null==page){
			page=1;
		}
		int rows=60;
		
		query.setStart((page-1)*rows);
		query.setRows(rows);
		SearchResult result = searchDao.query(query);
		result.setCurPage(page);
		result.setTotalPages((int)((result.getRecordCount()-1)/rows+1));
		return result;
	}

}
