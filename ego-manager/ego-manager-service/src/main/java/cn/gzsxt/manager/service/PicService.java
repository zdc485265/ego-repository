package cn.gzsxt.manager.service;

import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.common.pojo.PictureResult;

public interface PicService {
	
	public PictureResult upload(MultipartFile uploadFile);
}
