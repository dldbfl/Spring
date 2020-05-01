package com.jsp.dao;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	//회원정보 조회
	MemberVO selectMemberById(String id) throws SQLException; 
	
}
