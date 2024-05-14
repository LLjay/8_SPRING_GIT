<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	쿠키 생성 완료
	
	<c:if test="${not empty cookie.test }">
		${cookie.test } <!-- 주소값으로 가져옴, javax.servlet.http.Cookie@54ac2248 등의 정보로 -->
		${cookie.test.value } <!-- value 값으로 가져옴, sol -->
	</c:if>
</body>
</html>