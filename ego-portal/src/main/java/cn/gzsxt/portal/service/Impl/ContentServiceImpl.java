package cn.gzsxt.portal.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.ADItem;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.common.utils.HttpClientUtils;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;


	@Override
	public String getAdItemList() {
		List<ADItem> ads=new ArrayList<>();
		String result = HttpClientUtils.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
		if(null!=result&&!"".equals(result)){
			EgoResult egoResult = EgoResult.formatToList(result,Content.class);
			List<Content> contentList = (List<Content>)  egoResult.getData();
			if(null!=contentList){
				ADItem item=null;
				for (Content content : contentList) {
					item = new ADItem();
					item.setSrc(content.getPic());
					item.setSrcB(content.getPic2());
					item.setAlt(content.getTitleDesc());
					item.setHref(content.getUrl());
					ads.add(item);
				}
			}
		}
		return JsonUtils.objectToJson(ads);
	}

}
