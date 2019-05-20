package cn.gzsxt.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.mapper.ItemParamMapper;
import cn.gzsxt.manager.pojo.ItemParam;
import cn.gzsxt.manager.pojo.ItemParamExample;
import cn.gzsxt.manager.pojo.ItemParamExample.Criteria;
import cn.gzsxt.manager.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService{

	@Autowired
	@Qualifier(value="itemParamMapper")
	private ItemParamMapper itemParamMapper;
	
	@Override
	public EgoResult queryCatalogById(long cid) {
		ItemParamExample example=new ItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<ItemParam> itemParams = itemParamMapper.selectByExampleWithBLOBs(example);
		if (itemParams != null && !itemParams.isEmpty()) {
			return EgoResult.ok(itemParams.get(0));
		}
		// 查询成功但是没有查到数据
		return EgoResult.ok();
	}

	@Override
	public EgoResult saveItemParam(long cid, String paramData) {
		ItemParam record=new ItemParam();
		record.setItemCatId(cid);
		record.setParamData(paramData);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		itemParamMapper.insert(record);
		
		return EgoResult.ok();
	}

	@Override
	public EUDataGridResult getItemParamList(Integer page, Integer rows) {
		//查询商品列表
		ItemParamExample example = new ItemParamExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<ItemParam> list =itemParamMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<ItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
