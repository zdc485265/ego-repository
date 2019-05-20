package cn.gzsxt.manager.service;

import java.util.List;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;

public interface ContentCategoryService {
	
	public List<EUTreeNode> getNodesByParentId(Long parent_id) ;
	public EgoResult createByName(Long parentId,String name);
	public EgoResult updateByName(Long id,String name);
	public EgoResult deleteByName(Long id);
	
}
