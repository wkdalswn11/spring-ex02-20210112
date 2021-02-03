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
			success: function(data, status, xhr) {
				console.log("등록 성공");
				//console.log(jqXHR.responseText); complite썻을때
				console.log(data);	// success 함수를 썻을때 위 주석처리한 것과 같은 것.
			},
			error: function() {
				console.log("등록 실패");
			}
		});
	});
	
	$("#btn-4").click(function() {
		$.ajax({
			/* method: "get" type: "get" 둘다 같음*/
			type: "get",
			url: "/replies/pages/218/1",
			success: function(data, status, xhr) {
				// console.log(jqXHR.responseText);
				console.log(data);
				console.log(status);
				console.log("실행 성공");
			}
		});
	});
	
	$("#btn-5").click(function() {
		$.ajax({
			type: "delete",
			url: "/replies/32",
			success: function(data, status, xhr) { // 파라미터 data, status, xhr을 다쓸필요는 x 다만 순서는 다르면안됨
				console.log(data);				   // 순서는 data, status, xhr 의 순서가 맞아야함.
				console.log(status);
				console.log("삭제 성공");
			},
			error: function() {
				console.log(status);
				console.log("삭제 실패");
			}
		});
	});
	
	$("#btn-6").click(function() {
		$.ajax({
			type: "put",
			url: "/replies/16",
			data: '{"reply":"수정한 댓글(ajax)"}',
			contentType: "application/json;charset=UTF-8", // 한글이깨질때 ;charset=UTF-8 추가
			success: function(data, status) {
				//console.log(jqXHR.responseText);
				console.log(data);
				console.log(status);
				console.log("수정 성공");
			},
			error: function(data, status, xhr) {
				console.log(status);
				console.log("수정 실패");
			}
		});
	});
});
</script>
</head>
<body>
<h1>AJAX ex 3</h1>
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