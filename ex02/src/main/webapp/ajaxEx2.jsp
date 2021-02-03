<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<title>Insert title here</title>
<script>
$(document).ready(function() {
	$("#btn-1").click(function() { // 여기부터
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"bno":218, "reply":"새 댓글입니다(ajax)", "replyer":"user96"}',
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);	// controller에 "success"와는 관련없음
			}
				/* complete는 해야하는 일임. */
		});	// 여기 } 까지 setting
	});
	
	$("#btn-2").click(function() {
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"reply":"새 댓글입니다(ajax)", "replyer":"user96"}', // 등록 실패를위해 bno값을 주지않음
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);	// controller에 "success"와는 관련없음
				//console.log(jqXHR.responseText);	// controller에 "success"와 관련이있음
			}
				/* complete는 해야하는 일임. */
		});
	});
	
	$("#btn-3").click(function() {
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"bno":218,"reply":"새 댓글입니다(ajax)", "replyer":"user96"}', // 등록 실패를위해 bno값을 주지않음
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log("등록 성공");
					console.log(jqXHR.responseText);	// controller에 "success999"와 관련이있음
				} else if (status === "error") {
					console.log("등록 실패");
				}
			}
				/* complete는 해야하는 일임. */
		});
	});
	
	$("#btn-4").click(function() {
		$.ajax({
			/* method: "get" type: "get" 둘다 같음*/
			type: "get",
			url: "/replies/pages/218/1",
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log(jqXHR.responseText);
					console.log(status);
				}
			}
		});
	});
	
	$("#btn-5").click(function() {
		$.ajax({
			type: "delete",
			url: "/replies/22",
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log(jqXHR.responseText);
					console.log("삭제 성공");
				} else if (status === "error") {
					console.log("삭제 실패");
				}
			}
		});
	});
	
	$("#btn-6").click(function() {
		$.ajax({
			type: "put",
			url: "/replies/16",
			data: '{"reply":"수정한 댓글(ajax)"}',
			contentType: "application/json;charset=UTF-8", // 한글이깨질때 ;charset=UTF-8 추가
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log(jqXHR.responseText);
					console.log(status);
				} else if (status === "error") {
					console.log("수정 실패");
					console.log(status);
				}
			}
		})
	})
});
</script>
</head>
<body>
<h1>AJAX ex 2</h1>
<div>
<button id="btn-1">댓글 등록 성공</button>
</div>
<div>
<button id="btn-2">댓글 등록 실패</button>
</div>
<div>
<button id="btn-3">댓글 등록 성공 또는 실패</button>
</div>
<div>
<button id="btn-4">댓글 목록</button>
</div>
<div>
<button id="btn-5">댓글 삭제</button>
</div>
<div>
<button id="btn-6">댓글 수정</button>
</div>
</body>
</html>