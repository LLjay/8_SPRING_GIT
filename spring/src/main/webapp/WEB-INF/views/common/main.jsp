<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="init()">
	<jsp:include page="common/header.jsp"/>
	<div class="content">
		<br><br>
		
		<div class="innerOuter">
			<h4>게시글 Top 5</h4>
			<br>
			<table id="boardList" class="table table-hover" align="center">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>첨부파일</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<!-- 현재 조회수가 가장 높은 상위 5개의 게시글을 조회해서 그리기(ajax) -->
					<c:forEach var="b" items="list">
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>첨부파일</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<script>
				// onload나 jquery의 ready나 다른 방식
				function init(){
					// 서버로부터 조회수가 높은 게시글 5개 조회해서 가져오기(ajax)
					// tbody 요소로 추가해주기
					getTopBoardList(function(res){
						drawTopListBody(list);
					})
				}

				function drawTopListBody(list){
					const topBody = document.querySelector("#boardList>tbody");
					${topBody}.empty();
					
					for(let b of list){
						const tr = document.createElement("tr");
						tr.innerHTML = "<td>" + b.boardNo + "</td>"
										+ "<td>" + b.boardTitle + "</td>"
										+ "<td>" + b.boardWriter + "</td>"
										+ "<td>" + b.count + "</td>"
										+ "<td>" + b.createDate + "</td>"
										+ "<td>" + (b.originName != null ? "★" : "") + "</td>"
						tr.onclick = function(){
							location.href = "detail.bo?bno=" + b.boardNo;
						}
						topBody.appendChild(tr); // 붙여넣기
						
						// topBody += "<tr>"
						// 		+ "<td>" + b.boardNo + "</td>"
						// 		+ "<td>" + b.boardTitle + "</td>"
						// 		+ "<td>" + b.boardWriter + "</td>"
						// 		+ "<td>" + b.count + "</td>"
						// 		+ "<td>" + b.createDate + "</td>"
						// 		+ "<td>" + (b.originName != null ? "★" : "") + "</td>"
						// 		+ "</tr>";
					}
				}

				// 서버로부터 조회수가 높은 게시글 5개 조회해서 가져오는 함수
				function getTopBoardList(callback){
					// 보내주고 싶은 data가 없으니까 / 혹은 개수를 직접 보내서 확장성 넓힐 수도 있음
					$.ajax({
						url: "topList.bo",
						success : callback,
						error: function(){
							console.log("top5 ajax 실패")
						}
					});
				}
			</script>



			<!-- 내가 한 거 -->
			<!-- <script>
				function ajaxTopFive(){
					$.ajax({
						url: "selectTopFive.bo",
						data: {null},
						success: drawContent(list),
						error: function(){
							console.log("정보 불러오기 실패");
						}
					})
				}
				
				function drawContent(list){
					let str = "";
					for(let b of list){
						str += `<tr>
							<th>`+b.boardNo+`</th>
							<th>`+b.boardTitle+`</th>
							<th>`+b.boardWriter+`</th>
							<th>`+b.count+`</th>
							<th>`+b.enrollDate+`</th>
							<th>`+b.file+`</th>
						</tr>`;
					}
					const content = document.querySelector("#tbody");
					content.innerHTML = str;
				}
			</script> -->
		</div>
	</div>
	<jsp:include page="common/footer.jsp"/>
</body>
</html>