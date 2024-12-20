<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login" method="post">
	<input type="text" name="nick">
	<input type="submit" value="로그인">
</form>
	<a id="naverLoginLink">네이버 로그인</a>
    <script>
        // 네이버 로그인 설정
        window.onload = function() {
        // 발급 받은 client ID
        const clientId = "M6KbZh94kUB9jz1GlHQN";
        
        // 리다이렉트 할 URI를 UTF-8로 인코딩 해서 저장
            // 네이버 서버에서 알아서 처리해서 우리 페이지에 돌려줄 것
            // 그 돌려줄 페이지 uri를 저장하는 것
        const redirectURI = encodeURIComponent("http://localhost:8888/etc/naver-login");

            // 요청 정보 보낼 때 보내줘야 하는 데이터 state
            // 임의의 데이터?
        const state = Math.random().toString(36).substring(2);

        // 로그인 api url
            // type을 어떻게 받을건지 인데 서버로 받을 거니까 code로 받기
        const apiURL = 'https://nid.naver.com/oauth2.0/autorize?response_type=code&'
            + 'client_id=' + clientId + '&redirect_uri=' + redirectURI + '&state' + state;
       
            const loginBtn = document.getElementById("naverLoginLink");
            loginBtn.href = apiURL;
        }
    </script>
</body>
</html>