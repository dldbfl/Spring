package com.jsp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.session.SqlSessionException;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.exception.NullidException;
import com.jsp.exception.NullpasswordException;

public interface MemberService {
	
	//로그인
	void login(String id, String pwd)throws SQLException, NotFoundIDException, InvalidPasswordException, NullidException, NullpasswordException;	
	//회원정보조회
	MemberVO getMember(String id)throws SQLException;
	
}
