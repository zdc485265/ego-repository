package cn.gzsxt.manager.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.mapper.ItemMapper;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.manager.pojo.ItemExample;
import cn.gzsxt.manager.pojo.ItemExample.Criteria;
import cn.gzsxt.manager.service.RestItemService;

@Service
public class RestItemServiceImpl implements RestItemService{
	
	@Autowired
	@Qualifier(value="itemMapper")
	private ItemMapper itemMapper;

	@Override
	public EgoResult reshelfItemByIds(String ids) {
		EgoResult result=null;
		List<Long> list=null;
		try {
			String[] idArr = ids.split(",");
			if(null!=idArr){
				list=new ArrayList<Long>();
				for (String id : idArr) {
					list.add(Long.valueOf(id));
				}
			}
				Item record=new Item();
				record.setStatus((byte)1);
				ItemExample example=new ItemExample();
				Criteria criteria = example.createCriteria();
				criteria.andIdIn(list);
				int i = itemMapper.updateByExampleSelective(record, example);

			if(i>0){
				result=new EgoResult(200, "上架成功", null);
			}else{
				result=new EgoResult(500, "上架出了问题", null);
			}
		} catch (NumberFormatException e) {
			result=new EgoResult(403, "系统繁忙", null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public EgoResult instockItemByIds(String ids) {
		EgoResult result=null;
		List<Long> list=null;
		try {
			String[] idArr = ids.split(",");
			if(null!=idArr){
				list=new ArrayList<Long>();
				for (String id : idArr) {
					list.add(Long.valueOf(id));
				}
			}
				Item record=new Item();
				record.setStatus((byte)2);
				ItemExample example=new ItemExample();
				Criteria criteria = example.createCriteria();
				criteria.andIdIn(list);
				int i = itemMapper.updateByExampleSelective(record, example);

			if(i>0){
				result=new EgoResult(200, "下架成功", null);
			}else{
				result=new EgoResult(500, "下架出了问题", null);
			}
		} catch (NumberFormatException e) {
			result=new EgoResult(403, "系统繁忙", null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public EgoResult deleteItemByIds(String ids) {
		EgoResult result=null;
		List<Long> list=null;
		try {
			String[] idArr = ids.split(",");
			if(null!=idArr){
				list=new ArrayList<Long>();
				for (String id : idArr) {
					list.add(Long.valueOf(id));
				}
			}
				Item record=new Item();
				record.setStatus((byte)3);
				ItemExample example=new ItemExample();
				Criteria criteria = example.createCriteria();
				criteria.andIdIn(list);
				int i = itemMapper.updateByExampleSelective(record, example);

			if(i>0){
				result=new EgoResult(200, "删除成功", null);
			}else{
				result=new EgoResult(500, "删除出了问题", null);
			}
		} catch (NumberFormatException e) {
			result=new EgoResult(403, "系统繁忙", null);
			e.printStackTrace();
		}
		return result;
	}

}
