<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<!-- 메뉴바 -->
    <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="insert.me" method="post" id="enrollForm">
                <div class="form-group">
                    <label for="userId">* ID : </label> 
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="userId" required>
                    <div id="checkResult" style="font-size:0.7em; display:none;"></div>
					<br>
                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="Please Enter Password" name="userPwd" required> <br>

                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" placeholder="Please Enter Name" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" placeholder="Please Enter Email" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" placeholder="Please Enter Age" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" placeholder="Please Enter Phone (-없이)" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" placeholder="Please Enter Address" name="address"> <br>
                    
                    <label> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender" checked>
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" disabled>회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>  
        <script>
            // window.onload해도됨? 로드가 되어야 이걸 가져오니까
            $(function(){
                const idInput = document.querySelector("#enrollForm input[name=userId]");
                let eventFlag; // window 스코프에 들어감, 따라서 setTimeOut 변수가 계속해서 덮어씌워짐
                // #userId로 가져오지 않는 이유 -> include로 많이 가져오기 때문에 실수로 userId를 여러 개 사용할 수도 있음
                // 오류 방지를 위함
                // script import 헤드에서 해줄 때 아직 돔이 생성되지 않으면 식별자를 찾을 수 없음
                // -> 돔이 로드 되었을 때 실행해달라는 이벤트를 걸어줘야 함
                idInput.onkeyup = function(ev){ // 발생 이벤트를 첫 번째 매개변수로 가져올 수 있음
                    // onkeyup -> 눌러서 뗐을 때 이벤트가 발생한다.?
                    
                    clearTimeout(eventFlag);
                    // 눌렀을 때 실행해줘야 함, 값을 입력해줄 때마다 값을 지워줄 것

                    // 키가 눌릴 때마다 해당 아이디가 중복 되는지 검사
                    // 서버에 데이터를 보내 응답 받아야 함 -> ajax

                    // 한 글자 넣을 때마다 계속 요청이 가니까 5글자 이상이면 요청해달라
                    // 정규식으로 해도 됨
                    const str = ev.target.value;
                    if (str.trim().length >= 5) {
                        // 그래도 5글자 이상이면 계속 감
                        
                        eventFlag = setTimeout(function(){ // 1.5초 후에 서버로 check 요청 전송
                                    $.ajax({
                                        url: "idCheck.me",
                                        // data: {checkId : idInput.value} == 
                                        data: {checkId : ev.target.value}, // 체크하고 싶은 사용자가 입력한 아이디
                                        // 이 이벤트가 발생한 타겟의 밸류
                                        success: function(result){
                                            const checkResult = document.getElementById("checkResult");
                                            checkResult.style.display = "block";
                                            if(result === "NNNNN"){ // 사용이 불가한 경우
                                                // 회원가입 버튼 비활성화
                                                document.querySelector("#enrollForm button[type='submit']").disabled = true;
                                                
                                                checkResult.style.color = "red";
                                                checkResult.innerText = "이미 사용 중인 아이디입니다."
                                            } else { // 사용이 가능한 경우
                                                // 회원가입 버튼 활성화
                                                document.querySelector("#enrollForm button[type='submit']").disabled = false;
                                            
                                                checkResult.style.color = "green";
                                                checkResult.innerText = "사용 가능한 아이디입니다."
                                            }
                                        },
                                        error: function(){
                                            console.log("아이디 중복 체크 ajax 실패")
                                        }
                                    })
                                }, 500)
                    } else { // 5글자 이하
                        // disabled 상태 유지
                        // checkResult 안 보이는 상태
                        document.querySelector("#enrollForm button[type='submit']").disabled = true;
                        document.getElementById("checkResult").style.display = "none";
                    }
                }
            })
        </script> 

    </div>

    <!-- 푸터바 -->
    <jsp:include page="../common/footer.jsp" />

</body>
</html>