package cn.gzsxt.portal.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.common.utils.HttpClientUtils;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Override
	public Item getById(Long itemId) {
		Item item=null;
		String get = HttpClientUtils.doGet(REST_BASE_URL+"/item/"+itemId);
		if(null!=get&&!"".equals(get)){
			item = JsonUtils.jsonToPojo(get, Item.class);
		}
		return item;
	}

}
