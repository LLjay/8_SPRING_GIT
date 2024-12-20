package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.vo.Member;
import com.kh.spring.member.service.MemberService;

// Component -> 기본적으로 bean에 등록해주는 어노테이션, 하지만 우리는 컨트롤러를 만들 것
// Component 어노테이션의 성질을 갖고 있으나 이건 여기에 맵핑값이 있다는 뜻을 포함
// 이것도 servlet-context에 bean 등록을 해야 사용 가능

@Controller
// @RequestMapping("/member")
// -> member라고 하면 여기로 들어오는 것
// -> contextPath/member/login 등으로 url 맵핑
// -> 원래 login.me 였던 게 member/login으로 바뀌는 것
public class MemberController {
	
	// 의존성을 주입한 것
		// 생명주기 관리를 해준 것이다, 객체가 생성되고 소멸하는 과정까지 스프링이 다 해줄 것
		// ?????????????????????????
		// 인터페이스니까 변경 되어도 상관 없음?
//		private MemberService memberService = new MemberServiceImpl();
//		원래 이렇게 했음
	
//	#필드 주입 방식
//	@Autowired
//	private MemberService memberService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bcryptPasswordEncoder;
	// 스프링이 의존성을 주입한 것이라고 알아야 하므로 어노테이션 적용
	
	
//	#생성자 주입 방식
	private final MemberService memberService;
	
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	
//	이 생성자는 객체가 생성될 때 만들어지는 것이므로 사이에 null이 들어갈 시차가 생기지 않음
	@Autowired
	public MemberController(MemberService memberService, BCryptPasswordEncoder bcryptPasswordEncoder) {
		this.memberService = memberService;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
	}
	
	
	/*
	 * #기존 객체 생성 방식
	 * - 객체 간 결합도가 높아짐(소스코드 수정이 일어날 경우 하나하나 전부 다 바꿔줘야 함)
	 *  	// 객체 간 결합도가 낮아야 유지보수가 편함
	 *  	// 포조? 의존성을 주입하면 포조 컨트롤러가 되는 것, 외부의 영향을 덜 받으면서 활용할 수 있는 객체
	 *  - 서비스가 동시에 매우 많은 횟수 요청이 될 경우 그만큼 객체 생성
	 *  	// 컨트롤러 필드에 서비스를 넣을 경우 유저가 관리해야 함
	 *  
	 *  #Spring의 DI(Dependency Injection)를 이용한 방식
	 *  - 객체를 생성해서 주입해줌
	 *  - new라는 객체 생성 키워드 없이 @Autowired라는 어노테이션으로 사용해야 함
	 *  
	 *  -----------------------------------------------------------------
	 *  
	 *  #필드 주입 방식
	 *  스프링 컨테이너가 객체를 생성한 후, @Autowired 어노테이션이 붙은 필드에 의존성 주입
	 *  
	 *  #생성자 주입 방식
	 *  스프링 컨테이너가 객체를 생성할 때 생성자를 통해서 필요한 의존성 주입
	 *  
	 *  #필드 주입 방식 -> 생성자 주입 방식
	 *  주입 시점 차이로 인해 객체가 완전히 초기화된 상태로 사용할 수 있음을 보장(불변성 확보)
	 *  	// 주입 시점 차가 발생하지 않음
	 *  테스트 가능성과 유지 보수성 증가
	 */
	
