package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.common.pojo.PictureResult;
import cn.gzsxt.manager.service.PicService;

@Controller
public class PicController {

	@Autowired
	@Qualifier(value="picServiceImpl")
	private PicService picService;
	
	@RequestMapping(value="/pic/upload")
	@ResponseBody
	public Object upload(MultipartFile uploadFile){
		PictureResult result = picService.upload(uploadFile);
		return result;
	}
}
