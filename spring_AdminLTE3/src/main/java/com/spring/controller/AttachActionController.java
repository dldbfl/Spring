package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.utils.MakeFileName;
import com.spring.dao.AttachDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.PdsVO;
import com.spring.request.ModifyPdsRequest;
import com.spring.request.PageMaker;
import com.spring.request.RegistPdsRequest;
import com.spring.request.SearchCriteria;
import com.spring.service.PdsService;
import com.spring.utils.CreatePageMaker;

@Controller
@RequestMapping("/attach/*")
public class AttachActionController {
	
	@Autowired
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@RequestMapping("getFile.do")
	public String getfile(int pno, int ano,
						HttpServletRequest request,
						HttpServletResponse response,
					   ModelAndView mnv) throws Exception {
		
		String url=null;
		
		
			PdsVO pds = pdsService.getPds(pno);
			
			List<AttachVO> attachList = pds.getAttachList();
			
			String fileName = null;
			String filePath = null;
			
			for (AttachVO attach : attachList) {
				if (ano == attach.getAno()) {
					fileName = attach.getFileName();
					filePath = attach.getUploadPath();
					break;
				}
			}
			
			sendFile(request,response,fileName,filePath);
				
		return url;
	}
	
	private void sendFile(HttpServletRequest request, HttpServletResponse response,
			  String fileName, String filePath) throws Exception{

	filePath = filePath + File.separator + fileName;
	
	// 보낼 파일 설정.
	File downloadFile = new File(filePath);
	FileInputStream inStream = new FileInputStream(downloadFile);
	
	ServletContext context = request.getServletContext();
	// 파일 포맷으로 MIME를 결정한다.
	String mimeType = context.getMimeType(filePath);
	if (mimeType == null) {
	mimeType = "application/octet-stream";
	}
	
	
	
	// response 수정.
	response.setContentType(mimeType);
	response.setContentLength((int) downloadFile.length());
	
	// 파일명 한글깨짐 방지 : utf-8
	String downloadFileName = 
		new String(downloadFile.getName().getBytes("utf-8"), "ISO-8859-1");
	downloadFileName = MakeFileName.parseFileNameFromUUID(downloadFileName, "\\$\\$");
	
	// response header setting
	String headerKey = "Content-Disposition";
	String headerValue = String.format("attachment; filename=\"%s\"",downloadFileName);
	response.setHeader(headerKey, headerValue);
	
	// 파일 내보내기
	OutputStream outStream=null;
	try {
	outStream = response.getOutputStream();
	byte[] buffer = new byte[4096];
	int bytesRead = -1;
	
	while ((bytesRead = inStream.read(buffer)) != -1) {
		outStream.write(buffer, 0, bytesRead);
	}
	}finally {
	if(inStream!=null)inStream.close();
	if(outStream!=null)outStream.close();
	}
	}			
	
	}
	
	
	