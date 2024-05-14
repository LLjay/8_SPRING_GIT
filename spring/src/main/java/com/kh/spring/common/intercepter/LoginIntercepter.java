package com.kh.spring.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercepter extends HandlerInterceptorAdapter{

	/*
	 * HandlerInterceptor
	 * 
	 * -- Controller가 실행 되기 전 또는 후에 낚아채서 실행
	 * -- 로그인 유무 판단, 회원 권한 체크
	 * 
	 * preHandle (이전 처리) : DispatcherServlet이 컨트롤러를 호출하기 전에 낚아채는 영역
	 * postHandle (이후 처리) : 컨트롤러에서 요청 후 DispatcherServlet으로 view 정보를 return 하는 순간 낚아채는 영역
	 * 		> 서블릿과 return 사이에 실행
	 */
	// 서블릿과 컨트롤러 사이에 필터 역할을 하는 것
	// servlet-context에 등록해줘야 함
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		// return : true -> 기존 요청 흐름대로 진행 (Controller로 이동)
		// return : false -> 요청 중단 후 반환
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			return true;
		} else {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath()); // 메인 화면으로 보내기
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
