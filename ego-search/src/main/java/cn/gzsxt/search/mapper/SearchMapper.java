package cn.gzsxt.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.gzsxt.common.pojo.SearchItem;

public interface  SearchMapper {
	
	List<SearchItem> gatherData();
}
