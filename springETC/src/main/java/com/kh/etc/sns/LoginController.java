package com.kh.etc.sns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String login(HttpSession session, String nick) {
		session.setAttribute("nick", nick);
		return "chat";
	}

	@RequestMapping("/naver-login")
	public String naverLoginCallback(HttpServletRequest request) {
//		요청을 다시 보낼 것?
		String clientId = "M6KbZh94kUB9jz1GlHQN";
		String clientSecret = "SqDvvr0B5I";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		try {
			String redirectURI = URLEncoder.encode("http://localhost:8888/etc/naver-login", "UTF-8");
			String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
			apiURL += "&client_id=" + clientId;
			apiURL += "&client_secret=" + clientSecret;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&code=" + code;
			apiURL += "&state=" + state;
//			refresh? 토큰은 건드릴 필요가 거의 없음
//			다른 토큰(위 토큰)을 요청하면 어떤 정보에 접속할 수 있는지에 대한 정보를 담은 토큰이 옴
//			액세스 토큰으로 정보를... 몰라
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			
			// HTTP 요청에 대한 응답 코드 확인
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// 응답 데이터 읽어오기
			String inputLine;
			StringBuffer res = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			
			if(responseCode == 200) {
				// 정상적으로 정보를 받아왔을 때 result에 정보를 저장
				String result = res.toString();
//				System.out.println(result);
				
				JsonObject totalObj = JsonParser.parseString(result).getAsJsonObject();
				totalObj.get("access_token");
				
				String token = totalObj.get("access_token").getAsString(); // 정보 접근을 위한 토큰
				String header = "Bearer" + token;
				
				apiURL = "https://openapi.naver.com/v1/nid/me";
				Map<String, String> requestHeaders = new HashMap<String, String>();
				requestHeaders.put("Autorization", header);
			
				String responseBody = get(apiURL, requestHeaders);
				
				JsonObject memberInfo = JsonParser.parseString(responseBody).getAsJsonObject();
				System.out.println(responseBody);
//				Response의 값이 필요하므로 다시 꺼내주기
				JsonObject resObj = memberInfo.getAsJsonObject("response");
				System.out.println(resObj);
//				데이터베이스의 이메일 정보가 맞는지 확인해주면 됨
				
				// 받아온 email과 데이터베이스의 email을 비교하여 가입 유무 확인 후
				// 가입 되어 있다면 로그인, 아니라면 회원가입 창으로 정보를 가지고 이동
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return "login";
	}
	// API에 GET 요청 보내고 응답을 받아오는 메소드
	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection conn = connect(apiUrl);
		
		try {
			conn.setRequestMethod("GET");
			
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				return readBody(conn.getInputStream());
			} else {
				return readBody(conn.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패 : " + apiUrl, e);
		} finally {
			conn.disconnect(); // 스트림 닫아준 것, close()
		}
	}
	
	// API에 연결하기 위한 HttpURLConnection 객체를 생성하고 반환하는 메소드
	private static HttpURLConnection connect(String apiUrl) {
		URL url;
		try {
			url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못 되었습니다. : " + apiUrl, e);
		} catch (IOException e){
			throw new RuntimeException("연결이 실패하였습니다. : " + apiUrl, e);
		}
	}
	
	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		
		try (BufferedReader br = new BufferedReader(streamReader)){
			StringBuilder responseBody = new StringBuilder();
			
			String line;
			while((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패하였습니다.", e);
		}
		
	}
}