	/*
	 * Spring에서 파라미터를 받는 방법
	 * 1. HttpServletRequest 활용해 전달 받기 (jsp/servlet 방식)
	 * 		해당 메소드의 매개변수로 HttpServletRequest 작성해두면
	 * 		스프링 컨테이너가 해당 메소드를 호출 시 자동으로 객체를 생성해 매개변수로 주입해줌
	 */

// 어노테이션으로 bean에 등록 했으니 이제 맵핑 해줄 것
//	@RequestMapping(value="/login.me")
//	원래는 value가 정석이나 이렇게 생략해도 무방, 쓰는 경우는 다른 것과 혼용해서 쓸 때
	/*@RequestMapping("login.me")
	public String loginMember(HttpServletRequest request) {
//		원래 안 사용하는 건데 내가 request 객체가 필요하다고 기술 해주니까 디스패처 서블릿에서 넘겨준 것
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println("userId : " + userId);
		System.out.println("userPwd : " + userPwd);
		
		return "main";
		// == /WEB-INF/views/main.jsp
	}*/
//  return에 넘기는 게 jsp 페이지, 그럼 그 페이지를 자동으로 넘겨줌
//	servlet-context 안에 등록된 접두접미어가 main 앞뒤로 붙어 그 경로의 파일을 리턴 해준 것

	/*
	 * 2. @RequestParam 어노테이션을 이용하는 방법
	 * request.getParameter("키")로 밸류를 추출하는 역할을 대신 해주는 어노테이션
	 * value 속성의 밸류로 jsp에서 작성했던 name 속성 값을 담으면 알아서 해당 매개변수로 받아올 수 있음
	 * 만약 넘어온 값이 비어있는 형태라면 defaultValue 속성으로 기본값을 지정할 수 있음
	 * 
	 * @RequestParam 생략 가능
	 */
	/*@RequestMapping("login.me")
	public String loginMember(@RequestParam(value="userId", defaultValue="ddd") String id, @RequestParam String userPwd) {
//		RequestParam은 생략 가능, 원래 그 안에서 찾아서 가져오는 것?
//		전달값과 받아주는 값이 다른 경우 value 안에 전달값 이름을 쓰면 매치 가능
//		빈 값으로 오는 게 싫으면 default 값 지정 가능
		System.out.println("userId : " + id);
		System.out.println("userPwd : " + userPwd);
//		보낸 키와 받는 키가 이름이 동일하면 그대로 매치 시켜서 값을 보내줌
		return "main";
		// == /WEB-INF/views/main.jsp
	}*/
	
	/*
	 * 3. 커맨드 객체 방식
	 * 
	 * 해당 메소드의 매개변수로 요청 시 전달값을 담고자 하는 vo 클래스의 타입을 세팅 후
	 * 요청 시 전달값의 키값(jsp의 name 속성값)을 vo 클래스에 담고자 하는 필드명으로 작성
	 */
	
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m) {
//		Member 객체 안에 자동으로 세팅 되어서 들어옴
//		스프링 컨테이너에 이미 어노테이션이 있기 때문에 이 메소드 조회, 그 안의 객체 필드명과 일치하면 그냥 받아주는 것
//		게시글 수정 하거나 할 때 통으로 정보를 넘김 그럴 때 객체로 세팅해서 받아줄 수 있음
//		여기로 맵핑이 되었을 때 전달값 키와 객체 필드명이 일치하면 자동으로 넣어준다.
		System.out.println("userId : " + m.getUserId());
		System.out.println("userPwd : " + m.getUserPwd());
		
//		서비스로 넘겨줄 것
//		Member loginUser = new MemberServiceImpl(); 
//		이렇게 하면 유저가 만드는 거니까 의존성이 떨어짐, 바뀌면 유저가 수동으로 바꿔야 함
		
		Member loginUser = memberService.loginMember(m);
//		스프링이 알아서 객체를 생성해서 주려면 bean에 등록해야 함
//		직접 해줘도 되고 어노테이션을 이용해서 등록해도 됨
		
		if(loginUser == null) {
			System.out.println("로그인 실패");
		} else {
			System.out.println("로그인 성공");
		}
		
		return "main";
		// == /WEB-INF/views/main.jsp
	}*/
