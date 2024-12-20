package com.kh.etc.mail;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class Test2 {
//	스프링 부트는 xml 파일을 쓰지 않음, 자바 객체를 하나 만들어서 거기에 Conficuration 어노테이션을 붙여서 사용
	@Autowired
	private JavaMailSender sender;
	
	@GetMapping("send")
	public String mail() {
		// 메세지 생성
		SimpleMailMessage message = new SimpleMailMessage();
//				제목
		message.setSubject("이메일 전송 테스트2");
//				내용
		message.setText("이메일 테스트를 하겠습니다.2");
//				받는 사람 여러 명 쓸 수 있음
		String[] to = {"julylee0724@naver.com", "jsoohan6@naver.com"};
		message.setTo(to);
		
		String[] cc = {"julylee0724@gmail.com"};
		message.setCc(cc);
		
		sender.send(message);
		
		return "redirect:/";
	}
	
	@GetMapping("hypermail")
	public String hyperMail() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
//		false 부분은 멀티파트 란, 사진을 보내고 싶으면 true로 바꿔주면 됨
		
		String[] to = {"julylee0724@naver.com", "jsoohan6@naver.com"};
		helper.setTo(to);
		
		String[] cc = {"julylee0724@gmail.com"};
		helper.setCc(cc);
		
		helper.setSubject("이메일 전송 테스트 3");
		
		// 두 번째 인자를 true로 보낼 시 html을 사용하겠다는 의미
		helper.setText("<a href='localhost:8888/etc'>웹사이트로 이동</a>", true);
		sender.send(message);
//		원래 url을 만들 때 직접 기입하는 건 좋지 않음, contextPath를 가져와서 쓰는 게 좋음
		
//		지금 내가 사용하는 contextPath 가져오는 것
		String url = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.port(8888)
				.path("/user")
				.queryParam("user_id", "julylee0724")
				.queryParam("uuid", "asdlfjasldkfj")
				.toUriString();
//		아이디, 비밀번호 찾기 설정, 어떤 비밀번호인지 알아야 아이디 설정을 해줄 것
		
		return "redirect:/";
		
	}
	
	@GetMapping("sendfile")
	public String sendfile() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//		false 부분은 멀티파트 란, 사진을 보내고 싶으면 true로 바꿔주면 됨
		
		String[] to = {"jsoohan6@naver.com"};
		helper.setTo(to);
		
		String[] cc = {"julylee0724@gmail.com"};
		helper.setCc(cc);
		
		helper.setSubject("이미지 전송 테스트 3");
		
		helper.setText("파일 전송 테스트입니다.");
		
		// 첨부파일 추가
		DataSource source = new FileDataSource("C:\\workspace\\FinalProject\\UI\\free-icon-pawprint-4063510.png");
		helper.addAttachment(source.getName(), source);
		
		sender.send(message);
		return "redirect:/";
		
	}
	
}
