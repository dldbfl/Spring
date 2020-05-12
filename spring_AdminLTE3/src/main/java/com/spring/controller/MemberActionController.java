package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.spring.utils.GetUploadPath;
import com.spring.utils.MakeFileName;
import com.spring.dto.BoardVO;
import com.spring.dto.MemberVO;
import com.spring.request.MemberRegistRequest;
import com.spring.request.ModifyBoardRequest;
import com.spring.request.PageMaker;
import com.spring.request.RegistBoardRequest;
import com.spring.request.SearchCriteria;
import com.spring.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberActionController {
	
	@Autowired
	private MemberService memberService;// = BoardServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("list.do")
	public String list(SearchCriteria cri, Model model
					 /*HttpServletRequest request*/)throws Exception{
			
		String url="member/list.page";
		Map<String, Object> dataMap = memberService.getMemberList(cri);
	    model.addAttribute("title", "회원리스트");
		//request 방식으로 예전처럼 넣기
		/*request.setAttribute("memberList", (List<MemberVO>)dataMap.get("memberList"));
		request.setAttribute("pageMaker", (PageMaker)dataMap.get("pageMaker"));*/
		
		//하나넣기
		//model.addAttribute("pageMaker", (PageMaker)dataMap.get("pageMaker"));
		
		//다 넣기
		model.addAllAttributes(dataMap);
		return url;
		
	}
	
	@RequestMapping("registForm.do")
	public String registForm(Model model)throws Exception{
		
		String url = "member/regist.open";
		
		return url;		
	}
	
	@RequestMapping("modifyForm.do")
	public String modifyForm(String id,Model model)throws Exception{
		
		String url = "member/modify.open";
		MemberVO member = memberService.getMember(id);
		
		model.addAttribute("member", member);
		
		return url;
		
	}
	
	@RequestMapping("remove.do")
	public String remove(String id,HttpSession session, Model model)throws Exception{
		
		String url="member/remove_success";
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (id.equals(loginUser.getId())) { // 로그인 사용자일 경우 불허함.
			url = "member/remove_denied";
		}else {
			try {
				memberService.remove(id);
				
			}catch(SQLException e){
				e.printStackTrace();
				url = "member/remove_fail";
			}
		}	
		
		return url;
	}
	
	@RequestMapping("regist.do")
	public String regist(MemberRegistRequest memberReq)throws Exception{
				
		String   url  = "member/regist_success";
		
		MemberVO member  = memberReq.toMemberVO();
		
		try {
      		memberService.regist(member);
		} catch (SQLException e) {
			e.printStackTrace();
			url="member/regist_fail";
		}	
		
		return url;	
		
	}
	
	@RequestMapping("modify.do")
	public String modify(MemberRegistRequest memberReq, String id,
			HttpSession session,HttpServletRequest request, Model model)throws Exception{
		
		String url="member/modify_success";

		MemberVO member = memberReq.toMemberVO();	
		
		try {
			memberService.modify(member);		

			MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
			if(member.getId().equals(loginUser.getId())) {
				session.setAttribute("loginUser", member);
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
			url="member/modify_fail";
			/*String oldFileName = member.getPicture();
			String uploadPath=GetUploadPath.getUploadPath("member.picture.upload");
			File oldFile=new File(uploadPath+File.separator+oldFileName);
			if(oldFile.exists()) {
				oldFile.delete();
			}*/
		}
		
		return url;
		
	}

		@RequestMapping("detail.do")
		public String detail(String id,Model model)throws Exception{
			
			String url = "member/detail.open";
			
			MemberVO member = new MemberVO();
			try {
				member = memberService.getMember(id);
				
			} catch (SQLException e) {
				url="error/500_error";
				e.printStackTrace();
			}	
			
			model.addAttribute("member", member);
			return url;
			
		}
		
		@RequestMapping("checkpassword.do")
		@ResponseBody
		public ResponseEntity<String> checkpassword(String pwd, HttpSession session)throws Exception{
			
			ResponseEntity<String> entity = null;
						
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    	if(loginUser.getPwd().equals(pwd)) {
	    		entity = new ResponseEntity<String>(HttpStatus.OK);
	    	}else {
	    		entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    	return entity;
		}
		
		@RequestMapping("disabled.do")
		public String disabled(String id,HttpSession session)throws Exception{
			
			String url = "member/disabled_success";
			
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
			if (id.equals(loginUser.getId())) { // 로그인 사용자일 경우 불허함.
				url = "member/disabled_denied";
			} else { //로그인 사용자가 아닐경우 실행.
				try {
					memberService.disabled(id);
				} catch (SQLException e) {
					e.printStackTrace();
					url = "member/disabled_fail";
				}
			}
			return url;
			
		}
		
		@RequestMapping("enabled.do")
		public String enabled(String id)throws Exception{
			
			String url = "member/enabled_success";
			
			try {
				memberService.enabled(id);
			} catch (SQLException e) {
				e.printStackTrace();
				url = "member/enabled_fail";
			}
			
			return url;
			
		}
		

		@Resource(name="picturePath")
		private String picturePath;
		//'root-context'에서 bean의 id로 의존성 주입해주는 것, Autowired와 비슷하지만 Autowired는 타입으로 의존성 주입해줌.
		
		
		@RequestMapping(value="picture.do", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
		@ResponseBody
		public ResponseEntity<String> uploadpicture(MultipartFile pictureFile,
												    String oldPicture)throws Exception{
			
			ResponseEntity<String> entity=null;		
			File file = new File(picturePath);
			if(!file.mkdirs()) {
				System.out.println(picturePath+"가 이미 존재하거나 실패했습니다.");
			};
			
			try {
				
				//중복파일명 해결..
				UUID uid=UUID.randomUUID();
				String originalName = pictureFile.getOriginalFilename();
				System.out.println("originalName"+originalName);
				String saveName=uid.toString().replace("-", "")+"$$"+originalName;
				System.out.println("saveName"+saveName);
				//파일 저장
				File target=new File(picturePath,saveName);	
				pictureFile.transferTo(target);			
								

				//이전 사진 삭제
				File oldFile=new File(picturePath+File.separator+oldPicture);
				if(oldFile.exists()) {
					oldFile.delete();
				}
							
				
				entity = new ResponseEntity<String>(saveName,HttpStatus.CREATED);
			}catch(Exception e) {
				e.printStackTrace();			
				entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
			}			
			
			
			
			return entity;
			
		}
		

		
		@RequestMapping("/picture/get.do")
		@ResponseBody
		public ResponseEntity<byte[]> getPicture(String picture)throws Exception{
		
			ResponseEntity<byte[]> entity=null;	
			
			String imgPath = this.picturePath;
			
			InputStream in=null;
			try {
			
				in=new FileInputStream(new File(imgPath,picture));
			
				entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),HttpStatus.CREATED);
			
			}catch(IOException e){
				
				e.printStackTrace();
			
				entity=new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
			
			}finally{
				
				in.close();
				
			}						
				return entity;
		}
	}
	