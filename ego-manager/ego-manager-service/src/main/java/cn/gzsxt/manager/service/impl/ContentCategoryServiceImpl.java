package cn.gzsxt.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.mapper.ContentCategoryMapper;
import cn.gzsxt.manager.pojo.ContentCategory;
import cn.gzsxt.manager.pojo.ContentCategoryExample;
import cn.gzsxt.manager.pojo.ContentCategoryExample.Criteria;
import cn.gzsxt.manager.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
	
	@Autowired
	@Qualifier(value="contentCategoryMapper")
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getNodesByParentId(Long parent_id) {
		ContentCategoryExample example=new ContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parent_id);
		List<ContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> nodes=new ArrayList<>();
		if(null!=list && list.size()>0){
			EUTreeNode node=null;
			for (ContentCategory cc : list) {
				node=new EUTreeNode();
				node.setId(cc.getId());
				node.setText(cc.getName());
				node.setState(cc.getIsParent()?"closed":"open");
				nodes.add(node);
			}
		}
		return nodes;
	}

	@Override
	public EgoResult createByName(Long parentId, String name) {
		ContentCategory record=new ContentCategory();
		
		record.setIsParent(false);
		record.setCreated(new Date());
		record.setParentId(parentId);
		record.setUpdated(new Date());
		record.setName(name);
		record.setSortOrder(1);
		record.setStatus(1);
		int num = contentCategoryMapper.insertSelective(record);
		if(num>0){
			ContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
			if(!category.getIsParent()){
				category.setIsParent(true);
				int num2 = contentCategoryMapper.updateByPrimaryKeySelective(category);
				if(num2>0){
					return EgoResult.ok(record);
				}
			}
		}
		
		return EgoResult.build(300, "添加失败");

	}

	@Override
	public EgoResult updateByName(Long id, String name) {
		ContentCategory record=new ContentCategory();
		record.setId(id);
		record.setName(name);
		int num = contentCategoryMapper.updateByPrimaryKeySelective(record);
		if (num>0) {
			return EgoResult.ok(record);
		}
		return EgoResult.build(300, "修改未成功");
	}

	@Override
	public EgoResult deleteByName(Long id) {
		ContentCategory record=new ContentCategory();
		record.setId(id);
		record.setStatus(2);
		int num = contentCategoryMapper.updateByPrimaryKeySelective(record);
		if (num>0) {
			return EgoResult.ok(record);
		}
		return EgoResult.build(300, "删除未成功");
	}

}
