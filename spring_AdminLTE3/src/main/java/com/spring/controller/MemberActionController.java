package com.spring.controller;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.utils.GetUploadPath;
import com.spring.request.RegistMemberRequest;
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
	public void list(SearchCriteria cri, Model model
					 /*HttpServletRequest request*/)throws Exception{
				
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		
		//request 방식으로 예전처럼 넣기
		/*request.setAttribute("memberList", (List<MemberVO>)dataMap.get("memberList"));
		request.setAttribute("pageMaker", (PageMaker)dataMap.get("pageMaker"));*/
		
		//하나넣기
		//model.addAttribute("pageMaker", (PageMaker)dataMap.get("pageMaker"));
		
		//다 넣기
		model.addAllAttributes(dataMap);
		
	}
	
	@RequestMapping("registForm.do")
	public String registForm(Model model)throws Exception{
		
		String url = "member/regist";
		
		return url;		
	}
	
	@RequestMapping("modifyForm.do")
	public String modifyForm(String id,Model model)throws Exception{
		
		String url = "member/modify";
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
			
			String url = "member/detail";
			
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
		public void checkpassword(String pwd,HttpServletRequest request,
				HttpServletResponse response, HttpSession session)throws Exception{
			
			String result = null;
						
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    	if(loginUser.getPwd().equals(pwd)) {
	    		result= "SUCCESS";
	    	}
	    		    	
			response.getWriter().print(result);
			
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
		
		
	}
	