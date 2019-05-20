package cn.gzsxt.manager.service;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Content;

public interface ContentService {

	public EUDataGridResult  getByCatIdAndPage(Long categoryId,Integer page,Integer rows);
	public EgoResult saveByContent(Content content);
	public EgoResult editByContent(Content content);
	public EgoResult deleteByContent(Long[] ids);
}