//	필터는 url 파라미터 쭉 나열해서 사용하면 된다?
	
	/*
	 * 요청 처리 후 응답 데이터를 담고 응답 페이지로 포워딩 또는 url 재요청 처리하는 방법
	 * 
	 * 1. 스프링에서 제공하는 Model 객체를 이용하는 방법
	 * - 포워딩한 응답뷰로 전달 하고자 하는 데이터를 맵 형식(k-v)으로 담을 수 있는 영역
	 * - Model 객체는 requestScope
	 * - request.setAttribute 이용 했었는데 이제는 model.addAttribute 이용
	 */
	
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m, Model model, HttpSession session) {
		Member loginUser = memberService.loginMember(m);
//		HttpSession도 서블릿에서 request에서 꺼내서 만들어줄 수 있으므로 받아오면 됨
		
		if(loginUser == null) { // 로그인 실패 -> 에러 문구를 requestScope에 담고 에러 페이지로 포워딩
			model.addAttribute("errorMsg", "로그인 실패");
			return "/common/errorPage";
		} else { // 로그인 성공 -> sessionScope에 로그인 유저 담아서 메인으로 url 재요청
			// /WEB-INF/views/common/errorPage.jsp
			session.setAttribute("loginUser", loginUser);
			return "redirect:/";
//			redirect 요청은 이렇게 써줌 / 현재 페이지니까 이렇게 쓴 건가?
		}
	}*/
	
	// 2. 스프링에서 제공하는 ModelAndView 객체 사용
//	이건 잘 안 씀
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, ModelAndView mv, HttpSession session, String saveId, Http) {
		// 암호화 전
//		Member loginUser = memberService.loginMember(m);
//		
//		if(loginUser == null) { // 로그인 실패 -> 에러 문구를 requestScope에 담고 에러 페이지로 포워딩
////			model.addAttribute("errorMsg", "로그인 실패");
//			mv.addObject("errorMsg", "로그인 실패");
////			return "/common/errorPage";
//			mv.setViewName("common/errorPage"); // 리턴과 똑같은 효과
//		} else { // 로그인 성공 -> sessionScope에 로그인 유저 담아서 메인으로 url 재요청
//			session.setAttribute("loginUser", loginUser);
//			
////			return "redirect:/";
//			mv.setViewName("redirect:/");
//		}
//		return mv;
		
		
		
		// 암호화 후
		// Member m의 id -> 사용자가 입력한 아이디
		// 		  m의 pwd -> 사용자가 입력한 pwd(평문)
//		이제는 유저 정보를 아이디만 가져오면 됨, 비밀번호 검사를 내가 해주지 않을 것
		
		Member loginUser = memberService.loginMember(m);
		
		// loginUser id -> 아이디로 db에서 검색 해온 id
		// loginUser pwd -> db에 기록된 암호화 된 비밀번호
		
		// bcryptPasswordEncoder 객체의 matches()
		// matches(평문, 암호문)을 작성하면 내부적으로 복구화 작업 후 비교가 이루어짐
		// 두 구문이 일치하면 true 반환, 일치하지 않으면 false 반환
		
		bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd());
	
		if (loginUser == null) { // 아이디가 없을 때
			mv.addObject("errorMsg", "일치하는 아이디를 찾을 수 없습니다.");
			mv.setViewName("common/errorPage");
		} else if (!bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())){
			// 비밀번호가 서로 맞지 않을 때
			mv.addObject("errorMsg", "비밀번호가 일치하지 않습니다.");
			mv.setViewName("common/errorPage");
		} else { // 성공
//			session.setAttribute("loginUser", loginUser);
//			mv.setViewName("redirect:/");
			
			Cookie ck = new Cookie("saveId", loginUser.getUserId());
			if (saveId == null) {
				ck.setMaxAge(0);
			}
			
			response.addCookie(ck);
		}
		
		// input으로 들어온 saveId가 매개변수를 통해 들어올 것
		return mv;
	}
