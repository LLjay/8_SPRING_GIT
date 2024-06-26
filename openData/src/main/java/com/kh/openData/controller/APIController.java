package com.kh.openData.controller;

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
public class APIController {
	public static final String SERVICE_KEY="Grxmnd0m5qomi%2BM2R0VuQTB8VwW3B1xbJ1CCctU9UXuVP7OTQABkaeNfEP1hSag3QVygTWXzELqVWLGMRYThyQ%3D%3D";
	
	@ResponseBody // json 형식으로 보내겠다고 선언하는 것?
	@RequestMapping(value="air", produces="application/json; charset=UTF-8")
	public String getAirInfo(String location) throws IOException{
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICE_KEY;
		url += "&returnType=json";
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
		
		String responseText = "";
		URL requestURL = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestURL.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

//		Gson 객체 자체를 json의 String 타입으로 바꿔서 String으로 변환해서 보내는 것
//		responseText는 애초에 json 형식이니까 이걸 그대로 프론트에 보내줄 수 있음
		
		return "";
	}
	
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
	
}
