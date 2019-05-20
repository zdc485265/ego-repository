package cn.gzsxt.rest.service;

import java.util.List;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Content;

public interface ContentService {
	public List<Content> getByContentCatId(Long contentCatId);
	public EgoResult getContentList(long cid);
}
