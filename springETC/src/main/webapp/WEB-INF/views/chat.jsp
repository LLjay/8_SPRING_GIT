<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>채팅</h1>

    메세지 : <input type="text" name="msg"><br>
    수신자 : <input type="text" name="target">

    <button onclick="sendMsg()">전송</button>

    <br>
    <div id="msg-container"></div>

    <script>
        const socket = new WebSocket("ws://localhost:8888/etc/server");

        socket.onopen = function(){
            console.log("웹소켓 연결 성공...")
            // onopen : 연결이 성공해서 소켓의 통로가 열리면 실행됨
        }

        socket.onclose = function(){
            console.log("웹소켓 연결 끊어짐...")
            // onclose : 연결이 한 번 됐다가 끊어지는 경우 실행됨
        }

        socket.onerror = function(){
            console.log("웹소켓 연결 실패...")
            // onerror : 연결을 시도했으나 연결 되지 않은 것
            alert("웹소켓 연결 실패")
        }

        // socket 연결로부터 데이터가 도착 했을 때 실행하는 이벤트
        socket.onmessage = function(ev){
            console.log(ev)
            // onclose : 연결이 한 번 됐다가 끊어지는 경우 실행됨
            // 소켓 객체에 요청을 보내면 여기로 정보를 보냄

            // sendMessageUser 메소드로 소켓에 메세지 전송 시 여기로 옴
            const receive = JSON.parse(ev.data);

            const msgContainer = document.querySelector("#msg-container");
            msgContainer.innerHTML += (receive.name + "(" + receive.time + ") <br>" + receive.msg + "<br>");
            console.log(receive);
        }
            // 통로를 닫는 이유는 그게 계속 서버에서 리소스를 잡아먹기 때문에
            // 소켓을 한 서버에 여러 개 두는 건 좋지 않음, 병렬처리기 때문에 하는 만큼 속도가 느려짐

        function sendMsg(){
            const msgData = {
                message : document.querySelector("input[name=msg]").value,
                target : document.querySelector("input[name=target]").value,
            }
            console.log(msgData)
            // 다 String 형태로 보내게 됨

            socket.send(JSON.stringfy(msgData));

        }
    </script>
</body>
</html>