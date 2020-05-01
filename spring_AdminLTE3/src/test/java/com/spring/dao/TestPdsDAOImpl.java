package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberVO;
import com.spring.request.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com/spring/context/root-context.xml")
@TransactionConfiguration(transactionManager="transactionManager") // 무조건 롤백하겠다.
@Transactional //트렌젝션 하겠다.
public class TestPdsDAOImpl {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Before
	public void init(){}
	

	@Test
	public void testInsertMember()throws SQLException{
		MemberVO member = new MemberVO();
		member.setId("kaka");
		member.setPwd("kaka");
		member.setName("kaka");
		member.setEmail("kaka@kaka.net");
		member.setPhone("000-0000-0000");
		member.setPicture("noImage.jpg");

		memberDAO.insertMember(member);
		
		MemberVO result = memberDAO.selectMemberById("kaka");
		
		Assert.assertEquals(member.getId(), result.getId());
	}
	
	@Test
	public void testSelectMember()throws SQLException{
		
		String id="mimi";
		
		MemberVO member = memberDAO.selectMemberById(id);
		
		Assert.assertEquals(id, member.getId());
	}
	
	
	
}
