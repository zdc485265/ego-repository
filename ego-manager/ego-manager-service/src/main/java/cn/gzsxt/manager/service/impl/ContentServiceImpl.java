package cn.gzsxt.manager.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.mapper.ContentMapper;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.manager.pojo.ContentExample;
import cn.gzsxt.manager.pojo.ContentExample.Criteria;
import cn.gzsxt.manager.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	@Qualifier(value="contentMapper")
	private ContentMapper contentMapper;
	
	@Override
	public EUDataGridResult getByCatIdAndPage(Long categoryId, Integer page, Integer rows) {
		ContentExample example=new ContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		PageHelper.startPage(page, rows);
		List<Content> list = contentMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<Content> info=new PageInfo<>(list);
		EUDataGridResult result =new EUDataGridResult();
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}

	@Override
	public EgoResult saveByContent(Content content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		int num = contentMapper.insertSelective(content);
		if (num>0) {
			return EgoResult.ok(content);
		}
		return EgoResult.build(300, "添加内容失败");
	}

	@Override
	public EgoResult editByContent(Content content) {
		int num = contentMapper.updateByPrimaryKeySelective(content);
		if (num>0) {
			return EgoResult.ok(content);
		}
		return EgoResult.build(300, "修改内容失败");
	}

	@Override
	public EgoResult deleteByContent(Long[] ids) {
		List<Long> list = Arrays.asList(ids);
		ContentExample example=new ContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(list);
		int num = contentMapper.deleteByExample(example);
		if (num>0) {
			return EgoResult.ok();
		}
		return EgoResult.build(300, "删除内容失败");
	}
}
