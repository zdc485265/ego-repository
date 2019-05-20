package cn.gzsxt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/category/{cid}")
	@ResponseBody
	public EgoResult getContentList(@PathVariable Long cid) {
		EgoResult result = null;
		try {
			result = contentService.getContentList(cid);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(500, e.getMessage());
		}
		return result;
	}
}

