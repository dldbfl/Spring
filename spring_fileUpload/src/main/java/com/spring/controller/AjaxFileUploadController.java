package com.spring.controller;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.utils.FileUpload;
import com.spring.utils.MediaUtils;

@Controller
public class AjaxFileUploadController {
	
	@Resource(name="uploadPath")
	private String uploadPath;
	//'root-context'에서 bean의 id로 의존성 주입해주는 것, Autowired와 비슷하지만 Autowired는 타입으로 의존성 주입해줌.
	
	
	@RequestMapping("/ajaxFileUploadForm")
	public void ajaxFileUploadForm() {}
	
	//한글올리면 깨지기 때문에 적용	
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		ResponseEntity<String> entity=null;
		
		String sourcePath = null;
		
		try {
			sourcePath = FileUpload.uploadFile(file,uploadPath);
			entity = new ResponseEntity<String>(sourcePath,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();			
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}			
		
		return entity;
	}
	

	@RequestMapping(value="/deleteFile",method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> deleteFile(@RequestBody Map<String,String> dataMap)
									throws Exception{
	
		ResponseEntity<String> entity=null;
		
		String fileName = dataMap.get("fileName");
		System.out.println("fileName : "+fileName);
		
		
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType mType=MediaUtils.getMediaType(formatName);
		
		try {
			if(mType!=null){
				String front=fileName.substring(0, 12);
				String end=fileName.substring(14);
				new File(uploadPath+(front+end).replace('/', File.separatorChar))
					.delete();			
			}		
			new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
			
			entity = new ResponseEntity<String>("deleted",HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
}
