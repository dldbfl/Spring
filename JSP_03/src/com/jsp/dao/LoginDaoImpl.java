package com.jsp.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.jsp.vo.MemberVO;
import com.ibatis.sqlmap.client.SqlMapClient;


public class LoginDaoImpl implements LoginDao {

	private LoginDao loginDao;
	
	private SqlMapClient smc;
	
	private static LoginDaoImpl dao;
	
	private LoginDaoImpl() {
		
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			//1-2위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();// Reader객체 닫기
			}catch(IOException e) {
				System.out.println("SqlMapClient 객체 생성 실패!");
				e.printStackTrace();
			}
		}
		
		public static LoginDaoImpl getInstance() {
			if(dao == null) {
				dao = new LoginDaoImpl();
			}
			return dao;
		}
		@Override
		public MemberVO loginMember(MemberVO mv){
			MemberVO member = new MemberVO();
		
			try {
				member = (MemberVO) smc.queryForObject("member.loginMember",mv);
//				System.out.println(member.getId());
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return member;
	
	}
}
