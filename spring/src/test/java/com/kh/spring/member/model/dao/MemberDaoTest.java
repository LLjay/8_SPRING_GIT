package com.kh.spring.member.model.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
//		resources 안에 있는 설정 파일들을 가져오는 것
		"file:src/test/resources/root-context.xml",
		"file:src/test/resources/servlet-context.xml",
		"file:src/test/resources/spring-security.xml"
})
public class MemberDaoTest {
	
	@Autowired
	private MemberDao memberDao;
//	MemberDao 가져와서 의존성 주입 해주는 것...
	
	@Autowired
	private SqlSessionTemplate sqlSession; // 이건 root-context.xml에 있는 것

	@Test
	public void testLoginMember() {
		Member m = new Member();
		m.setUserId("admin");
		m.setUserPwd("1234");
		
		m = memberDao.loginMember(sqlSession, m);
		log.info("member : " + m);
		if (m != null) {
			assertEquals("userNameEquals", "관리자", m.getUserName());
		} else {
			fail("testLoginMember fail");
		}
	}

	@Test
	public void testIdCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
