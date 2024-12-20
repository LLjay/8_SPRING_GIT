package com.kh.etc.chat;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// spring의 컴포넌트로 등록
@Component
public class ChatServer extends TextWebSocketHandler{
	// 핸들러의 이름과 앞 대문자는 똑같이 씀, 핸들러 설정 파일 쪽에는 변수명이니까 소문자로 써줌

	private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
//	보통 HashMap의 단점을 보완, 보통 기능에 따라 달라짐
//	쓰레드에 대한 안전성을 보장하기 위한 HashMap
	
	// 클라이언트가 연결 할 때 호출되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String nick = (String)session.getAttributes().get("nick");
		log.info("{} 연결됨...," nick);
//		{} 안에 컴마 뒤의 데이터가 들어가서 보여지게 됨
//		웹소켓은 일반 소켓과 달리 input output이 합쳐진 소켓이라고 생각하면 됨
		
		userSessions.put(nick, session);
	}	

	// 클라이언트로부터 메세지를 받을 때 호출되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 내 세션에서 닉네임 가져옴
		String nick = (String)session.getAttributes().get("nick");
		
		// 메세지 payload를 json 객체로 파싱
		JsonObject obj = new JsonParser().parse(message.getPayload()).getAsJsonObject();
		
		log.info("message : {}", obj.get("message").getAsString());
		log.info("target : {}", obj.get("target").getAsString());
	
		MsgVo vo = new MsgVo();
		vo.setMsg(obj.get("message").getAsString());
		vo.setNick(nick);
		vo.setTime(new Date().toLocaleString());
		
		// 다른 사용자에게 메세지 전송
		sendMessageUser(obj.get("target").getAsString(), vo);
	}
	
	// 특정 사용자에게 메세지를 전송하는 메소드
	private void sendMessageUser(String targetNick, MsgVo msgVo) {
		// target의 세션을 세션 목록으로부터 가져옴
		WebSocketSession targetSession = userSessions.get(targetNick);
		
		// 현재 사용자의 세션을 세션 목록으로부터 가져옴
		WebSocketSession mySession = userSessions.get(msgVo.getNick());
		
		// target의 세션이 유효한지 검사
		if(targetSession != null && targetSession.isOpen()) {
//			사용자가 세션에 정보가 있어야 채팅할 수 있고 만약 브라우저를 끊어서 연결이 닫혀있는 경우면 안 됨, 만약 연결이 안 되어 있는 경우 연결이 되어 있는지 검사해줌
			String str = new Gson().toJson(msgVo);
			// 웹소켓 텍스트 전송 규격 메세지로 변환
			TextMessage msg = new TextMessage(str);
			
			try {
				mySession.sendMessage(msg);
				targetSession.sendMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 클라이언트가 연결을 끊을 때 호출되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String nick = (String)session.getAttributes().get("nick");
		
		log.info("{} 연결 끊김...", nick);
		userSessions.remove(nick);
	}
	
}
