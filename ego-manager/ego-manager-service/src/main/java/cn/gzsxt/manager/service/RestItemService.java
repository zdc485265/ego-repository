package cn.gzsxt.manager.service;

import cn.gzsxt.common.pojo.EgoResult;

public interface RestItemService {

	public EgoResult reshelfItemByIds(String ids);
	public EgoResult instockItemByIds(String ids);
	public EgoResult deleteItemByIds(String ids);
}
