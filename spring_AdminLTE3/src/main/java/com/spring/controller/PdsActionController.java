package com.spring.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/pds/*")
public class PdsActionController {
	
	@Autowired
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Autowired
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception {
		
		String url = "pds/list.page";
		
		Map<String, Object> dataMap = pdsService.getList(cri);
		
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;	
	}
	

	@RequestMapping("/registForm.do")
	public ModelAndView registForm(ModelAndView mnv) throws Exception {
		String url = "pds/regist.open";
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value = "regist.do", method = RequestMethod.POST, 
			produces = "text/plain;charset=utf-8")
	public String regist(RegistPdsRequest registReq, 
					   HttpServletRequest request,
					   HttpServletResponse response) throws Exception {
		
		String url = "pds/regist_success";
		
		List<AttachVO> attachList = saveFile(registReq,request,response);
		
		PdsVO pds = registReq.toPdsVO();
		pds.setAttachList(attachList);
		
		try {
			pdsService.regist(pds);
		}catch(SQLException e) {
			e.printStackTrace();
			url = "pds/regist_fail";
		}
		return url;
		
			
	}

	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv,
			//@ModelAttribute("cri") SearchCriteria cri,
			int pno) throws Exception {
		String url = "pds/modify.open";

		PdsVO pds = pdsService.getPds(pno);

		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);


/*		Map<String, Object> dataMap = pdsService.getList(cri);		
		mnv.addAllObjects(dataMap);		*/
		
		return mnv;
	}
	
	@RequestMapping(value = "modify.do", method = RequestMethod.POST, 
			produces = "text/plain;charset=utf-8")
	public String modify(ModifyPdsRequest modifyReq, int pno,
					   HttpServletRequest request,
					   HttpServletResponse response,
					   Model model) throws Exception {
		
		String url = "pds/modify_success";
		
		List<AttachVO> attachList = modifyFile(modifyReq,request,response);
		PdsVO pds = modifyReq.toPdsVO();
		
		pds.setAttachList(attachList);
		try {
			pdsService.modify(pds);
		}catch(SQLException e) {
			e.printStackTrace();
			url = "pds/modify_fail";
		}
		
		model.addAttribute("pno",modifyReq.getPno());
		System.out.println(modifyReq.getPno());
		return url;
		
		
	}
	
	@RequestMapping("detail.do")
	public ModelAndView detail(ModelAndView mnv, int pno, String from) throws Exception {
		String url = "pds/detail.open";
		
		PdsVO pds =null; 
		if(from!=null && from.equals("modify")) {
			pds = pdsService.getPds(pno);
		}else {
			pds = pdsService.read(pno);
		}
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);

		return mnv;
	}
	

	@RequestMapping("remove.do")
	public String remove(int pno, PageMaker pageMaker, 
						 HttpServletRequest request,Model model)throws Exception{
		
		String url = "pds/remove_success";

		
		List<AttachVO> attachList = pdsService.getPds(pno).getAttachList();
		
		// 각 attachlist 를 이용 파일을 삭제.
		if(attachList != null) {
			for (AttachVO attach : attachList) {
				String storedFilePath = attach.getUploadPath() + File.separator
						+ attach.getFileName();
				File file = new File(storedFilePath);
				if (file.exists()) {
					file.delete();
				}
		
			}
		}
		//DB 내용 삭제 ( 종속 삭제로 attach도 삭제됨.)
		pdsService.remove(pno);
		
		return url;
	}
	
	
	@Resource(name = "fileUploadPath")
	private String fileUploadPath;
	
	private List<AttachVO> saveFile(RegistPdsRequest registReq,
									HttpServletRequest request,
									HttpServletResponse response)
											throws Exception{ 
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		if (registReq.getUploadFile() != null) {
			for (MultipartFile multi : registReq.getUploadFile()) {
				String fileName = UUID.randomUUID().toString().replace("-", "")
						+ "$$" + multi.getOriginalFilename();
				File target = new File(fileUploadPath, fileName);

				if (!target.exists()) {
					target.mkdirs();
				}

				multi.transferTo(target);

				AttachVO attach = new AttachVO();
				attach.setUploadPath(fileUploadPath);
				attach.setFileName(fileName);
				attach.setFileType(fileName.substring(
						fileName.lastIndexOf('.') + 1).toUpperCase());

				attachList.add(attach);
			}
		}
		
		
		return attachList;
	}
	
	private List<AttachVO> modifyFile(ModifyPdsRequest modifyReq,
								      HttpServletRequest request,
									  HttpServletResponse response
									  )	throws Exception{ 
		
		//파일 넣을꺼 리스트객체만들어주기
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		
		//파일이 있으면 ;;
		if (modifyReq.getUploadFile() != null) {
			for (MultipartFile multi : modifyReq.getUploadFile()) {
				String fileName = UUID.randomUUID().toString().replace("-", "")
								  + "$$" + multi.getOriginalFilename();
				File target = new File(fileUploadPath, fileName);
		
				if (!target.exists()) {
					 target.mkdirs();
					}
				//저장
				multi.transferTo(target);				
				
				//삭제 (삭제한 파일이 있다면)(
				if( modifyReq.getDeleteFile() != null) {
					for(int deleteFile : modifyReq.getDeleteFile()) {
						
						AttachVO attach = attachDAO.selectAttachByAno(deleteFile);
						
						String filePath = attach.getUploadPath() 
								+ File.separator + attach.getFileName();
						File targetFile = new File(filePath);
						
						targetFile.delete(); //파일 삭제
						
						attachDAO.deleteAttach(deleteFile); //DB 삭제
						System.out.println(deleteFile+"번 삭제했소.");
					}		
				}
		
				AttachVO attach = new AttachVO();
				attach.setUploadPath(fileUploadPath);
				attach.setFileName(fileName);
				attach.setFileType(fileName.substring(
				fileName.lastIndexOf('.') + 1).toUpperCase());
				
				attachList.add(attach);
			}
		}
		
		
		return attachList;
	}
	
	
}