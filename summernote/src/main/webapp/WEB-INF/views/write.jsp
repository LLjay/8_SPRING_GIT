<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- 부트스트랩 버전마다 include 링크가 따로 있으므로 맞는 걸 가져와서 사용하면 됨 -->
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>
	<form method="post">
	  <textarea id="summernote" name="editordata"></textarea>
	</form>
	
	<script>
		$(document).ready(function() {
		  $('#summernote').summernote({
			  placeholder: "게시글을 작성해주세요.",
			  height: 600,
			  maxHeight: 1000,
			  width: 800,
			  toolbar: [
				    // [groupName, [list of button]]
				    ['style', ['bold', 'italic', 'underline', 'clear']],
				    ['font', ['strikethrough', 'superscript', 'subscript']],
				    ['fontsize', ['fontsize']],
				    ['color', ['color']],
				    ['para', ['ul', 'ol', 'paragraph']],
				    ['height', ['height']],
				    ['insert', []]
				    ],
				      callbacks: {
				        // onImageUpload: function(files) {
				        //   // upload image to server and create imgNode...
				        // //   $summernote.summernote('insertNode', imgNode);
						// fileUpload(files);
				        //   // ajax 통신을 통한 이미지 업로드
				        // } // imgNode에 이미지 url이 들어감
						// $summernote.summernote('insertNode', imgNode)
						onImageUpload: fileUpload
				      }
			});
		  });
		// 이걸 커스터마이징 한 사람들 것 참고 하거나 get start 보기
		// callback

		// summernote에 이미지 업로드가 발생 했을 때 동작하는 함수
		function fileUpload(fileList){
			// summernote는 이미지를 추가하면 해당 이미지 파일을 전달해줌
			// callback 함수를 작성하지 않을 경우 자동으로 이미지를 string으로 변환해서 보여주지만
			// customCallback을 작성할 경우 해당 이미지의 경로를 직접 줘야 함
			
			// 파일 업로드를 할 때는 form 태그에서 encType을 multipart/form-data 형식으로
			// 요청하는 것처럼 자바 스크립트 객체에 FormData 객체를 이용해서 ajax 요청 해주기
			const fd = new FormData(); // 이걸 사용하려면 FormData 라는 객체를 이용해야 함
			// multipartformdata와 같은 자바 스크립트의 객체가 FormData
			
			for(let file of fileList){
				fd.append("fileList", file);
			}
			
			// fd.append("fileList", fileList[0]);

			insertFileApi(fd);

			$summernote.summernote('insertNode', imgNode);
			
			// 서버에 사진을 먼저 저장해줄 것
		}

		function insertFileApi(data, callback){
			$.ajax({
				url: "upload",
				type: "POST",
				data: data,
				processData: false, // 기본이 true, true일 때는 전송하는 data를 string으로 변환해서 요청
				contentType: false, // application/x-www-form-urlencoded; charset=UTF-8; (디폴트) -> multipart/form-data로 보내기 위해 false로 지정
				// 전송하는 데이터의 타입
				dataType: "json", // 서버로부터 json으로 데이터를 받겠다
				success: function(changeNameList){
					console.log(changeNameList)
				},
				error: function(){
					console.log("파일 업로드 api 요청 실패")
				}
			})
		}
	</script>
	
</body>
</html>