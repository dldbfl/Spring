package com.spring.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.MemberService;
import com.spring.dto.MemberVO;
import com.spring.exception.InvalidPasswordException;
import com.spring.exception.NotFoundIDException;

@Controller
@RequestMapping("/commons/*")
public class LoginActionController {
	
	@Autowired
	private MemberService memberService;//=MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	
	
	@RequestMapping("loginForm.do")
	public String loginForm()throws Exception{
		
		String url = "commons/loginForm";
		
		return url;		
	}
	
	@RequestMapping("login.do")
	public String login(String id, String pwd, HttpSession session, 
						HttpServletRequest request)throws Exception{
		String url="redirect:/member/list.do";
				
		try {
			memberService.login(id, pwd);
						
			MemberVO loginUser=memberService.getMember(id);
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(6*60);
			
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
		}catch (NotFoundIDException | InvalidPasswordException e) {
			//e.printStackTrace();
			url="commons/loginForm";
			request.setAttribute("msg", e.getMessage());
		} 
		

		return url;
		
	}
	
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session)throws Exception{
		
		String url = "redirect:/commons/loginForm.do";
		
		session.invalidate();
		
		return url;		
	}
	
	
}
