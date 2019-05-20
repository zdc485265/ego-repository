package cn.gzsxt.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	@Qualifier(value="contentCategoryServiceImpl")
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public Object getByParentId(@RequestParam(name="id",defaultValue="0")Long parent_id){   
		List<EUTreeNode> list = contentCategoryService.getNodesByParentId(parent_id);
		
		return list;
	}
	
	@RequestMapping(value="/create")
	@ResponseBody
	public Object create(Long parentId,String name){   
		EgoResult result = contentCategoryService.createByName(parentId,name);
		
		return result;
	}
	@RequestMapping(value="/update")
	@ResponseBody
	public Object update(Long id,String name){   
		EgoResult result = contentCategoryService.updateByName(id,name);
		
		return result;
	}
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(Long id){   
		EgoResult result = contentCategoryService.deleteByName(id);
		
		return result;
	}
	
}
