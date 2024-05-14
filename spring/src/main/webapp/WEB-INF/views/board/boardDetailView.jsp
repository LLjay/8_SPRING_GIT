<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
table * {
	margin: 5px;
}

table {
	width: 100%;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="content">
		<br>
		<br>
		<div class="innerOuter">
			<h2>게시글 상세보기</h2>
			<br> <a class="btn btn-secondary" style="float: right;"
				href="list.bo">목록으로</a> <br>
			<br>

			<table id="contentArea" algin="center" class="table">
				<tr>
					<th width="100">제목</th>
					<td colspan="3">${b.boardTitle }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${b.boardWriter }</td>
					<th>작성일</th>
					<td>${b.createDate }</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3"><c:choose>
							<c:when test="${not empty b.originName }">
								<!-- case1 -->
								<a href="${b.changeName }" download="${b.originName }">이미지.jpg</a>
							</c:when>
							<c:otherwise>
								<!-- case2 -->
								첨부파일이 없습니다.
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4"><p style="height: 150px;">게시글내용입니다</p></td>
				</tr>
			</table>
			<br>


			<!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
			<div align="center">
				<c:if test="${loginUser.userId eq b.boardWriter }">
					<a class="btn btn-primary" onclick="">수정하기</a>
					<a class="btn btn-danger" onclick="">삭제하기</a>
				</c:if>
			</div>
			<br>
			<br>


			<form action="" method="post" id="postForm">
				<input type="hidden" name=bno value="7"> <input
					type="hidden" name="filePath" value="이미지.jpg">
			</form>


			<!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
			<table id="replyArea" class="table" align="center">
				<thead>
					<c:choose>
						<c:when test="${empty loginUser }">
							<tr>
								<th colspan="2"><textarea class="form-control" readonly
										cols="55" rows="2" style="resize: none; width: 100%;">로그인 후 이용 가능합니다.</textarea>
								</th>
								<th style="vertical-align: middle"><button
										class="btn btn-secondary disabled">등록하기</button></th>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th colspan="2"><textarea class="form-control" id="content"
										cols="55" rows="2" style="resize: none; width: 100%;"></textarea>
								</th>
								<th style="vertical-align: middle"><button
										class="btn btn-secondary" onclick="addReply();">등록하기</button></th>
							</tr>
						</c:otherwise>
					</c:choose>

					<tr>
						<td colspan="3">댓글(<span id="rcount">0</span>)
						</td>
					</tr>

				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
		<br>
		<br>

		<script>
            $(function(){
                getReplyList({bno :  ${b.boardNo}}, function(result){
                    drawReplyList(result);
                })
            })

            // 서버에서는 간단하게 데이터만 만들어주면 프론트에서 다 풀어 헤쳐서 형변환 자유롭게 그리고 구성하면 됨


            // ajax 호출 함수를 따로 뺀 것
            // 댓글 목록 가져오기
            // 이 함수는 댓글 목록 가져오기 하나의 기능만 가져오면 좋음
            // 만약 필터를 적용하지 않고 가져오면 그때는 이렇게 쓰면 되고
            // 필터가 있다면 컨디션에 따라 보내는 정보가 달라짐
            function getReplyList(data, callback){
                $.ajax({
                    url: 'rlist.bo',
                    // data : {bno : ${b.boardNo}}, // 페이지 로딩 시 있는 정보이므로 EL이 먼저 보드 넘버로 바꿔줄 것
                    data : data, // 이렇게 해야 외부의 영향을 내부에서 받지 않음
                    success: function(result) {
                        callback(result)
                    },
                    error: function(){
                        console.log("댓글 요청 ajax 실패");
                    }
                })
            }

            // 1. innerHTML로 가져오는 방법 : 단순히 보여주기 위한 view 작성 시
            // function drawReplyList(replyList){
            //     const replyBody = document.querySelector("#replyArea tbody");
            //     let str = "";
            //     for (let reply of replyList){
            //         str += `<tr>
            //                     <th>`+reply.replyWriter+`</th>
            //                     <td>`+reply.replyContent+`</td>
            //                     <td>`+reply.createDate+`</td>
            //                 </tr>`
            //     }
            //     replyBody.innerHTML = str;
            // }
            // 단 이 방식은 중간에 onclick, 버튼 클릭 등을 넣기 힘듦


            // 2. document element : 이벤트를 넣는 뷰를 작성하고 싶을 때
            // tr 자체를 document로 만든다?
            function drawReplyList(replyList){
                for (let reply of replyList){
                    const replyRow = document.createElement('tr');
                    // 객체로 만들어서 가져와준 것
                    replyRow.innerHTML = `<th>`+reply.replyWriter+`</th>
                                            <td>`+reply.replyContent+`</td>
                                            <td>`+reply.createDate+`</td>`
                    replyBody.appendChild(replyRow);

                    replyRow.onclick = function(){
                        console.log(reply);
                    }
                }
                // 온클릭 했을 때 그 댓글 지우고 싶으면 그 온클릭에 댓글 번호를 넣으면 됨
                // reply, notice 등 여러 아이템을 범용 가능한 ui여야 함
                // 들어갈 데이터까지 받아서 주면 ui는 화면만 그려줄 수 있는 코드가 됨
            
                // 강사님 해당 페이지 코드 참고할 것

                // 데이터만 넘겨주면 ui는 알아서 그려줄게 -> 라이브러리
            }


        </script>
	</div>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>