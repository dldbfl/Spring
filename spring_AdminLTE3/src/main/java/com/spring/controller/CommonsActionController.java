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
/*@RequestMapping("/commoms/*")*/ //url은 /가 두개붙어도 1개로 친다.
public class CommonsActionController {
	
	@Autowired
	private MemberService memberService;//=MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	
	
	@RequestMapping({"/","/commons/loginForm.do"})
	public String loginForm()throws Exception{
		
		String url = "commons/loginForm";
		
		return url;		
	}
	
	@RequestMapping("/commons/login.do")
	public String login(String id, String pwd, HttpSession session, 
						HttpServletRequest request)throws Exception{
		String url="redirect:/member/list.do";
		/*String url="redirect:"+request.getContextPath()+"/member/list.do";*/
		
		//로그인 실패 시 추가한 attribute를 삭제
		session.removeAttribute("msg");
		
		String message = null;
		try {
			memberService.login(id, pwd);
						
			MemberVO loginUser=memberService.getMember(id);
			if(loginUser.getEnabled()==0) { //사용정지
				message="사용중지된 아이디로 이용이 제한됩니다.";
				url="redirect:/commons/loginForm.do";
			}else {
				session.setAttribute("loginUser", loginUser);
				//session.setMaxInactiveInterval(6*60);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
			message="시스템 장애로 로그인이 불가!(不可).";
			/*url="error/500_error";*/
			url="redirect:loginForm.do";
		}catch (NotFoundIDException e) {
			//e.printStackTrace();
			message="아이디가 존재하지 않소!";
			url="redirect:loginForm.do";
			//request.setAttribute("msg", e.getMessage());
		}catch(InvalidPasswordException e) {
			message="비번이 틀렸소!";
			url="redirect:loginForm.do";
			//request.setAttribute("msg", e.getMessage());
		}
		
		session.setAttribute("msg", message);	
		session.setAttribute("id", id);	
		
		return url;
		
	}
	
	
	@RequestMapping("/commons/logout.do")
	public String logout(HttpSession session)throws Exception{
		
		String url = "redirect:/commons/loginForm.do";
		
		session.invalidate();
		
		return url;		
	}
	
	
}
