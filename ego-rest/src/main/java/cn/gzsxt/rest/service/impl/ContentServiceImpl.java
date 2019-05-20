package cn.gzsxt.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.manager.mapper.ContentMapper;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.manager.pojo.ContentExample;
import cn.gzsxt.manager.pojo.ContentExample.Criteria;
import cn.gzsxt.rest.service.ContentService;
import redis.clients.jedis.JedisCluster;

@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Autowired
	private ContentMapper contentMapper;

	@Override
	public List<Content> getByContentCatId(Long contentCatId) {
		List<Content> contents=null;
		try {
			String jsonData = jedisCluster.hget("ego:content", contentCatId+"");
			if(null!=jsonData&&!"".equals(jsonData)){
				contents = JsonUtils.jsonToList(jsonData, Content.class);
			}else{
				ContentExample example=new ContentExample();
				Criteria criteria = example.createCriteria();
				criteria.andCategoryIdEqualTo(contentCatId);
				contents = contentMapper.selectByExample(example);
				jedisCluster.hset("ego:content",contentCatId+"", JsonUtils.objectToJson(contents));
			}
		} catch (Exception e) {
			ContentExample example=new ContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(contentCatId);
			contents = contentMapper.selectByExample(example);
			e.printStackTrace();
		}
		return contents;
	}

	@Override
	public EgoResult getContentList(long cid) {
		ContentExample example = new ContentExample();
		//添加条件
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<Content> list = contentMapper.selectByExample(example);
		return EgoResult.ok(list);
	}

}