//	굳이 컨트롤러 다시 안 받아줘도 됨, 매핑값만 잘 해주면 됨
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.removeAttribute("loginUser");
		// session.invalidate(); -> 만료
		return "redirect:/";
	}
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}

	//	보내주는 name 값을 객체 필드랑 일치 시켜줘야 스프링이 받아올 때 바로 매치 시켜서 편하게 받아옴

	/*
	 * ajax 요청에 대한 응답을 위한 controller에는 @ResponseBody 어노테이션을 작성해줘야 함
	 * 기본적인 세팅이 jsp 응답으로 되어 있기 때문에 @ResponseBody를 작성 해주면
	 * 반환값을 http 응답 객체에 직접 작성하겠다 라는 의미를 가지게 됨
	 */
	
	// 보내는 응답을 body에 담아서 보내겠다 (경로가 아니고)
	@ResponseBody
	@RequestMapping("idCheck.me")
	public String idCheck(String checkId) {
//		System.out.println(checkId);
//		return "NNNNN"; // jsp 페이지로 포워딩 되는 경로로 만들어주게 됨 -> 데이터 전송한다는 걸 다시 알려줘야 함
	
		int result = memberService.idCheck(checkId);
		
		if (result > 0) { // 이미 존재 한다면
			return "NNNNN";
		} else { // 존재하지 않는다면
			return "NNNNY";
		}
	
//		return memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
	}
	
	@RequestMapping("insert.me")
	public String insertMember(Member m, HttpSession session, Model model) {
		// 필드가 int일 때 형변환도 알아서 해줌
		// 만약 int인데 없을 수도 있는 값이면 빈값("")으로 넣음, 그건 String이니까 int로 받아주지 못함
		// 필수값이면 빈값이 넘어올 수 있는 가능성이 없으므로 int로 써도 됨

//		단방향 암호 : 알 수 없는 규칙이나 규칙 없이 다른 패턴의 글자로 만들어 복구가 불가능 하게 만드는 것
//			> 인증 시 주로 사용
//		블록체인 : 캐시 암호 사용, 복구 불가능
//		해시코드를 이용, 원문을 다 비교하는 것보다 해시코드를 비교하는 게 훨씬 간단함
//		토큰? 어떤 API에 접근할 수 있는지, 만료는 언제인지 등의 정보가 담겨있음
//		그 토큰으로 요청을 보내면 접근 가능한 유저인지 확인
//		JWT 토큰? -> 통합로그인에 유리
//			> 세션에 정보 유지가 아니라 암호키만 저장해서 인증하는 방식이라 서버에 무리도 덜 함
//		비밀번호 + 회사만의 랜덤 값을 부여하면 복구가 불가능해짐, 그 랜덤 값을 키로 사용, 암호화 규칙에 넣어서 사용
//		비크립트? 이 랜덤 키를 자동으로 발급해줌
//		원래는 프론트에서 넘어올 때부터 암호화 해야 하므로 프론트에서도 작업 처리 해줘야 함

		/*
		 * 1. 한글 깨짐 문제 발생 -> web.xml에 스프링에서 제공하는 인코딩 필터 등록 
		 * 2. 나이를 입력하지 않을 경우 int 자료형에 빈 문자열을 대입해야 하는 경우 발생
		 * -> 400 에러 발생 : Member의 Age 필드 자료형을 String으로 변경 해주면 됨
		 * 3. 비밀번호가 사용자가 입력한 그대로 전달됨(평문)
		 * Bcrypt 방식을 이용해 암호화 한 후 저장
		 * -> 스프링 시큐리티에서 제공하는 모듈 이용 <pom.xml에 라이브러리 추가>
		 * 		// 라이브러리 다운 받아야 함, security core, web, config pom에 추가 후 bean 추가
		 */
		
		// 암호화 작업
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		
//		System.out.println(encPwd); //$2a$10$8GvXJUwS.ArcFHUyBgr1W.dT9zkkauSRPbKjj.zaW32t5Kx5N1QhO
		m.setUserPwd(encPwd);
		
		int result = memberService.insertMember(m);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "성공적으로 회원가입이 완료 되었습니다.");
			return "redirect:/";
		} else {
//			request 대신 model 쓰는 것
			model.addAttribute("errorMsg", "회원가입 실패");
			return "common/errorPage";
		}
	}
	
	// HTTP 상태 400 에러 -> 대부분 parameter 타입이 서로 안 맞는 것, 서버에서 잘못 보내준 것
	// HTTP 상태 200 -> 성공
	// HTTP 상태 500 에러 -> 서버에서 보내줄 수 있는 리턴이 없을 때
	
	// 단방향 암호 -> 복구가 안 되게 한쪽으로만 보내서 확인
	// 양방향 -> 암호화 하면 상대가 복구해서 읽을 수 있도록
	// 스프링 시큐리티에게 암호화를 맡길 것
	
	
	@RequestMapping("myPage.me")
	public String myPage() {
		return "member/myPage";
	}
	
	@RequestMapping("update.me")
	public String updateMember(Member m, HttpSession session, Model model) {
		int result = memberService.updateMember(m);
		
		if (result > 0) {
			// 세션에 있는 로그인 정보도 업데이트 해줘야 함
			session.setAttribute("loginUser", memberService.loginMember(m));
			session.setAttribute("alertMsg", "회원정보 수정 성공");
			return "redirect:/myPage.me";
		} else {
			model.addAttribute("alertMsg", "회원정보 수정 실패");
			return "common/errorPage;";
		}
	}
	
