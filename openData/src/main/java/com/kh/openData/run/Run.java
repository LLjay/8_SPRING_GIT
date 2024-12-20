package com.kh.openData.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Run {
	public static final String SERVICE_KEY="Grxmnd0m5qomi%2BM2R0VuQTB8VwW3B1xbJ1CCctU9UXuVP7OTQABkaeNfEP1hSag3QVygTWXzELqVWLGMRYThyQ%3D%3D";
//	원래 이렇게 키를 그냥 써두면 안 됨
	
	public static void main(String[] args) throws IOException {
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
	
		url += "?serviceKey=" + SERVICE_KEY;
//		요청변수에 서비스키는 이 항목명으로 보내라고 써있기 때문
//		데이터를 가져올 때는 대부분 GET 요청 (숨길 필요가 없으므로)
		url += "&returnType=json";
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8"); // 요청 시 전달 값에 한글이 있으면 인코딩 작업을 해줘야 함
//		항목 구분에 필수라고 되어 있는 부분은 무조건 넣어줘야 함
//		한글을 쓸 때는 엔코더를 이용해야 함
//		데이터 표출 방식을 json으로 바꾼 이유는 디폴트가 xml로 명시되어 있기 때문에 json으로 쓰고 싶어서
//		url로 요청을 보낼 것이기 때문에 알고 싶은 정보에 대한 것을 ? &을 써서 url로 요청하는 것
		
//		System.out.println(url); -> 이게 자바 코드로 전달이 되어야 함
		
		// 자바 코드로 서버에서 서버로 요청을 보내야 함
		// ** HttpURLConnection 객체를 이용해서 요청을 보내자
	
		// 1. 요청하고자 하는 url을 전달 하면서 java.net.URL 객체 생성
		URL requestURL = new URL(url);
//		java.net.URL
		
		// 2. 만들어진 URL 객체로 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestURL.openConnection();
	
		// 3. 요청에 필요한 Header 설정
		urlConnection.setRequestMethod("GET");
//		안 해줘도 디폴트 요청은 GET
		
		// 4. 해당 API 서버로 요청 보낸 후 입력 데이터 읽어오기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//		BufferedReader를 이용해 꺼내주면 read를 이용해 한 줄씩 꺼내줄 수 있음 -> 편리
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
//		스트림, 커넥션 전부 썼으니까 닫아주고 끊어줘야 함

		
		
//		System.out.println(responseText); 데이터가 한 줄로 옴, json으로 바꿔줘야 함
		
		// 각각의 item 정보 -> JSON 형식으로 변환
		// JSONObject, JSONArray -> json 라이브러리에서 제공하는 객체들
		// JsonObject, JsonArray -> Gson에서 제공
//		뭘 써도 상관 없으나 그 형식에 맞게 쓰면 됨
		
//		String 형식의 데이터를 json으로 바꿔줘야 함
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
//		Gson 버전 확인할 것
//		JsonElement로 나오는 결과를 JsonObject로 바꾸는 함수를 실행해서 바꿔주는 것
		System.out.println(totalObj);
		
//		response 안에 다시 body가 있음, 이걸 꺼내줘야 함
		JsonObject responseObj = totalObj.getAsJsonObject("response");
		JsonObject bodyObj = totalObj.getAsJsonObject("body");
		System.out.println(bodyObj);
		
		int totalCount = bodyObj.get("totalCount").getAsInt();
		// count 세줌
		
		JsonArray itemArr = bodyObj.getAsJsonArray("items");
//		이 안에 있는 items라는 키값의 밸류를 Array 형식으로 꺼내주는 것
		System.out.println(itemArr);
		
		itemArr.get(0).getAsJsonObject();
		// 배열 중 0번째 인덱스 데이터를 가져오는 것
		
		JsonObject jObj1 = itemArr.get(0).getAsJsonObject(); // 0번째 구의 미세먼지 정보를 가진 json 객체
		
		for(int i = 0; i < itemArr.size(); i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject(); // i번째 구의 미세먼지 정보를 가진 json 객체
			System.out.println("측정소명 : " + item.get("stationName").getAsString());
			System.out.println("측정일시 : " + item.get("dataTime").getAsString());
			
		}
	}

}
