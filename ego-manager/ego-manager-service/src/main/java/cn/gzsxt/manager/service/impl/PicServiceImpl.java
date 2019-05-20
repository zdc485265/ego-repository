package cn.gzsxt.manager.service.impl;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.common.pojo.IDUtils;
import cn.gzsxt.common.pojo.PictureResult;
import cn.gzsxt.manager.service.PicService;

@Service
public class PicServiceImpl implements PicService{
	
	@Value("${FTP_HOST}")
	private String	FTP_HOST;
	@Value("${FTP_PORT}")
	private Integer	FTP_PORT;
	@Value("${FTP_USER}")
	private String	FTP_USER;
	@Value("${FTP_PASSWD}")
	private String	FTP_PASSWD;
	@Value("${FTP_BASE_URL}")
	private String	FTP_BASE_URL;
	@Value("${PICTURE_BASE_URL}")
	private String	PICTURE_BASE_URL;

	@Override
	public PictureResult upload(MultipartFile uploadFile) {
		PictureResult result=new PictureResult();
		
		FTPClient client=new FTPClient();
		try {
			client.connect(FTP_HOST, FTP_PORT);
			client.login(FTP_USER, FTP_PASSWD);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.changeWorkingDirectory(FTP_BASE_URL);
			String OriginalFilename = uploadFile.getOriginalFilename();
			String fileType=OriginalFilename.substring(OriginalFilename.lastIndexOf("."));
			String fileName = IDUtils.genImageName();
			boolean flag = client.storeFile(fileName+fileType,uploadFile.getInputStream());
			if(flag){
				System.out.println("上传成功");
				result.setError(0);
				result.setUrl(PICTURE_BASE_URL+"/"+fileName+fileType);
				
			}else {
				System.out.println("上传失败");
				result.setError(1);
				result.setMessage("服务器繁忙");
			}
			client.disconnect();
		} catch (Exception e) {
			result.setError(1);
			result.setMessage("上传异常");
			e.printStackTrace();
		}
		
		return result;
	}

}
