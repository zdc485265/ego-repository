package cn.gzsxt.manager.service;

import java.util.List;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Item;

public interface ItemService {
	public Item getByItemId(long id);	
	public EUDataGridResult getItemList(int page, int rows);
	public List<EUTreeNode> getTreeById(long id);
	public EgoResult save(Item item,String desc,String itemParams);
}
