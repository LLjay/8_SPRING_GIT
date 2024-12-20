package com.kh.spring.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// post 방식으로 보내는 것?
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // POST 등을 호출할 수 있도록 해주는 어노테이션
@ContextConfiguration(locations = {
//		resources 안에 있는 설정 파일들을 가져오는 것
		"file:src/test/resources/root-context.xml",
		"file:src/test/resources/servlet-context.xml",
		"file:src/test/resources/spring-security.xml"
})
public class MemberControllerTest {
	
	@Autowired
	private WebApplicationContext wac; // 웹 어플리케이션 컨텍스트
	private MockMvc mockMvc; // HTTP 요청 및 응답을 모의 테스트 할 수 있는 객체
	
	@Before // Test 객체가 실행되기 전에 실행하는 메소드
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); // mockMvc에 context 정보를 저장
		log.info("==========mockMvc 준비 완료==========");
	}
	
	@Test
	public void testLoginMember() {
		
		try {
			mockMvc.perform(post("/login.me") // POST 메소드로 /login.me 요청
					.param("userId", "admin") // 요청 파라미터 userId 세팅
					.param("userPwd", "1234")) // 보낸 것
			.andDo(print()) // 요청에 대한 응답 코드를 콘솔에 출력
			.andExpect(status().isOk()); // HTTP 상태 코드가 200 ok인지 확인
		} catch (Exception e) {
			e.printStackTrace();
		} 
	} // 체인 형식으로 넘길 파라미터 지정해줄 수 있음
	
//	프로젝트의 묶음 단위가 context
//	우리가 쓰고 있는 context는 스프링
	
//	
//	@Autowired
//	private final MemberService memberService;
//	
//	@Autowired
//	private final BCryptPasswordEncoder bcryptPasswordEncoder;

	@Test
	public void testMemberController() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testLogoutMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnrollForm() {
		fail("Not yet implemented");
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
	public void testMyPage() {
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
