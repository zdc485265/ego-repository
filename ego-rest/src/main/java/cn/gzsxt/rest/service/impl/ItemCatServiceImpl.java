package cn.gzsxt.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.Menu;
import cn.gzsxt.common.pojo.MenuNode;
import cn.gzsxt.manager.mapper.ItemCatMapper;
import cn.gzsxt.manager.pojo.ItemCat;
import cn.gzsxt.manager.pojo.ItemCatExample;
import cn.gzsxt.manager.pojo.ItemCatExample.Criteria;
import cn.gzsxt.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	@Qualifier(value="itemCatMapper")
	private ItemCatMapper CatMapper;

	@Override
	public Menu getMenu() {
		List<?> list = getMenuNodeByParentId(0);
		Menu menu=new Menu();
		menu.setData(list);
		return menu;
	}
	public List<?> getMenuNodeByParentId(long parant_id){
		ItemCatExample example=new ItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parant_id);
		List<ItemCat> cats = CatMapper.selectByExample(example);
		List nodes=new ArrayList<>();
		MenuNode node=null;
		if(null!=cats&&cats.size()>0){
			for (ItemCat cat : cats) {
				if(cat.getIsParent()){
					node=new MenuNode();
					node.setU("/products/"+cat.getId()+".html");
					node.setN("<a href='/products/"+cat.getId()+".html'>"+cat.getName()+"</a>");
					node.setI(getMenuNodeByParentId(cat.getId()));
					nodes.add(node);
				}else{
					nodes.add("/products/"+cat.getId()+".html|"+cat.getName());
				}

				
			}
		}
		
		return nodes;
	}

}
