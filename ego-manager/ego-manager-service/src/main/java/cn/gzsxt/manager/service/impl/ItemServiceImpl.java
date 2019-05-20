package cn.gzsxt.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.IDUtils;
import cn.gzsxt.manager.mapper.ItemCatMapper;
import cn.gzsxt.manager.mapper.ItemDescMapper;
import cn.gzsxt.manager.mapper.ItemMapper;
import cn.gzsxt.manager.mapper.ItemParamItemMapper;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.manager.pojo.ItemCat;
import cn.gzsxt.manager.pojo.ItemCatExample;
import cn.gzsxt.manager.pojo.ItemCatExample.Criteria;
import cn.gzsxt.manager.pojo.ItemDesc;
import cn.gzsxt.manager.pojo.ItemExample;
import cn.gzsxt.manager.pojo.ItemParamItem;
import cn.gzsxt.manager.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	@Qualifier(value="itemMapper")
	private ItemMapper itemMapper;
	
	@Autowired
	@Qualifier(value="itemCatMapper")
	private ItemCatMapper catMapper;
	
	@Autowired
	@Qualifier(value="itemDescMapper")
	private ItemDescMapper descMapper;
	
	@Autowired
	@Qualifier(value="itemParamItemMapper")
	private ItemParamItemMapper itemParamItemMapper;
	
	
	@Override
	public Item getByItemId(long id) {
		Item item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//查询商品列表
		ItemExample example = new ItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<Item> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<Item> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;

	}

	@Override
	public List<EUTreeNode> getTreeById(long id) {
		
		List<EUTreeNode> nodes=new ArrayList<EUTreeNode>();
		
		ItemCatExample example=new ItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<ItemCat> cats = catMapper.selectByExample(example);
		if(null!=cats&&cats.size()>0){
			 
			EUTreeNode node=null;
			for (ItemCat cat : cats) {
				node=new EUTreeNode();
				node.setId(cat.getId());
				node.setText(cat.getName());
				
				if(cat.getIsParent()){
					node.setState("closed");
				}else{
					node.setState("open");
				}
				
				nodes.add(node);
			}
		}
		return nodes;
	}

	@Override
	public EgoResult save(Item item, String desc, String itemParams) {
		try {
		long itemId = IDUtils.genItemId();
		Date date=new Date();
		item.setId(itemId);
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte)1);
		itemMapper.insert(item);
		if(desc != null && !"".equals(desc)){
			ItemDesc itemDesc=new ItemDesc();
			itemDesc.setItemId(itemId);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			itemDesc.setItemDesc(desc);
			descMapper.insert(itemDesc);
			
			ItemParamItem itemParamItem=new ItemParamItem();
			
			itemParamItem.setItemId(itemId);
			itemParamItem.setParamData(itemParams);
			itemParamItem.setCreated(date);
	        itemParamItem.setUpdated(date);
	
	        itemParamItemMapper.insert(itemParamItem);
		}
		
		return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(400, "当前服务器忙，请稍后再试!");
		}

	}

}
