package com.jsp.service;

import java.util.List;

import com.jsp.dao.LoginDao;
import com.jsp.dao.LoginDaoImpl;
import com.jsp.vo.MemberVO;


public class LoginServiceImpl implements LoginService{

	private LoginDao loginDao;
	
	private static LoginServiceImpl service;
	
		public LoginServiceImpl() {
			loginDao = LoginDaoImpl.getInstance();
		}

		public static LoginServiceImpl getInstance() {
			if(service == null) {
				service = new LoginServiceImpl();
			}
			return service;
		}
		
		@Override
		public MemberVO loginMember(MemberVO mv) {
			return loginDao.loginMember(mv);
		}
		
/*		public static void main(String[] args) {
			MemberVO mv = new MemberVO();
			mv.setId("dldbfl");
			mv.setPwd("dldbfl");
			
			MemberVO loginMember = LoginDaoImpl.getInstance().loginMember(mv);
			loginMember = LoginDaoImpl.getInstance().loginMember(mv);
			
			System.out.println(loginMember);
		}*/
}
