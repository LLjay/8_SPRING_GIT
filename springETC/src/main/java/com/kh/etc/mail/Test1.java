package com.kh.etc.mail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// 직접 도구를 생성해서 이메일 보내기
	// pom.xml에 라이브러리 등록 해야함

/*
 * Email Protocol
 * 
 * #SMTP
 * - 이메일을 전송할 때 사용하는 프로토콜
 * 
 * #POP
 * - 이메일 서버에 도착한 메일을 클라이언트로 가져올 때 사용하는 프로토콜
 * 
 * #IMAP
 * 이메일 서버에 직접 접속하여 이메일을 확인할 때 사용하는 프로토콜
 * (gmail의 SMTP를 이용하기 위해서는 IMAP 설정을 사용으로 바꿔줘야 함)
 */
public class Test1 {
//	@RequestMapping("/test")
//	public String test() {
//		System.out.println("잘 됨!");
//		return "redirect:/";
//	}
	
	public static void main(String[] args) {
		
		// MIME 형식의 메일
			// 일반적으로 사용하는 형식의 메일
			// 발신자, 수신자, 첨부파일, 내용, 날짜 등
			// 데이터 형식이 다 똑같아야 서버가 달라도 같은 형식을 보여줄 수 있음, 표준 형식이라고 생각하면 됨
		// MIME 형식의 메일을 보내기 위해 JavaMailSender 인터페이스 사용
		// 계정 설정
		
		// JavaMailSender를 실제로 구현해놓은 JavaMailSenderImpl 객체가 있음
		
		// 하지만 객체로 구현할 것
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");
//		포트는 gmail에서 쓰라고 한 포트를 쓰는 것
		sender.setPort(587);
//		메일 센더에 사용할 계정(아이디) 등록
		sender.setUsername("julylee0724@gmail.com");
//		발급 받은 코드
		sender.setPassword("dqag npke oxfg opsf");
		
		// 옵션 설정
//		키 값 형태로 저장할 수 있는 자료 구조
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
//		tls를 사용한다, 사용하기 위해 저장하는 것
		
		sender.setJavaMailProperties(prop);
		
		// 메세지 생성
		SimpleMailMessage message = new SimpleMailMessage();
//		제목
		message.setSubject("이메일 전송 테스트");
//		내용
		message.setText("이메일 테스트를 하겠습니다.");
//		받는 사람 여러 명 쓸 수 있음
		String[] to = {"julylee0724@naver.com", "jsoohan6@naver.com"};
		message.setTo(to);
		
		String[] cc = {"julylee0724@gmail.com"};
		message.setCc(cc);
		
		sender.send(message);
		
		}
	/*
	 * 서버에서 메일을 찾으면 그 메일이 맞는지 메일로 코드를 보낼 것(UUID), 그 메일을 5분 내로 입력하면 비밀번호 변경 창으로 넘기는 것
	 * 혹은 암호키로 비밀번호를 변경하는 법
	 */
}
