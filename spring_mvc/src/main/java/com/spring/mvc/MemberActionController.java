package com.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.request.member.RegistMemberRequest;

@Controller
@RequestMapping("/member/*")
public class MemberActionController {

	@RequestMapping(value="regist",method=RequestMethod.GET)
	public void regist_GET()throws Exception{}
	
		
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public String regist_POST(RegistMemberRequest registReq,
							  @RequestParam(value="password")String pwd,
							  @RequestParam(defaultValue="대전광역시")String address,
							  @RequestParam(required=true)String authority)throws Exception{
		String url="redirect:https://www.ddit.or.kr";
				
		// MemberVO member = registReq.toMemberVO();
		// try{
		// memberService.regist(member);
		// }catch(SQLException e){
		// url = "error/500_error";
		// }
		
		System.out.println(registReq);
		System.out.println(pwd);
		System.out.println(address);
		System.out.println(authority);
		
		return url;
	}
}









