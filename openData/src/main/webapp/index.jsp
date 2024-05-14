<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<h1>실시간 대기오염 정보</h1>

    지역 :
    <select name="" id="location">
        <option>서울</option>
        <option>대전</option>
        <option>대구</option>
        <option>부산</option>
    </select>
    <button id="btn1">해당 지역 대기 오염 정보</button>
    <br><br>

    <table>
        <thead>
            <tr>
                <th>측정소 명</th>
                <th>측정 일시</th>
                <th>통합 대기 환경 수치</th>
                <th>미세먼지 농도</th>
                <th>일산화탄소 농도</th>
                <th>일산화질소 농도</th>
                <th>오존 농도</th>
            </tr>
        </thead>
        <tbody>
            // 서버로부터 받아온 데이터 출력
        </tbody>
    </table>
    <script>
        const btn1 = document.querySelector("#btn1");
        btn1.onclick = function(){
            $.ajax({
                url: "air",
                data: {
                    location : document.querySelector("#location").value
                },
                success: function(data){
                    const itemArr = data.response.body.items;
                    drawAirBody
                },
                error: function(){
                    console.log("대기정보 api 요청 실패")
                }
            })
        }

        function drawAirBody(itemArr){
            const tBody = document.querySelector("#result body");
            tBody.innerHTML = "";

            for(let item of itemArr){
                tBody.innerHTML += ("<tr>"
                                    + "<th>"+item.stationName+"</th>"
                                    + "<th>"+item.dataTime+"</th>"
                                    + "<th>"+item.khaiValue+"</th>"
                                    + "<th>"+item.pm10Value+"</th>"
                                    + "<th>"+item.coValue+"</th>"
                                    + "<th>"+item.no2Value+"</th>"
                                    + "<th>"+item.o3Value+"</th>"
                                    + "</tr>")
            }
        }
    </script>
    <!-- 강사님 코드 참고할 것 -->
</body>
</html>