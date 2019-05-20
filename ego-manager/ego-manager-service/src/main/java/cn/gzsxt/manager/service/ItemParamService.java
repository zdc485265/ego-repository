package cn.gzsxt.manager.service;

import cn.gzsxt.common.pojo.EUDataGridResult;
import cn.gzsxt.common.pojo.EgoResult;

public interface ItemParamService {
	public EgoResult queryCatalogById(long cid);
	public EgoResult saveItemParam(long cid,String paramData);
	public EUDataGridResult getItemParamList(Integer page, Integer rows);
}
