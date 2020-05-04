package com.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.request.member.RegistMemberRequest;
import com.spring.dto.MemberVO;
import com.spring.request.PageMaker;
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
	
}