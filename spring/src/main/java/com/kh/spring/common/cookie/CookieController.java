package com.kh.spring.common.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	// 저장하는 게 쿠키?
	// 세션 관리는 서버에서 해줌, 톰캣에서 서버를 생성 및 관리
	// HTTP 통신을 헤더에 담아서 브라우저가 보내주면
	// 세션 아이디는 브라우저마다 달라져 서버에서 인식
	// 세션 아이디는 클라이언트 컴퓨터에 저장
	// -> 탈취하기 쉬움
	// 쿠키에 세션, 테마(화이트, 블랙 테마) 등 저장
	// 이 브라우저에서는 이런 설정을 사용하는구나 등 사용자의 세팅 정보를 쿠키에 많이 저장
	
	// 로컬 스토리지와 쿠키, 브라우저에서 관리하는 영역
	// 로컬 스토리지는 저장 공간, 데이터를 저장하기 위해 서버에 요청을 보내지 않음
	// 반면 쿠키는 특정 url마다 저장? 서버에 데이터를 저장하기 위해 반드시 요청을 보냄
	// 서버에서 응답을 내려줄 때 쿠키에 어떤 데이터를 담아서 가져가라고 하면 담아줄 수 있음
	// 자바 스크립트에서도 저장 가능
	// 애플리케이션의 쿠키란에 세션 데이터 없애면 새로 빈 세션 데이터를 만들어서 가져다줌
	
	// 로컬 스토리지
	// 50메가바이트로 조금 더 많은 영역을 쓸 수 있음
	// 단순히 브라우저에서 필요한 데이터면 로컬 스토리지, 서버 공유가 필요하면 쿠키에 저장
	
	// 보통 요새는 통합 로그인 사용, 다른 url에서도 정보가 유지 되어야 함
	// Access 토큰, Refresh 토큰
	// Access 토큰 : 암호화 시킨 정보, 어디에 접근할 수 있는지에 대한 정보가 보통 담겨있음
	// 어떤 정보까지, 어떤 url까지, 언제까지 접근할 수 있는지의 정보를 다 쿠키 안에 저장
	// json web 토큰?
	// 로컬 스토리지에는 이 사람에 대한 정보, 탈취해도 상관 없는 정보를 저장
	// 
	
	/*
	 * #cookie
	 * 브라우저에 저장되는 데이터 조각, 주로 사용자를 식별하고 상태 정보를 기억하는데 사용
	 * 쿠키는 클라이언트(브라우저)의 로컬 저장소에 저장됨
	 * 저장된 쿠키 정보는 서버에 Http 요청 시 헤더에 담겨 함께 전송됨
	 * 
	 * 쿠키는 보안성이 낮고 개인정보 유출에 취약할 수 있음 -> 주요 정보를 저장할 때 사용하려면 보안적인 조치 필요
	 */
	
	@RequestMapping("create")
	public String create(HttpServletResponse response) {
		// 쿠키는 객체를 생성한 다음 응답 정보에 첨부 가능
		// name, value 속성을 필수로 작성해야 함
		
//		 서버에서는 쿠키를 직접 저장하는 게 아니라 브라우저에게 저장해달라고 요청하는 것
//		 이런 쿠키를 저장해달라고 response에 담아서 보내는 것
		
		Cookie ck = new Cookie("test", "sol");
//		시간 제한 주는 것이 가능
		ck.setMaxAge(60 * 60 * 24); // 하루의 시간, 초 단위
//		여기 정해준 시간 만큼만 유효
//		보통 통합로그인 할 때 줌
//		광고 팝업창, 오늘 하루 그만 보기 창에 체크하면 그 하루는 이미 읽었다는 정보를 쿠키에 저장하는 것
		
//		쿠키는 저장은 계속 가능, 만료되지 않음
//		다만 시간 정보가 그 시간이 되면 쿠키가 알아서 그 데이터를 못 쓰게 만들어줌
//		로직적으로 삭제를 시켜주는 것, 브라우저에 들어가 있는 텍스트 덩어리
		
		
//		 키 값 형태로 데이터를 넣는 것이 가능
		response.addCookie(ck);
		
		Cookie ck1 = new Cookie("test", "jay");
//		 키 값 형태로 데이터를 넣는 것이 가능
		response.addCookie(ck1);
		
		return "cookie/create";
//		쿠키는 쓰기 위해 만드는 것
	}
	
	@RequestMapping("create")
	public String delete(HttpServletResponse response) {
		// 쿠키는 삭제 명령어가 따로 없음
		// 0초로 만료 시간을 지정 후 덮어쓰기를 진행
		
		Cookie ck = new Cookie("test", "sol");
		ck.setMaxAge(0); 
		response.addCookie(ck);
		
		return "cookie/delete";
	}

}
