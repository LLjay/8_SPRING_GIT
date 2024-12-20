package com.kh.spring.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
//	public static final String SERVICE_KEY="Grxmnd0m5qomi%2BM2R0VuQTB8VwW3B1xbJ1CCctU9UXuVP7OTQABkaeNfEP1hSag3QVygTWXzELqVWLGMRYThyQ%3D%3D";
//	
//	@ResponseBody
//	@RequestMapping(value="air.do", produces="application/json; charset=UTF-8")
//	public String airPollution(String location) throws IOException{
//		
//		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
//		
//		url += "?serviceKey=" + SERVICE_KEY;
//		url += "&returnType=json";
//		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
//		url += "&sidoName=" + URLEncoder.encode("부산", "UTF-8");
//		url += "&sidoName=" + URLEncoder.encode("대전", "UTF-8");
//		
//		URL requestURL = new URL(url);
//		HttpURLConnection urlConnection = (HttpURLConnection)requestURL.openConnection();
//		urlConnection.setRequestMethod("GET");
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//		String responseText = "";
//		String line;
//		while((line = br.readLine()) != null ) {
//			responseText += line;
//		}
//		
//		br.close();
//		urlConnection.disconnect();
//		
//		return responseText;
//	}
	
//	@PostMapping(value="air")
//	public String getAirInfo() {
//		return "";
//	}
//	
//	@GetMapping(value="air")
//	public String getAirInfo() {
//		return "";
//	}
	
	// 프로젝트 시 GET : 조회, POST : 생성, 수정, 삭제로 할 것
	
//	public static void main(String[] args) {
//		int ranNum = (int)(Math.random()*900000 + 100000);
//		System.out.println(ranNum);
//	}
	
	public static String getHeaders() {
	
	}
}
