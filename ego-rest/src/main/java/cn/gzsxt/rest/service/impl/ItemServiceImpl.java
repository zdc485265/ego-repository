package cn.gzsxt.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.manager.mapper.ItemMapper;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMapper mapper;

	@Override
	public Item getById(Long itemId) {
		Item item = mapper.selectByPrimaryKey(itemId);
		return item;
	}

}