//	@RequestMapping("delete.me")
//	public String deleteMember(Member m, HttpSession session) {
//		// 1. 암호화 된 비밀번호 가져오기
//		// 내거
////		Member loginUser = memberService.loginMember(m);
////		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
//		
//		// 강사님
//		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
//		// 2. 비밀번호 일치 / 불일치 판단
////		지금 session에 로그인 되어 있는 값과 내가 방금 입력한 비밀번호가 맞는지 확인해야 하므로 데이터베이스 아니고 세션에서 로그인 정보의 패스워드 가져옴
//		if (bcryptPasswordEncoder.matches(m.getUserPwd(), encPwd)) {
////	 		> 일치 -> 탈퇴 처리 -> session에서 제거 -> 메인페이지로
//			int result = memberService.deleteMember(m.getUserId());
//			
//			if (result > 0) {
//				session.removeAttribute("loginUser");
//				session.setAttribute("alertMsg", "성공적으로 탈퇴 했습니다.");
//				return "redirect:/";
//			} else {
//				session.setAttribute("alertMsg", "회원 탈퇴에 실패했습니다.");
//				return "redirect:/myPage.me";
//			}
//			
//		} else {
////	 		> 불일치 -> alertMsg : 비밀번호 다시 입력 -> 마이페이지
//			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다. 다시 입력하세요.");
//			return "redirect:/myPage.me";
//		}
	
	@RequestMapping("delete.me")
	public String deleteMember(Member m, HttpSession session) {
		
		//1. 암호화된 비밀번호 가져오기
		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
		
		//2. 비밀번호 일치/불일치 판단후
		if (bcryptPasswordEncoder.matches(m.getUserPwd(), encPwd)) {
			//일치 -> 탈퇴처리 -> session에서 제거 -> 메인페이지로
			int result = memberService.deleteMember(m.getUserId());
			
			if(result > 0) {
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "회원탈퇴가 성공적으로 이루어졌습니다.");
				return "redirect:/";
			} else {
				session.setAttribute("alertMsg", "탈퇴처리 실패");
				return "redirect:/myPage.me";
			}
			
		} else {
			//불일치 -> alertMsg: 비밀번호 다시 입력 -> 마이페이지
			session.setAttribute("alertMsg", "비밀번호를 다시 확인해주세요");
			return "redirect:/myPage.me";
		}
		
		// 에러 나면 스프링이 알아서 롤백, 에러 안 나면 알아서 커밋
		// 만약 수동으로 트랜잭션 해야 하는 상황이면 어노테이션 사용하면 됨
	}
	
}
